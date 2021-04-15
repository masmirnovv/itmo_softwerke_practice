package ru.itmo.masmirnov.task5.abstractparser;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;

public interface RSSParser {

    List<String> parseHeadlines(
            String url,
            Function<String, String> extraHeadlineAction
    ) throws IOException;

    List<String> parseHeadlines(String url) throws IOException;

}
