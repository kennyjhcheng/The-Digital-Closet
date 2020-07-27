//package jsonparsing;
//
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.*;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//
//import java.io.File;
//import java.io.IOException;
//
//public class Json {
//
//    private static ObjectMapper objectMapper = getDefaultObjectMapper();
//
//    private static ObjectMapper getDefaultObjectMapper() {
//        ObjectMapper defaultObjectMapper = new ObjectMapper();
//        defaultObjectMapper.registerModule(new JavaTimeModule());
//        defaultObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        return defaultObjectMapper;
//    }
//
//    public static JsonNode parse(String src) throws JsonProcessingException {
//
//        return objectMapper.readTree(src);
//    }
//
//    public static <A> A fromJson(JsonNode node, Class<A> clazz) throws JsonProcessingException {
//        return objectMapper.treeToValue(node, clazz);
//    }
//
//    public static JsonNode toJson(Object a) {
//        return objectMapper.valueToTree(a);
//    }
//
//    public static String stringify(JsonNode node) throws JsonProcessingException {
//
//        return generateString(node, false);
//    }
//
//    public static String prettyPrint(JsonNode node) throws JsonProcessingException {
//        return generateString(node, true);
//    }
//
//    private static String generateString(JsonNode node, boolean pretty) throws JsonProcessingException {
//
//        ObjectWriter objectwriter = objectMapper.writer();
//        if (pretty) {
//            objectwriter = objectwriter.with(SerializationFeature.INDENT_OUTPUT);
//        }
//
//        return objectwriter.writeValueAsString(node);
//    }
//
//    // TODO: TESTS
//    public static void writeRegistrationToFile(String fileName, JsonNode inputNode) {
//        try {
//            getDefaultObjectMapper().writeValue(new File(fileName + ".json"), inputNode);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//}
