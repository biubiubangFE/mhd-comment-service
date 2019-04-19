package com.mhdss.comment.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 */
public class JsonUtil {

    private JsonUtil() {
	}

	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	public static ObjectMapper getMapper() {
		return OBJECT_MAPPER;
	}

}
