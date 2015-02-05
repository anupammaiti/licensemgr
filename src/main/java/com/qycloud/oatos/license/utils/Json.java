package com.qycloud.oatos.license.utils;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * json
 * 
 * @author yang
 * 
 */
public final class Json {

	private static final ObjectMapper mapper = new ObjectMapper();

	private Json() {

	}

	/**
	 * to json
	 * @param obj
	 * @return
	 * @throws Exception 
	 */
	public static String toJson(Object obj) throws Exception {
		return mapper.writeValueAsString(obj);
	}

	/**
	 * parse json as Object
	 * @param json
	 * @param clazz
	 * @return
	 * @throws Exception 
	 */
	public static <T> T parse(String json, Class<T> clazz) throws Exception {
		return mapper.readValue(json, clazz);
	}

	/**
	 * parse json use TypeReference
	 * @param json
	 * @param typeRef
	 * @return
	 * @throws Exception
	 */
	public static <T> T parse(String json, TypeReference<T> typeRef) throws Exception {
		return mapper.readValue(json, typeRef);
	}

	/**
	 * parse json as array
	 * @param json
	 * @param typeRef
	 * @return
	 * @throws Exception 
	 */
	@Deprecated
	public static <T> T parseArray(String json, TypeReference<T> typeRef) throws Exception {
		return mapper.readValue(json, typeRef);
	}

}
