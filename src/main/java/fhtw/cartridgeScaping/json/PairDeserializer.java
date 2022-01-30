package fhtw.cartridgeScaping.json;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import javafx.util.Pair;

import java.io.IOException;

public class PairDeserializer extends StdDeserializer<Pair<Integer, Integer>> {
    public  PairDeserializer() {
        this(null);
    }

    protected PairDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Pair<Integer, Integer> deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JacksonException {
        Pair<Integer, Integer> pair = new Pair<>(
                ctxt.readTree(p).get("X").intValue(), ctxt.readTree(p).get("Y").intValue());
        return pair;
    }
}
