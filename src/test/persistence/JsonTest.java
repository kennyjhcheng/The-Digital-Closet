package persistence;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import model.Closet;
import persistence.pojo.AuthorPOJO;
import persistence.pojo.BookPOJO;
import persistence.pojo.DayPOJO;
import persistence.pojo.SimpleTestCaseJsonPOJO;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class JsonTest {

    private String simpleTestCaseJsonSource = "{ \n" +
            "  \"title\": \"Coder From Scratch\",\n" +
            "  \"author\": \"Rui\"\n" +
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

    @Test
    public void parse() throws JsonProcessingException {

        JsonNode node = Json.parse(simpleTestCaseJsonSource);
        assertEquals(node.get("title").asText(), "Coder From Scratch");
    }

    @Test
    public void fromJson() throws JsonProcessingException {

        JsonNode node = Json.parse(simpleTestCaseJsonSource);
        SimpleTestCaseJsonPOJO pojo = Json.fromJson(node, SimpleTestCaseJsonPOJO.class);

        assertEquals(pojo.getTitle(), "Coder From Scratch");

    }

    @Test
    public void toJson() {
        SimpleTestCaseJsonPOJO pojo = new SimpleTestCaseJsonPOJO();
        pojo.setTitle("Testing 123");

        JsonNode node = Json.toJson(pojo);

        assertEquals(node.get("title").asText(), "Testing 123");
    }

    @Test
    public void stringify() throws JsonProcessingException {
        SimpleTestCaseJsonPOJO pojo = new SimpleTestCaseJsonPOJO();
        pojo.setTitle("Testing 123");

        JsonNode node = Json.toJson(pojo);

        System.out.println(Json.stringify(node));
        System.out.println(Json.prettyPrint(node));

    }

    @Test
    public void dayTestScenario1() throws JsonProcessingException {

        JsonNode node = Json.parse(dayScenario1);
        DayPOJO pojo = Json.fromJson(node, DayPOJO.class);

        assertEquals("2019-12-25", pojo.getDate().toString());
    }

    @Test
    public void authorBookScenario1() throws JsonProcessingException {

        JsonNode node = Json.parse(authorBookScenario);
        AuthorPOJO pojo = Json.fromJson(node, AuthorPOJO.class);

        System.out.println("Author : " + pojo.getAuthorName());
        for (BookPOJO b : pojo.getBooks()) {
            System.out.println("Book : " + b.getTitle());
            System.out.println("Is In Print? : " + b.getPublishDate());
            System.out.println("Date : " + b.getPublishDate());
        }
    }

    @Test
    public void testParseUserCloset() {
        Closet testCloset = new Closet();
        try {
            testCloset = Json.parseUserCloset("test");
        } catch (IOException e) {
            fail("Exception shouldn't have been thrown");
        }

        assertTrue(testCloset.containsClothing("korea"));
        assertEquals(testCloset.getNumberOfClothing(), 1);
    }

}