package ru.itmo.masmirnov.task5.urlextractor;

import java.io.IOException;

public interface URLExtractor {

    String extract(String url) throws IOException;

}
