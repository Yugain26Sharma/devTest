package com.flipkart.utility;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flipkart.setup.PropertyManager;

public class JsonOperations { 

	// read json file to string
	public static String readJsonFile(String propertyKey, String fileName) throws IOException {
		String testDataPath = getTestDataFilePath(propertyKey, fileName);
		return FileUtils.readFileToString(new File(testDataPath), StandardCharsets.UTF_8);
	}

	public static String getTestDataFilePath(String propertyKey, String fileName) {
		return Paths.get(PropertyManager.getProperty(propertyKey) + fileName).toString();
	}

	// convert json string to map
	public static Map<String, Object> convertJsonToMap(String json) {

		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode = null;
		try {
			jsonNode = objectMapper.readTree(json);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return processJsonNode(jsonNode);

	}

	private static Map<String, Object> processJsonNode(JsonNode jsonNode) {
		Map<String, Object> nodeDataMap = new LinkedHashMap<>();
		Iterator<Map.Entry<String, JsonNode>> fieldsIterator = jsonNode.fields();

		while (fieldsIterator.hasNext()) {
			Map.Entry<String, JsonNode> entry = fieldsIterator.next();
			String nodeName = entry.getKey();
			JsonNode nodeValue = entry.getValue();
			Object nodeData = null;

			if (nodeValue.isTextual()) {
				nodeData = nodeValue.asText();
			} else if (nodeValue.isNumber()) {
				nodeData = nodeValue.numberValue();
			} else if (nodeValue.isNull()) {
				nodeData = null;
			} else if (nodeValue.isObject()) {
				nodeData = processJsonNode(nodeValue);
			} else if (nodeValue.isArray()) {
				parseJsonArray(nodeName, nodeDataMap, nodeValue);
			}

			if (!nodeValue.isArray()) {
				nodeDataMap.put(nodeName, nodeData);
			}
		}
		return nodeDataMap;
	}

	private static void parseJsonArray(String nodeName, Map<String, Object> nodeDataMap, JsonNode jsonArray) {
		Iterator<JsonNode> elementsIterator = jsonArray.elements();
		StringBuilder sb = new StringBuilder();

		int count = 0;

		while (elementsIterator.hasNext()) {
			JsonNode elementNode = elementsIterator.next();

			if (!elementNode.isObject()) {
				if (elementNode.isTextual()) {
					sb.append(elementNode.asText());
				} else if (elementNode.isNumber()) {
					sb.append(elementNode.numberValue());
				} else if (elementNode.isBoolean()) {
					sb.append(elementNode.asBoolean());
				} else if (elementNode.isNull()) {
					sb.append("null");
				}

				if (elementsIterator.hasNext()) {
					sb.append(",");
				}

				nodeDataMap.put(nodeName, sb.toString());
			} else {
				nodeDataMap.put(nodeName + count, processJsonNode(elementNode));
			}
			count++;
		}
	}

}
