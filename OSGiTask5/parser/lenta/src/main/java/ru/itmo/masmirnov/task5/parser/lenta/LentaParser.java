package ru.itmo.masmirnov.task5.parser.lenta;

import org.osgi.service.component.annotations.*;
import ru.itmo.masmirnov.task5.abstractparser.*;

import java.io.IOException;
import java.util.List;

@Component
public class LentaParser implements Parser {

    @Reference
    RSSParser parser;

    public String getName() {
        return "lenta";
    }

    public List<String> parseHeadlines() throws IOException {
        return parser.parseHeadlines("https://api.lenta.ru/rss");
    }

}