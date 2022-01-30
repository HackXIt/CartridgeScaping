package fhtw.cartridgeScaping.json;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;

public class Json {
    private static Json singletonInstance;
    private ObjectMapper objectMapper;

    private Json() {
        objectMapper = new ObjectMapper();
    }

    public static Json getInstance() {
        if(singletonInstance == null) {
            singletonInstance = new Json();
        }
        return singletonInstance;
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public JsonNode parse(String src) throws IOException {
        return objectMapper.readTree(src);
    }

    public <A> A fromJson(JsonNode node, Class<A> clazz) throws JsonProcessingException {
        return objectMapper.treeToValue(node, clazz);
    }

    public JsonNode toJson(Object a) {
        return objectMapper.valueToTree(a);
    }

    public String stringify(JsonNode node) throws JsonProcessingException {
        return generateString(node, false);
    }

    public String prettyStringify(JsonNode node) throws JsonProcessingException {
        return generateString(node, true);
    }

    private String generateString(JsonNode node, boolean pretty) throws JsonProcessingException {
        ObjectWriter objectWriter = objectMapper.writer();
        if(pretty) {
            objectWriter = objectWriter.with(SerializationFeature.INDENT_OUTPUT);
        }
        return objectWriter.writeValueAsString(node);
    }
}
