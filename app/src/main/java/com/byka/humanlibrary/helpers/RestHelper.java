package com.byka.humanlibrary.helpers;

import com.byka.humanlibrary.constants.RestConstants;
import com.byka.humanlibrary.data.NewUserData;
import com.byka.humanlibrary.data.PostData;
import com.byka.humanlibrary.data.User;
import com.byka.humanlibrary.handler.DefaultResponseErrorHandler;
import com.byka.humanlibrary.wrapper.GenericListWrapper;

import org.springframework.http.HttpBasicAuthentication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.logging.Logger;

public class RestHelper {
    private static final Logger logger = Logger.getLogger("RestHelper");

    private HttpHeaders addAuth() {
        return new HttpHeaders() {{
            if (RestConstants.AUTH_TOKEN != null && !RestConstants.AUTH_TOKEN.isEmpty()) {
                set(HttpHeaders.AUTHORIZATION, "Basic " + RestConstants.AUTH_TOKEN);
            }
        }};
    }

    private HttpHeaders contentTypeHeader() {
        return new HttpHeaders() {{
            set(HttpHeaders.CONTENT_TYPE, "application/json");
        }};
    }

    public <E, T extends GenericListWrapper<E>> ResponseEntity<T> getResponseAsArray(final String stringUrl, final Class<T> responseClass) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(Collections.singletonList(new GsonHttpMessageConverter()));

        return restTemplate.exchange(RestConstants.SERVER_ENDPOINT + stringUrl, HttpMethod.GET, new HttpEntity<>(addAuth()), responseClass);
    }

    public <T> ResponseEntity<T> getSingleResponse(final String stringUrl, final Class<T> responseClass) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler());
        restTemplate.setMessageConverters(Collections.singletonList(new GsonHttpMessageConverter()));

        return restTemplate.exchange
                (RestConstants.SERVER_ENDPOINT + stringUrl, HttpMethod.GET, new HttpEntity<T>(addAuth()), responseClass);
    }

    public <T> ResponseEntity<T> post(String url, PostData param, Class<T> responseClass) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler());
        restTemplate.setMessageConverters(Collections.singletonList(new GsonHttpMessageConverter()));

        return restTemplate.exchange
                (RestConstants.SERVER_ENDPOINT + url, HttpMethod.POST, new HttpEntity<>(param, contentTypeHeader()), responseClass);
    }
}
