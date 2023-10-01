package by.itacademy.ganina.core.reader.json;

import by.itacademy.ganina.core.reader.TransportReader;
import by.itacademy.ganina.core.converter.transport.TransportConverterException;
import by.itacademy.ganina.core.converter.transport.impl.TransportConverterImpl;
import by.itacademy.ganina.core.reader.TransportReaderException;
import by.itacademy.ganina.web.model.Transport;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

import static by.itacademy.ganina.util.CommonConstance.DEFAULT_CHARSET;

public class JsonTransportReader implements TransportReader {

    @Override
    public Map<Boolean, List<Transport>> read(final InputStream in) throws TransportReaderException, TransportConverterException {
        if (in == null) {
            return Collections.EMPTY_MAP;
        }

        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(in, DEFAULT_CHARSET))) {
            final String content = reader.lines()
                                         .reduce("", String::concat);
            final List<Transport> transportList = new ArrayList<>();

            final JSONArray jsonArray = new JSONArray(content);
            for (int index = 0; index < jsonArray.length(); index++) {
                final JSONObject jsonObject = jsonArray.getJSONObject(index);
                final String lines = jsonObject.get("type") + ", " + jsonObject.get("model");
                transportList.add(new TransportConverterImpl()
                                     .convertLinesToTransport(lines));
            }

            final List<Transport> validTransportList = new ArrayList<>();
            final List<Transport> invalidTransportList = new ArrayList<>();
            for (Transport transport : transportList) {
                if (transport.getPrice() == null) {
                    invalidTransportList.add(transport);
                } else {
                    validTransportList.add(transport);
                }
            }

            final Map<Boolean, List<Transport>> transportMap = new HashMap<>();
            transportMap.put(true, validTransportList);
            transportMap.put(false, invalidTransportList);

            return transportMap;
        } catch (final JSONException ex) {
            throw new TransportReaderException("Ошибка при чтении JSON контента", ex);
        } catch (final IllegalArgumentException ex) {
            throw new TransportReaderException("Ошибка определения типа транспорта", ex);
        } catch (final Exception ex) {
            throw new TransportConverterException("Ошибка анализа прочтенного контента", ex);
        }
    }
}
