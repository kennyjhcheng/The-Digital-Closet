package persistence;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import model.Closet;

import java.io.IOException;

public class ClosetDeserializer extends StdDeserializer<Closet> {

    protected ClosetDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Closet deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws
            IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        Closet closet = new Closet();

        return null;
    }
}
