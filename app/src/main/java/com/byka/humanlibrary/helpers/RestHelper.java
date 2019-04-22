package com.byka.humanlibrary.helpers;

import com.byka.humanlibrary.constants.RestConstants;
import com.byka.humanlibrary.converter.Converter;
import com.byka.humanlibrary.data.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RestHelper {
    private static final Logger logger = Logger.getLogger("RestHelper");

    private String getResponseAsString(final String stringUrl) throws IOException {
        final URL url = new URL(RestConstants.SERVER_ENDPOINT + stringUrl);
        final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        if (!RestConstants.AUTH_TOKEN.isEmpty()) {
            connection.setRequestProperty("Authorization", "Basic " + RestConstants.AUTH_TOKEN);
        }
        if (connection.getResponseCode() != 200) {
            logger.log(Level.WARNING, "Incorrect response " + connection.getResponseCode());
            return null;
        } else {
            final BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = reader.readLine();
            final StringBuilder builder = new StringBuilder();
            while (line != null) {
                builder.append(line);
                line = reader.readLine();
            }
            return builder.toString();
        }
    }

    public <T> List<T> getResponseAsArray(final String stringUrl, final Converter<T> converter) throws IOException, JSONException {
        final String stringResponse = getResponseAsString(stringUrl);
        if (stringResponse != null && !stringResponse.isEmpty()) {
            return converter.convert(new JSONArray(getResponseAsString(stringUrl)));
        } else {
            return Collections.emptyList();
        }
    }

    public <T> T getSingleResponse(final String stringUrl, final Converter<T> converter) throws IOException, JSONException {
        final String stringResponse = getResponseAsString(stringUrl);
        if (stringResponse != null && !stringResponse.isEmpty()) {
            return converter.convert(new JSONObject(stringResponse));
        } else {
            return null;
        }
    }
}
