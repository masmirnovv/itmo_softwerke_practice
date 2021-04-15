package ru.itmo.masmirnov.task5.abstractparser;

import java.io.IOException;
import java.util.List;

public interface Parser {

    String getName();

    List<String> parseHeadlines() throws IOException;

}