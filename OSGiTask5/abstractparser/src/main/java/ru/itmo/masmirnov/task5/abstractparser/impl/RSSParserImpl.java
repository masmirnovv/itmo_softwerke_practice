package ru.itmo.masmirnov.task5.abstractparser.impl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;
import org.osgi.service.component.annotations.*;
import ru.itmo.masmirnov.task5.abstractparser.RSSParser;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component(service = RSSParser.class)
public class RSSParserImpl implements RSSParser {

    private static final int END = -1;

    private static String extract(String urlName) throws IOException {

        URL url = new URL(urlName);
        URLConnection connection = url.openConnection();
        StringBuilder sb = new StringBuilder();

        try (InputStream in = connection.getInputStream();
             Reader reader = new InputStreamReader(in, StandardCharsets.UTF_8)) {
            int ch;
            while ((ch = reader.read()) != END) {
                sb.append((char) ch);
            }
        }

        return sb.toString();

    }

    public List<String> parseHeadlines(
            String url,
            Function<String, String> extraHeadlineAction
    ) throws IOException {
        String content = extract(url);
        Document document = Jsoup.parse(content);
        Elements headlineElements = document.select("item > title");

        return headlineElements.stream()                    // Stream<Element>
                .map(Element::text)                         // Stream<String>
                .map(extraHeadlineAction)
                .collect(Collectors.toList());
    }

    public List<String> parseHeadlines(String url) throws IOException {
        return parseHeadlines(url, String::toString);
    }

}
