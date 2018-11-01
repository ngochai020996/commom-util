/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.axonactive.common.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;


/**
 *
 * @author nvmuon
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JsonUtils {
	private static final ObjectMapper mapper = new ObjectMapper();

	static {
		mapper.findAndRegisterModules();
		mapper.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
	}

	public static <T> T fromJson(String json, Class<T> clazz) {
		try {
			return mapper.readerFor(clazz).readValue(json);
		} catch (IOException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	public static <T> String toJson(T obj) {
		try {
			return mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	public static JsonNode toJsonNode (String json) {
		try {
			return mapper.readTree(json);
		} catch (IOException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}
	
	public static <T> T fromJsonNode(JsonNode jsonNode, Class<T> clazz) {
		try {
			return mapper.treeToValue(jsonNode, clazz);
		} catch (JsonProcessingException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

}
