
package persistence;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.ArrayNode;

import model.Closet;

import model.StyleBoard;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

// handles converting data to and from Json files
public class Json {
    private static ObjectMapper objectMapper = getDefaultObjectMapper();
    public static ArrayNode userList = getDefaultObjectMapper().createArrayNode();
    public static ObjectWriter writer = getDefaultObjectMapper().writer(new DefaultPrettyPrinter());

    //constructor made to test Class -> no other purpose
    public Json() {

    }

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
    public static boolean writeRegistrationToFile(JsonNode inputNode, String user) throws IOException {
        String username = inputNode.get("username").asText();
        Closet closet = new Closet();
        StyleBoard styleBoard = new StyleBoard();
        boolean successfulLogin = !userListContains(inputNode);
        if (successfulLogin) {
            userList.add(inputNode);
            writer.writeValue(new File("./data/" + user + "Info.json"), userList);
            writer.writeValue(new File("./data/" + username + "Logged.json"), true);
            writer.writeValue(new File("./data/" + username + "Closet.json"), closet);
            writer.writeValue(new File("./data/" + username + "StyleBoard.json"), styleBoard);
        }

        return successfulLogin;
    }

    // removes registration from userList and files and removes associated files
    public static boolean removeRegistrationFromFile(JsonNode inputNode, String user) throws IOException {
        int index = 0;
        boolean foundUser = false;
        ArrayNode accounts = Json.getDefaultObjectMapper().readValue(Paths.get("./data/" + user + "Info.json")
                .toFile(), ArrayNode.class);

        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).equals(inputNode)) {
                index = i;
                foundUser = true;
                break;
            }
        }

        if (foundUser) {
            File closet = new File("./data/" + accounts.get(index).get("username").asText() + "Closet.json");
            File styleBoard = new File("./data/" + accounts.get(index).get("username").asText()
                    + "StyleBoard.json");
            File logged = new File("./data/" + accounts.get(index).get("username").asText() + "Logged.json");
            closet.delete();
            styleBoard.delete();
            logged.delete();

            accounts.remove(index);
            userList = accounts;
            writer.writeValue(new File("./data/" + user + "Info.json"), accounts);
        }
        return foundUser;
    }

    public static ArrayNode parseUserInfo(String user) throws IOException {
        return getDefaultObjectMapper().readValue(Paths.get("./data/" + user + "Info.json").toFile(),
                ArrayNode.class);
    }

    public static boolean userListContains(JsonNode node) {
        boolean foundUser = false;
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).equals(node)) {
                foundUser = true;
                break;
            }
        }
        return foundUser;
    }

    public static Closet parseUserCloset(String username) throws IOException {
        String jsonStr = new String(Files.readAllBytes(Paths.get("./data/" + username
                + "Closet.json")), StandardCharsets.UTF_8);

        Closet theCloset = Json.fromJson(jsonStr, Closet.class);

        return theCloset;

    }

    public static StyleBoard parseUserStyleBoard(String username) throws IOException {
        String jsonStr = new String(Files.readAllBytes(Paths.get("./data/" + username
                + "StyleBoard.json")), StandardCharsets.UTF_8);

        StyleBoard theStyleBoard = Json.fromJson(jsonStr, StyleBoard.class);

        return theStyleBoard;
    }


}

