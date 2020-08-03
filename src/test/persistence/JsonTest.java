package persistence;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import exceptions.DuplicateClothingException;
import model.Closet;
import model.Clothing;
import model.Outfit;
import model.StyleBoard;
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
    Outfit outfit1;
    Outfit outfit2;

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

        outfit1 = new Outfit("3piece");
        outfit2 = new Outfit("2piece");
        outfit1.addClothing(clothing1);
        outfit1.addClothing(clothing2);
        outfit1.addClothing(clothing4);
        outfit2.addClothing(clothing1);
        outfit2.addClothing(clothing2);

    }

    @Test
    void testConstructor() {
        Json json = new Json();
    }

    @Test
    public void fromJson() throws JsonProcessingException {

        SimpleTestCaseJsonPOJO pojo = Json.fromJson(simpleTestCaseJsonSource, SimpleTestCaseJsonPOJO.class);

        assertEquals(pojo.getTitle(), "Coder From Scratch");

        try {
            SimpleTestCaseJsonPOJO failPojo = Json.fromJson(dayScenario1, SimpleTestCaseJsonPOJO.class);
            fail();
        } catch (Exception e) {

        }

    }

    @Test
    public void toJson() {
        SimpleTestCaseJsonPOJO pojo = new SimpleTestCaseJsonPOJO();
        pojo.setTitle("Testing 123");

        JsonNode node = Json.toJson(pojo);

        assertEquals(node.get("title").asText(), "Testing 123");
    }

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
    public void testRemoveRegistrationFromFileSuccess() {
        Registration account1 = new Registration("removeuser", "removepass");
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
            assertTrue(found);
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testRemoveRegistrationFromFileInputNodeNull() {
        Registration account1 = new Registration("reguser", "regpass");
        JsonNode accountNode1 = Json.toJson(account1);
        boolean found = true;
        try {
            Json.writeRegistrationToFile(accountNode1, "test");
            Json.removeRegistrationFromFile(null, "test");
            ArrayNode accounts = Json.getDefaultObjectMapper().readValue(Paths.get("./data/testInfo.json").toFile(),
                    ArrayNode.class);

            for (int i = 0; i < accounts.size(); i++) {
                if (accounts.get(i).equals(accountNode1)) {
                    found = false;
                    break;
                }
            }
            assertFalse(found);
            Json.removeRegistrationFromFile(accountNode1, "test");
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
            ArrayNode testAccounts = Json.getDefaultObjectMapper().readValue(Paths.get("./data/testInfo.json").toFile(),
                    ArrayNode.class);

            for (int i = 0; i < testAccounts.size(); i++) {
                if (testAccounts.get(i).equals(accountNode2)) {
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
    public void testParserUserInfo() {
        ArrayNode testAccounts = null;
        Registration account1 = new Registration("testuser1", "testpass1");
        Registration account2 = new Registration("testuser2", "testpass2");
        JsonNode accountNode1 = Json.toJson(account1);
        JsonNode accountNode2 = Json.toJson(account2);

        try {
            testAccounts = Json.parseUserInfo("testParse");
        } catch (IOException e) {
            e.printStackTrace();
            fail("Exception should not have been thrown");
        }

        assertEquals(testAccounts.get(0), accountNode1);
        assertEquals(testAccounts.get(1), accountNode2);
        assertEquals(testAccounts.size(), 2);
    }

    @Test
    public void testUserListContains() {
        Registration account1 = new Registration("contains1", "testpass1");
        Registration account2 = new Registration("contains2", "testpass2");
        Registration account3 = new Registration("contains3", "testpass2");
        JsonNode accountNode1 = Json.toJson(account1);
        JsonNode accountNode2 = Json.toJson(account2);
        JsonNode accountNode3 = Json.toJson(account3);
        Json.userList.add(accountNode1);
        Json.userList.add(accountNode2);

        assertTrue(Json.userListContains(accountNode1));
        assertTrue(Json.userListContains(accountNode2));
        assertFalse(Json.userListContains(accountNode3));
    }

    @Test
    public void testUserListContainsEmpty() {
        Registration account1 = new Registration("contains700", "testpass1");
        JsonNode accountNode1 = Json.toJson(account1);
        Json.userList = Json.getDefaultObjectMapper().createArrayNode();

        assertFalse(Json.userListContains(accountNode1));
    }

    @Test
    public void testUserListContainsNullInput() {
        Registration account1 = new Registration("contains700", "testpass1");
        JsonNode accountNode1 = Json.toJson(account1);
        Json.userList = Json.getDefaultObjectMapper().createArrayNode();

        assertFalse(Json.userListContains(null));
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
            testCloset = Json.parseUserCloset("test");

        } catch (IOException e) {
            e.printStackTrace();
            fail("Exception shouldn't have been thrown");
        }

        assertTrue(testCloset.containsClothing("Levi Jeans"));
        assertEquals(testCloset.collectionSize(), 4);
    }

    @Test
    public void testParseUserStyleBoard() {
        StyleBoard testStyleBoard = new StyleBoard();
        try {
            testStyleBoard = Json.parseUserStyleBoard("test");

        } catch (IOException e) {
            e.printStackTrace();
            fail("Exception shouldn't have been thrown");
        }

        assertTrue(testStyleBoard.containsOutfit("3piece"));
        assertTrue(testStyleBoard.containsOutfit("2piece"));
        assertEquals(testStyleBoard.styleBoardSize(), 2);
    }

}