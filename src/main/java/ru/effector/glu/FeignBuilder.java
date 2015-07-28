package ru.effector.glu;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.jaxrs.JAXRSContract;
import feign.slf4j.Slf4jLogger;

import java.util.Base64;

/**
 * @author Alexandr Kolosov
 * @since 28.07.2015
 */
public class FeignBuilder {

    public static Feign getFeign(String username, String password) {
        ObjectMapper mapper = new ObjectMapper()
                .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        return getFeign(username, password, mapper);
    }

    public static Feign getFeign(String username, String password, ObjectMapper mapper) {
        return Feign.builder()
                .encoder(new JacksonEncoder(mapper))
                .decoder(new JacksonDecoder(mapper))
                .contract(new JAXRSContract())
                .logger(new Slf4jLogger())
                .requestInterceptor(requestFacade -> {
                    requestFacade.header("Content-Type", "application/json");
                    String token = username + ":" + password;
                    requestFacade.header("Authorization", "Basic " + Base64.getEncoder().encodeToString(token.getBytes()));
                })
                .build();
    }
}
