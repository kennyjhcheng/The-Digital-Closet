
package persistence;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import model.Closet;
import model.StyleBoard;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

// handles converting data to and from Json files
public class Json {
    private static ObjectMapper objectMapper = getDefaultObjectMapper();
    public static ArrayNode userList = objectMapper.createArrayNode();
    public static ObjectWriter writer = getDefaultObjectMapper().writer(new DefaultPrettyPrinter());


    // creates object mapper
    private static ObjectMapper getDefaultObjectMapper() {
        ObjectMapper defaultObjectMapper = new ObjectMapper();
        defaultObjectMapper.registerModule(new JavaTimeModule());
        defaultObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return defaultObjectMapper;
    }

    // parses Json format string
    public static JsonNode parse(String src) throws JsonProcessingException {

        return objectMapper.readTree(src);
    }

    // converts json node parsed from Json string into an object of the specified class
    public static <A> A fromJson(JsonNode node, Class<A> clazz) throws JsonProcessingException {
        return objectMapper.treeToValue(node, clazz);
    }

    // converts an object to a JsonNode
    public static JsonNode toJson(Object a) {
        return objectMapper.valueToTree(a);
    }

    // converts a JsonNode to a json string
    public static String stringify(JsonNode node) throws JsonProcessingException {

        return generateString(node, false);
    }

    // converts a JsonNode into a json string with a better format
    public static String prettyPrint(JsonNode node) throws JsonProcessingException {
        return generateString(node, true);
    }

    // converts json node to json string that is 'pretty' if true, 'normal' if false
    private static String generateString(JsonNode node, boolean pretty) throws JsonProcessingException {

        ObjectWriter objectwriter = objectMapper.writer();
        if (pretty) {
            objectwriter = objectwriter.with(SerializationFeature.INDENT_OUTPUT);
        }

        return objectwriter.writeValueAsString(node);
    }

    // TODO: TESTS
    // writes the registered information to a file
    public static void writeRegistrationToFile(JsonNode inputNode) {
        try {
            userList.add(inputNode);
            writer.writeValue(new File("./data/UserInfo.json"), userList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void removeRegistrationFromFile(JsonNode inputNode) {
        int index = 0;
        boolean foundUser = false;


        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).equals(inputNode) && inputNode != null) {
                index = i;
                foundUser = true;
                break;
            }
        }

        if (foundUser) {
            userList.remove(index);
            try {
                writer.writeValue(new File("./data/UserInfo.json"), userList);
            } catch (IOException e) {
                System.out.println("Could not remove user");
            }
        } // todo exception for if user not found
    }

    public static ArrayNode parseUserInfo() throws IOException {
        return getDefaultObjectMapper().readValue(Paths.get("./data/UserInfo.json").toFile(), ArrayNode.class);
    }

    public static boolean userListContains(JsonNode node) {
        boolean foundUser = false;
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).equals(node) && node != null) {
                foundUser = true;
                break;
            }
        }
        return foundUser;
    }

    public static Closet parseUserCloset(String username) throws IOException {
        JsonNode closet = getDefaultObjectMapper().readValue(Paths.get("./data/" + username + "Closet.json")
                .toFile(), JsonNode.class);
        Closet theCloset = Json.fromJson(closet,  Closet.class);

        return theCloset;

    }

    public static StyleBoard parseUserStyleBoard(String username) throws IOException {
        JsonNode styleBoard =  getDefaultObjectMapper().readValue(Paths.get("./data/" + username + "StyleBoard.json")
                .toFile(), JsonNode.class);
        StyleBoard theStyleBoard = Json.fromJson(styleBoard, StyleBoard.class);

        return theStyleBoard;
    }


}

