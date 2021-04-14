package ru.itmo.masmirnov.task5.urlextractor;

import java.io.IOException;
import java.util.List;

public interface Parser {

    String getName();

    List<String> parseHeadlines() throws IOException;

}