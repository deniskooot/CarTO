package com.github.Denis.converter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.Duration;

/**
 * Class for Duration-JSON convert. Hibernate must know how to do it for validation works.
 *
 */
public class DurationJsonConverter {

    public static class DurationSerializer extends JsonSerializer<Duration> {
        @Override
        public void serialize(Duration duration, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            gen.writeNumber(duration.toDays()); // Пример: "PT10S"
        }
    }

    public static class DurationDeserializer extends JsonDeserializer<Duration> {
        @Override
        public Duration deserialize(JsonParser p, com.fasterxml.jackson.databind.DeserializationContext ctxt) throws IOException {
            return Duration.ofDays(p.getLongValue()); // Пример: "PT10S"
        }
    }

}
