package exercise;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

class App {

    public static void save(Path path, Car car) throws IOException {
        String carString = car.serialize();
        ObjectMapper o = new ObjectMapper(new JsonFactory());
        String path1 = path.toAbsolutePath().normalize().toString();
        String cars = car.serialize();

        try (FileWriter w = new FileWriter(path1)) {
            w.write(cars);
        } catch(IOException e) {
            System.out.println("Not found file!");
        }
    }

    public static Car extract(Path path) throws IOException {
        StringBuilder s = new StringBuilder();
        try (Scanner scanner = new Scanner(new File(path.toAbsolutePath().normalize().toString()))) {
            while(scanner.hasNext()) {
                s.append(scanner.next());
            }
        } catch(IOException e) {
            System.out.println("Not found file!");
        }
        return Car.unserialize(s.toString());
    }

    public static void main(String[] args) throws IOException {
        Car c = Car.builder()
                .id(123)
                .brand("BMV")
                .color("Black")
                .model("X9")
                .owner(new User(1, "1", "1", 1)).build();

        System.out.println(c.serialize());
        Car c2 = Car.unserialize(c.serialize());
        System.out.println(c2);

        Path filePath = Paths.get("src/Car.json");

        save(filePath, c);
        System.out.println(extract(filePath));
    }
}
