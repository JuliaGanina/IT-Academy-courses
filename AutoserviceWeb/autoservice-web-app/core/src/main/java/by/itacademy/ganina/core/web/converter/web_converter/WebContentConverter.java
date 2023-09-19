package by.itacademy.ganina.core.web.converter.web_converter;

import java.io.InputStream;

public interface WebContentConverter {

    String convertWebContentToLines(final InputStream in) throws WebContentConverterException;
}
