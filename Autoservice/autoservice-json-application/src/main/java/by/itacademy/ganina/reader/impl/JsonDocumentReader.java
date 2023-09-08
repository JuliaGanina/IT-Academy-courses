package by.itacademy.ganina.reader.impl;

import by.itacademy.ganina.reader.DocumentReader;
import by.itacademy.ganina.reader.DocumentReaderException;
import by.itacademy.ganina.transport.Transport;
import by.itacademy.ganina.docs.impl.DocumentAdapterAndValidator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class JsonDocumentReader implements DocumentReader {

    @Override
    public List<Transport> readFile(File fileToRead) throws DocumentReaderException {
        String content = null;
        try {
            content = new String((Files.readAllBytes(Paths.get(fileToRead.toURI()))));
            JSONArray jsonArray = new JSONArray(content);
            List<Transport> transportList = new ArrayList<>();

            for (int index = 0; index < jsonArray.length(); index++) {
                JSONObject jsonObject = jsonArray.getJSONObject(index);
                String lines = jsonObject.get("type") + ", " + jsonObject.get("model");
                transportList.add(DocumentAdapterAndValidator.splitLines(lines));
            }
            return transportList;
        } catch (final JSONException ex) {
            throw new DocumentReaderException("Ошибка при чтении JSON контента", ex);
        } catch (final IOException ex) {
            throw new DocumentReaderException("Ошибка при чтении файла " + fileToRead, ex);
        } catch (final IllegalArgumentException ex) {
            throw new DocumentReaderException("Ошибка определения типа транспорта", ex);
        } catch (final RuntimeException ex) {
            throw new DocumentReaderException("Ошибка анализа прочтенного файла", ex);
        }
    }
}