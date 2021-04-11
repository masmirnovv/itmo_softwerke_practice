package ru.itmo.masmirnov.task5.urlextractor.impl;

import org.osgi.service.component.annotations.*;
import ru.itmo.masmirnov.task5.urlextractor.URLExtractor;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

@Component(service = URLExtractor.class)
public class URLExtractorImpl implements URLExtractor {

    private static final int END = -1;

    public String extract(String urlName) throws IOException {

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

}
