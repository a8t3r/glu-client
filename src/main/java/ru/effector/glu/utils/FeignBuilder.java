package ru.effector.glu.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import feign.Feign;
import feign.Logger;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.jaxrs.JAXRSContract;
import feign.slf4j.Slf4jLogger;

/**
 * @author Alexandr Kolosov
 * @since 28.07.2015
 */
public class FeignBuilder {

    public static Feign getFeign(String token) {
        ObjectMapper mapper = new ObjectMapper()
                .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        return getFeign(token, mapper);
    }

    public static Feign getFeign(String token, ObjectMapper mapper) {
        return Feign.builder()
                .encoder(new JacksonEncoder(mapper))
                .decoder(new JacksonDecoder(mapper))
                .contract(new JAXRSContract())
                .logger(new Slf4jLogger())
                .logLevel(Logger.Level.HEADERS)
                .requestInterceptor(requestFacade -> {
                    requestFacade.header("Content-Type", "application/json");
                    requestFacade.header("Authorization", token);
                })
                .build();
    }
}
