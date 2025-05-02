package com.pratik.payGateBlend.common.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pratik.payGateBlend.setting.pojo.FieldValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

public class RowMapperUtil {
	private static final ObjectMapper objectMapper = new ObjectMapper();
	
	@Nullable
	public static String getString(@NotNull ResultSet resultSet, String keyName) throws SQLException {
		String data = resultSet.getString(keyName);
		return Objects.equals(data, "null") ? null : data;
	}

	@Nullable
	public static Long getLong(@NotNull ResultSet resultSet, String keyName) throws SQLException {
		Long data = resultSet.getLong(keyName);
		return resultSet.wasNull()? null : data;
	}

	@Nullable
	public static Integer getInteger(@NotNull ResultSet resultSet, String keyName) throws SQLException {
		Integer data = resultSet.getInt(keyName);
		return resultSet.wasNull()? null : data;
	}

	@Nullable
	public static Double getDouble(@NotNull ResultSet resultSet, String keyName) throws SQLException {
		Double data = resultSet.getDouble(keyName);
		return resultSet.wasNull()? null : data;
	}

	@Nullable
	public static Boolean getBoolean(@NotNull ResultSet resultSet, String keyName) throws SQLException {
		Boolean data = resultSet.getBoolean(keyName);
		return resultSet.wasNull()? null : data;
	}

	@Nullable
	public static Timestamp getTimestamp(@NotNull ResultSet resultSet, String keyName) throws SQLException {
		Timestamp data = resultSet.getTimestamp(keyName);
		return resultSet.wasNull()? null : data;
	}

	@Nullable
	public static Date getDate(@NotNull ResultSet resultSet, String keyName) throws SQLException {
		Date data = resultSet.getDate(keyName);
		return resultSet.wasNull()? null : data;
	}

	@Nullable
	public static Map<String, Object> getMap(@NotNull ResultSet resultSet, String keyName) throws SQLException {
		String json = resultSet.getString(keyName);
		if (resultSet.wasNull() || json == null || json.isBlank()) {
			return null;
		}
		try {
			return objectMapper.readValue(json, new TypeReference<Map<String, Object>>() {});
		} catch (Exception e) {
			throw new SQLException("Failed to parse JSON to Map for column: " + keyName, e);
		}
	}

	@Nullable
	public static List<Map<String, Object>> getListOfMaps(@NotNull ResultSet resultSet, String keyName) throws SQLException {
		String json = resultSet.getString(keyName);
		if (resultSet.wasNull() || json == null || json.isBlank()) {
			return null;
		}
		try {
			return objectMapper.readValue(json, new TypeReference<List<Map<String, Object>>>() {});
		} catch (Exception e) {
			throw new SQLException("Failed to parse JSON to List<Map<String, Object>> for column: " + keyName, e);
		}
	}

	@Nullable
	public static Object getJsonObject(@NotNull ResultSet resultSet, String keyName) throws SQLException {
		String json = resultSet.getString(keyName);
		if (resultSet.wasNull() || json == null || json.isBlank()) {
			return null;
		}
		try {
			return objectMapper.readValue(json, Object.class);
		} catch (Exception e) {
			throw new SQLException("Failed to parse JSON for column: " + keyName, e);
		}
	}

	@Nullable
	public static List<FieldValue> getListOfFieldValues(@NotNull ResultSet resultSet, String keyName) throws SQLException {
		String json = resultSet.getString(keyName);
		if (resultSet.wasNull() || json == null || json.isBlank()) {
			return null;
		}

		try {
			List<Map<String, Object>> listOfMaps = objectMapper.readValue(json, new TypeReference<>() {});
			List<FieldValue> fieldValues = new ArrayList<>();

			for (Map<String, Object> map : listOfMaps) {
				String field = (String) map.get("field");
				boolean isKeyValue = Boolean.parseBoolean(String.valueOf(map.get("keyValue")));
				String value = map.get("value") != null ? map.get("value").toString() : null;

				List<String> values = null;

				if (map.containsKey("values") && map.get("values") instanceof List) {
					values = objectMapper.convertValue(map.get("values"), new TypeReference<List<String>>() {});
				}

				List<Map<String, String>> keyValueMapList = null;
				if (map.containsKey("map") && map.get("map") instanceof List) {
					keyValueMapList = new ArrayList<>();
					List<?> rawList = (List<?>) map.get("map");
					for (Object item : rawList) {
						if (item instanceof Map) {
							Map<String, String> entryMap = new LinkedHashMap<>();
							for (Map.Entry<?, ?> entry : ((Map<?, ?>) item).entrySet()) {
								entryMap.put(String.valueOf(entry.getKey()), entry.getValue() != null ? entry.getValue().toString() : null);
							}
							keyValueMapList.add(entryMap);
						}
					}
				}

				fieldValues.add(new FieldValue(field, isKeyValue, value, values, keyValueMapList));
			}

			return fieldValues;
		} catch (Exception e) {
			throw new SQLException("Failed to parse JSON to List<FieldValue> for column: " + keyName, e);
		}
	}



	@Nullable
	public static List<String> getListOfStrings(@NotNull ResultSet resultSet, String keyName) throws SQLException {
		String json = resultSet.getString(keyName);
		if (resultSet.wasNull() || json == null || json.isBlank()) {
			return null;
		}
		try {
			return objectMapper.readValue(json, new TypeReference<List<String>>() {});
		} catch (Exception e) {
			throw new SQLException("Failed to parse JSON to List<String> for column: " + keyName, e);
		}
	}







}
