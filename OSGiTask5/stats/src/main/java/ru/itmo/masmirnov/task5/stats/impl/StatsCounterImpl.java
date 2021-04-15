package ru.itmo.masmirnov.task5.stats.impl;

import org.osgi.service.component.annotations.*;
import osgi.enroute.debug.api.Debug;
import ru.itmo.masmirnov.task5.stats.StatsCounter;
import ru.itmo.masmirnov.task5.abstractparser.Parser;

import java.io.IOException;
import java.util.*;

@Component(
        service = StatsCounter.class,
        property = {
                Debug.COMMAND_SCOPE + "=news",
                Debug.COMMAND_FUNCTION + "=stats"
        }
)
public class StatsCounterImpl implements StatsCounter {

    private static final int DEFAULT_TOP_ENTRIES = 10;



    private static Map<String, Parser> services = new HashMap<>();

    @Reference(
            service = Parser.class,
            cardinality = ReferenceCardinality.AT_LEAST_ONE,
            bind = "bindService",
            unbind = "unbindService"
    )
    private void bindService(Parser service) {
        services.put(service.getName(), service);
    }

    private void unbindService(Parser service) {
        services.remove(service.getName());
    }



    public void stats(String... args) {
        int topN = DEFAULT_TOP_ENTRIES;
        switch (args.length) {
            case 0:
                printInfo();
                printAvailableSources();
                break;
            case 2:
                try {
                    topN = Integer.parseInt(args[1]);
                    if (topN < 1)
                        throw new NumberFormatException();
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number: " + args[1]);
                    break;
                }
                /* falls through */
            case 1:
                Parser service = services.get(args[0].toLowerCase());
                if (service == null) {
                    System.out.println("Unknown source: " + args[0]);
                    printAvailableSources();
                    break;
                }
                try {
                    System.out.println("Top entries in " + service.getName() + ": ");
                    List<String> headlines = service.parseHeadlines();
                    List<Map.Entry<String, Integer>> topEntries = topEntries(headlines, topN);
                    topEntries.forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));
                } catch (IOException e) {
                    System.out.println("IO Exception while parsing headlines: " + e.getMessage());
                }
                break;
            default:
                System.out.println("Expected 1 or 2 args");
                printInfo();
        }
    }

    private static List<Map.Entry<String, Integer>> topEntries(List<String> headlines, int topN) {
        Map<String, Integer> wordFreq = new HashMap<>();
        headlines.stream()
                .flatMap(h -> Arrays.stream(h.split("\\s+")))
                .map(String::toLowerCase)
                .forEach(entry -> wordFreq.put(entry, wordFreq.getOrDefault(entry, 0) + 1));

        List<Map.Entry<String, Integer>> wordList = new ArrayList<>(wordFreq.entrySet());
        wordList.sort(Map.Entry.comparingByValue());
        Collections.reverse(wordList);
        return topN < wordList.size()? wordList.subList(0, topN) : wordList;
    }

    private static void printInfo() {
        System.out.println("Command:  news:stats <source> [topN]");
        System.out.println("  source: name of source");
        System.out.println("  topN:   number of top entries to print (or as much as possible if");
        System.out.println("          total number of entries is less than topN). Default value is " + DEFAULT_TOP_ENTRIES);
    }

    private static void printAvailableSources() {
        System.out.println("Available sources:");
        services.keySet().forEach(s -> System.out.println("  " + s));
    }

}