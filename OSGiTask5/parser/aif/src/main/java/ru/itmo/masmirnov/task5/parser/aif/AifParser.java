package ru.itmo.masmirnov.task5.parser.aif;

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
public class AifParser implements Parser {

    @Reference
    URLExtractor urlExtractor;

    public List<String> parseHeadlines() throws IOException {
        String content = urlExtractor.extract("https://aif.ru/rss/news.php");
        Document document = Jsoup.parse(content);
        Elements headlineElements = document.select("item > title");

        return headlineElements.stream()                    // Stream<Element>
                .map(Element::text)                         // Stream<String>
                .map(h -> h.substring(9, h.length() - 3))   // Stream<String>;      <![CDATA[content]]>   ->   content
                .collect(Collectors.toList());
    }

    // Temporary, until the rest of task is undone
    @Activate
    public void start(Map<String, Object> props) {
        System.out.println("Aif parser started");

        try {
            parseHeadlines().forEach(System.out::println);
        } catch (IOException e) {
            System.out.println("IO Exception: " + e.getMessage());
        }
    }

}