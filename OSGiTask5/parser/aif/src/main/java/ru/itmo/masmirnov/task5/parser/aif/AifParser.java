package ru.itmo.masmirnov.task5.parser.aif;

import org.osgi.service.component.annotations.*;
import ru.itmo.masmirnov.task5.abstractparser.*;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;

@Component
public class AifParser implements Parser {

    @Reference
    RSSParser parser;

    public String getName() {
        return "aif";
    }

    // <![CDATA[content]]>   ->   content
    private static final Function<String, String> REMOVE_CDATA = h -> h.substring(9, h.length() - 3);

    public List<String> parseHeadlines() throws IOException {
        return parser.parseHeadlines("https://aif.ru/rss/news.php", REMOVE_CDATA);
    }

}