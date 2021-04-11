package ru.itmo.masmirnov.task5.parser.vedomosti;

import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;
import org.osgi.service.component.annotations.*;
import ru.itmo.masmirnov.task5.urlextractor.Parser;
import ru.itmo.masmirnov.task5.urlextractor.URLExtractor;

import java.io.IOException;
import java.util.*;
import java.util.stream.*;

@Component(immediate = true)
public class VedomostiParser implements Parser {

    @Reference
    URLExtractor urlExtractor;

    public List<String> parseHeadlines() throws IOException {
        String content = urlExtractor.extract("https://api.lenta.ru/rss");
        Document document = Jsoup.parse(content);
        Elements headlineElements = document.select("item > title");

        return headlineElements.stream()                    // Stream<Element>
                .map(Element::text)                         // Stream<String>
                .collect(Collectors.toList());
    }

    // Temporary, until the rest of task is undone
    @Activate
    public void start() {
        System.out.println("Vedomosti parser started");
        try {
            parseHeadlines().forEach(System.out::println);
        } catch (IOException e) {
            System.out.println("IO Exception: " + e.getMessage());
        }
    }

}