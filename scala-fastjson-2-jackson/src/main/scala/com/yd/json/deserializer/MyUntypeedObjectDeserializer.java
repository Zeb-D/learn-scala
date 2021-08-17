package com.yd.json.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.std.UntypedObjectDeserializer;
import com.yd.json.JSONObject;

import java.io.IOException;

public class MyUntypeedObjectDeserializer extends UntypedObjectDeserializer {

    public MyUntypeedObjectDeserializer(JavaType listType, JavaType mapType) {
        super(listType, mapType);
    }

    @SuppressWarnings("unchecked")
    public MyUntypeedObjectDeserializer(UntypedObjectDeserializer base,
                                        JsonDeserializer<?> mapDeser,
                                        JsonDeserializer<?> listDeser,
                                        JsonDeserializer<?> stringDeser,
                                        JsonDeserializer<?> numberDeser) {
        super(base, mapDeser, listDeser, stringDeser, numberDeser);
    }

    /**
     * @since 2.9
     */
    protected MyUntypeedObjectDeserializer(UntypedObjectDeserializer base, boolean nonMerging) {
        super(base, nonMerging);
    }

    @Override
    protected Object mapObject(JsonParser p, DeserializationContext ctxt) throws IOException {
        String key1;

        JsonToken t = p.getCurrentToken();

        if (t == JsonToken.START_OBJECT) {
            key1 = p.nextFieldName();
        } else if (t == JsonToken.FIELD_NAME) {
            key1 = p.getCurrentName();
        } else {
            if (t != JsonToken.END_OBJECT) {
                return ctxt.handleUnexpectedToken(handledType(), p);
            }
            key1 = null;
        }
        if (key1 == null) {
            // empty map might work; but caller may want to modify... so better just give small modifiable
            return new JSONObject(2);
        }
        // minor optimization; let's handle 1 and 2 entry cases separately
        // 24-Mar-2015, tatu: Ideally, could use one of 'nextXxx()' methods, but for
        //   that we'd need new method(s) in JsonDeserializer. So not quite yet.
        p.nextToken();
        Object value1 = deserialize(p, ctxt);

        String key2 = p.nextFieldName();
        if (key2 == null) { // has to be END_OBJECT, then
            // single entry; but we want modifiable
            JSONObject result = new JSONObject(2);
            result.put(key1, value1);
            return result;
        }
        p.nextToken();
        Object value2 = deserialize(p, ctxt);

        String key = p.nextFieldName();

        if (key == null) {
            JSONObject result = new JSONObject(4);
            result.put(key1, value1);
            result.put(key2, value2);
            return result;
        }
        // And then the general case; default map size is 16
        JSONObject result = new JSONObject();
        result.put(key1, value1);
        result.put(key2, value2);

        do {
            p.nextToken();
            result.put(key, deserialize(p, ctxt));
        } while ((key = p.nextFieldName()) != null);
        return result;
    }
}

