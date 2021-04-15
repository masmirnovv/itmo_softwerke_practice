package ru.itmo.masmirnov.task5.parser.vedomosti;

import org.osgi.service.component.annotations.*;
import ru.itmo.masmirnov.task5.abstractparser.*;

import java.io.IOException;
import java.util.List;

@Component
public class VedomostiParser implements Parser {

    @Reference
    RSSParser parser;

    public String getName() {
        return "vedomosti";
    }

    public List<String> parseHeadlines() throws IOException {
        return parser.parseHeadlines("https://vedomosti.ru/rss/news");
    }

}