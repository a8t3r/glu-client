package ru.effector.glu.utils;

import feign.Response;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Alexandr Kolosov
 * @since 28.07.2015
 */
public class ResponseUtils {

    public static String asString(Response response) {
        try {
            InputStream stream = response.body().asInputStream();
            return IOUtils.toString(stream);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
