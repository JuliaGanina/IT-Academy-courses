package by.itacademy.ganina.writer.impl.impl;

import by.itacademy.ganina.transport.Transport;
import by.itacademy.ganina.writer.DocumentWriterException;
import by.itacademy.ganina.writer.impl.CommonDocumentWriter;
import org.json.JSONException;
import org.json.JSONWriter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class JsonDocumentWriter extends CommonDocumentWriter {
    @Override
    public void writeIntoFile(File fileToWrite, List<Transport> transportList) throws DocumentWriterException {
        try (final BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileToWrite, StandardCharsets.UTF_8))) {
            JSONWriter jsonWriter = new JSONWriter(bufferedWriter);
            jsonWriter.array();
            for (final Transport transport : transportList) {
                jsonWriter.object()
                        .key("type").value(transport.getType())
                        .key("model").value(transport.getModel())
                        .key("tax").value(transport.getTax())
                        .endObject();
            }
            jsonWriter.endArray();
            System.out.println(fileToWrite.getName() + " written to file with path:" + fileToWrite.getAbsolutePath());
        } catch (final JSONException | IOException ex) {
            throw new DocumentWriterException("Ошибка при записи файла " + fileToWrite.getAbsolutePath(), ex);
        }
    }
}
