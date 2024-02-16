package exercise.components;

import exercise.model.Task;
import exercise.model.User;
import exercise.repository.TaskRepository;
import exercise.repository.UserRepository;
import lombok.AllArgsConstructor;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

@Component
@AllArgsConstructor
public class AddData implements ApplicationRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        var faker = new Faker();
        User u = new User();
        u.setName(faker.name().firstName());
        u.setEmail(faker.internet().emailAddress());
        u.setCreatedAt(LocalDate.now());

        IntStream.range(1, 10).forEach(i -> {
            var task = new Task();
            task.setTitle(faker.lorem().paragraph(1));
            task.setDescription(faker.lorem().paragraph());
            task.setCreatedAt(LocalDate.now());
            task.setUpdatedAt(LocalDate.now());
            taskRepository.save(task);
        });

        List<Task> listOfTasks = new ArrayList<>();

        LongStream.range(1, new Random().nextLong(1, 3)).forEach(i -> {
            var task = taskRepository.findById(i).get();
            listOfTasks.add(task);
        });

        u.setTasks(listOfTasks);
        userRepository.save(u);
    }
}
