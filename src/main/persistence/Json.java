
package persistence;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jdk.internal.org.objectweb.asm.TypeReference;
import model.Closet;
import model.Clothing;
import model.StyleBoard;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

// handles converting data to and from Json files
public class Json {
    private static ObjectMapper objectMapper = getDefaultObjectMapper();
    public static ArrayNode userList = objectMapper.createArrayNode();
    public static ObjectWriter writer = getDefaultObjectMapper().writer(new DefaultPrettyPrinter());


    // creates object mapper
    public static ObjectMapper getDefaultObjectMapper() {
        ObjectMapper defaultObjectMapper = new ObjectMapper();
//        defaultObjectMapper.registerModule(new JavaTimeModule());
//        defaultObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return defaultObjectMapper;
    }


    // converts json node parsed from Json string into an object of the specified class
    public static <A> A fromJson(String jsonStr, Class<A> clazz) {
        try {
            return getDefaultObjectMapper().readValue(jsonStr, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    // converts an object to a JsonNode
    public static JsonNode toJson(Object a) {
        return objectMapper.valueToTree(a);
    }

    // writes the registered information to a file
    public static void writeRegistrationToFile(JsonNode inputNode, String user) throws IOException {
        userList.add(inputNode);
        writer.writeValue(new File("./data/" + user + "Info.json"), userList);
    }

    public static void removeRegistrationFromFile(JsonNode inputNode, String user) throws IOException {
        int index = 0;
        boolean foundUser = false;
        ArrayNode accounts = Json.getDefaultObjectMapper().readValue(Paths.get("./data/" + user + "Info.json")
                .toFile(), ArrayNode.class);

        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).equals(inputNode) && inputNode != null) {
                index = i;
                foundUser = true;
                break;
            }
        }

        if (foundUser) {
            accounts.remove(index);
            userList = accounts;
            writer.writeValue(new File("./data/" + user + "Info.json"), accounts);

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
        String jsonstr = new String(Files.readAllBytes(Paths.get("./data/" + username
                + "Closet.json")), StandardCharsets.UTF_8);

        Closet theCloset = Json.fromJson(jsonstr, Closet.class);

        return theCloset;

    }

    public static StyleBoard parseUserStyleBoard(String username) throws IOException {
        String jsonstr = new String(Files.readAllBytes(Paths.get("./data/" + username
                + "StyleBoard.json")), StandardCharsets.UTF_8);

        StyleBoard theStyleBoard = Json.fromJson(jsonstr, StyleBoard.class);

        return theStyleBoard;
    }


}

