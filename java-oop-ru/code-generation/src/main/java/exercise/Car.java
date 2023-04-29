package exercise;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

@Getter
@AllArgsConstructor
@Builder
@ToString
@Value
class Car {
    int id;
    String brand;
    String model;
    String color;
    User owner;

    public String serialize() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper(new JsonFactory());
        String s = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
        return s;
    }

    public static Car unserialize(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper(new JsonFactory());
        return mapper.readValue(json, Car.class);
    }
}
