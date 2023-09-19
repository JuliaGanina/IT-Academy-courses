package by.itacademy.ganina.core.web.reader.json;

import by.itacademy.ganina.core.web.converter.transport_converter.TransportConverterException;
import by.itacademy.ganina.core.web.converter.web_converter.WebContentConverterException;
import by.itacademy.ganina.core.web.converter.transport_converter.impl.TransportConverterImpl;
import by.itacademy.ganina.core.web.converter.web_converter.impl.JsonWebContentConverter;
import by.itacademy.ganina.core.web.reader.TransportReader;
import by.itacademy.ganina.core.web.reader.TransportReaderException;
import by.itacademy.ganina.web.model.Transport;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.*;

public class JsonTransportReader implements TransportReader {

    @Override
    public Map<Boolean, List<Transport>> read(final InputStream in) throws TransportReaderException, WebContentConverterException, TransportConverterException {
       if (in == null) {
            return Collections.EMPTY_MAP;
        }

        final String content = new JsonWebContentConverter().convertWebContentToLines(in);

        try {
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
                if (transport.getTax() == null) {
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
        } catch (final RuntimeException ex) {
            throw new WebContentConverterException("Ошибка анализа прочтенного контента", ex);
        } catch (final Exception ex) {
            throw new TransportConverterException("Ошибка анализа прочтенного контента", ex);
        }
    }
}
