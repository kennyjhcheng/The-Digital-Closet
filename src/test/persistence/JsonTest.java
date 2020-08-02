package persistence;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import exceptions.DuplicateClothingException;
import model.Closet;
import model.Clothing;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.pojo.SimpleTestCaseJsonPOJO;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class JsonTest {

    private String simpleTestCaseJsonSource = "{\n" +
            "  \"title\": \"Coder From Scratch\"\n" +
            "}";

    private String dayScenario1 = "{\n" +
            "  \"date\": \"2019-12-25\",\n" +
            "  \"name\": \"Christmas Day\"\n" +
            "}";

    private String authorBookScenario = "{\n" +
            "  \"authorName\": \"Rui\",\n" +
            "  \"books\": [\n" +
            "    {\n" +
            "      \"title\": \"title1\",\n" +
            "      \"inPrint\": true,\n" +
            "      \"publishDate\": \"2019-12-25\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"title\": \"title2\",\n" +
            "      \"inPrint\": false,\n" +
            "      \"publishDate\": \"2019-01-01\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";
    Closet testCloset;
    Clothing clothing1;
    Clothing clothing2;
    Clothing clothing3;
    Clothing clothing4;

    @BeforeEach
    public void runBefore() throws DuplicateClothingException {
        testCloset = new Closet();
        clothing1 = new Clothing("H&M Shirt", "shirt", "black", 2.0);
        clothing2 = new Clothing("Levi Jeans", "pants", "blue", 32.0);
        clothing3 = new Clothing("Gucci Shirt", "shirt", "yellow", 3.0);
        clothing4 = new Clothing("off-white socks", "socks", "white", 3.0);
        testCloset.addClothing(clothing1);
        testCloset.addClothing(clothing2);
        testCloset.addClothing(clothing3);
        testCloset.addClothing(clothing4);
    }

    @Test
    public void fromJson() throws JsonProcessingException {

        SimpleTestCaseJsonPOJO pojo = Json.fromJson(simpleTestCaseJsonSource, SimpleTestCaseJsonPOJO.class);

        assertEquals(pojo.getTitle(), "Coder From Scratch");

    }

    @Test
    public void toJson() {
        SimpleTestCaseJsonPOJO pojo = new SimpleTestCaseJsonPOJO();
        pojo.setTitle("Testing 123");

        JsonNode node = Json.toJson(pojo);

        assertEquals(node.get("title").asText(), "Testing 123");
    }

    //    @Test
//    public void dayTestScenario1() throws JsonProcessingException {
//
//        DayPOJO pojo = Json.fromJson(dayScenario1, DayPOJO.class);
//
//        assertEquals("2019-12-25", pojo.getDate().toString());
//    }
//
//    @Test
//    public void authorBookScenario1() throws JsonProcessingException {
//
//        AuthorPOJO pojo = Json.fromJson(authorBookScenario, AuthorPOJO.class);
//
//        System.out.println("Author : " + pojo.getAuthorName());
//        for (BookPOJO b : pojo.getBooks()) {
//            System.out.println("Book : " + b.getTitle());
//            System.out.println("Is In Print? : " + b.getPublishDate());
//            System.out.println("Date : " + b.getPublishDate());
//        }
//    }
    @Test
    public void testWriteRegistrationToFile() {
        try {
            Registration account = new Registration("testUsername", "testPassword");
            boolean found = false;

            JsonNode accountNode = Json.toJson(account);
            Json.writeRegistrationToFile(accountNode, "test");
            ArrayNode accounts = Json.getDefaultObjectMapper().readValue(Paths.get("./data/testInfo.json").toFile(),
                    ArrayNode.class);

            for (int i = 0; i < accounts.size(); i++) {
                if (accounts.get(i).equals(accountNode)) {
                    found = true;
                    break;
                }
            }
            assertTrue(found);
        } catch (IOException e) {
            e.printStackTrace();
            fail("shouldn't throw exception");
        }


    }

    @Test
    public void testWriteRegistrationToFileFailExceptionThrown() {
        try {
            Registration account = new Registration("testUsername", "testPassword");
            boolean found = false;

            JsonNode accountNode = Json.toJson(account);
            Json.writeRegistrationToFile(accountNode, "test");
            ArrayNode accounts = Json.getDefaultObjectMapper().readValue(Paths.get("./data/test.json").toFile(),
                    ArrayNode.class);

            for (int i = 0; i < accounts.size(); i++) {
                if (accounts.get(i).equals(accountNode)) {
                    found = true;
                    break;
                }
            }
            assertTrue(found);
            fail();
        } catch (IOException e) {

        }
    }

    @Test
    public void testRemoveRegistrationFromFileFound() {
        Registration account1 = new Registration("testUsername", "testPassword");
        JsonNode accountNode1 = Json.toJson(account1);
        boolean found = true;
        try {
            Json.writeRegistrationToFile(accountNode1, "test");
            Json.removeRegistrationFromFile(accountNode1, "test");
            ArrayNode accounts = Json.getDefaultObjectMapper().readValue(Paths.get("./data/testInfo.json").toFile(),
                    ArrayNode.class);

            for (int i = 0; i < accounts.size(); i++) {
                if (accounts.get(i).equals(accountNode1)) {
                    found = false;
                    break;
                }
            }
            assertFalse(found);
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testRemoveRegistrationFromFilNoteFound() {
        Registration account1 = new Registration("testUsername", "testPassword");
        Registration account2 = new Registration("testUsername2", "testPassword2");
        JsonNode accountNode1 = Json.toJson(account1);
        JsonNode accountNode2 = Json.toJson(account2);

        boolean found = true;
        try {
            Json.writeRegistrationToFile(accountNode1, "test");
            Json.removeRegistrationFromFile(accountNode2, "test");
            ArrayNode accounts = Json.getDefaultObjectMapper().readValue(Paths.get("./data/testInfo.json").toFile(),
                    ArrayNode.class);

            for (int i = 0; i < accounts.size(); i++) {
                if (accounts.get(i).equals(accountNode2)) {
                    found = false;
                    break;
                }
            }
            assertTrue(found);
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testSaveClosetToFile() {
        try {
            Json.writer.writeValue(new File("./data/testCloset.json"), testCloset);
        } catch (IOException e) {
            fail("Exception shouldn't't have been thrown");
        }
    }

    @Test
    public void testParseUserCloset() {
        Closet testCloset = new Closet();
        try {
            testCloset = Json.getDefaultObjectMapper().readValue(new File("./data/testCloset.json"), Closet.class);

        } catch (IOException e) {
            e.printStackTrace();
            fail("Exception shouldn't have been thrown");
        }

        assertTrue(testCloset.containsClothing("Levi Jeans"));
        assertEquals(testCloset.collectionSize(), 4);
    }

}