package by.itacademy.ganina.core.web.converter.web_converter.impl;

import by.itacademy.ganina.core.web.converter.web_converter.WebContentConverter;
import by.itacademy.ganina.core.web.converter.web_converter.WebContentConverterException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class JsonWebContentConverter implements WebContentConverter {

    @Override
    public String convertWebContentToLines(final InputStream in) throws WebContentConverterException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {
            return reader.lines()
                         .reduce("", String::concat);
        } catch (final IOException ex) {
            throw new WebContentConverterException("ошибка чтения контента", ex);
        }
    }
}
