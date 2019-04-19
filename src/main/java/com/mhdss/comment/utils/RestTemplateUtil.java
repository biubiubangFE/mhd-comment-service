package com.mhdss.comment.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class RestTemplateUtil {

    public static HttpEntity<String> buildJsonRequestEntity(Object obj) {
        if (obj == null) {
            return null;
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        try {
            return new HttpEntity<>(JsonUtil.getMapper().writeValueAsString(obj), headers);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
