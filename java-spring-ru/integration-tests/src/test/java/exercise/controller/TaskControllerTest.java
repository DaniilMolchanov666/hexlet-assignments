package exercise.controller;

import exercise.exception.ResourceNotFoundException;
import org.junit.jupiter.api.*;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;
import org.instancio.Instancio;
import org.instancio.Select;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import java.util.HashMap;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.datafaker.Faker;
import exercise.repository.TaskRepository;
import exercise.model.Task;

@SpringBootTest
@AutoConfigureMockMvc
class ApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Faker faker;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TaskRepository taskRepository;

    @BeforeEach
    public void getNewTask() {
        Task t = generateTask();
        taskRepository.save(t);
    }

    @AfterEach
    public void deleteTasks() {
        taskRepository.deleteAll();
    }


    @Test
    public void testWelcomePage() throws Exception {
        var result = mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThat(body).contains("Welcome to Spring!");
    }

    @Test
    public void testIndex() throws Exception {
        var result = mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThatJson(body).isArray();
    }


    private Task generateTask() {
        return Instancio.of(Task.class)
                .ignore(Select.field(Task::getId))
                .supply(Select.field(Task::getTitle), () -> faker.lorem().word())
                .supply(Select.field(Task::getDescription), () -> faker.lorem().paragraph())
                .create();
    }

    @Test
    public void showTest() throws Exception {
        var task = taskRepository.findAll().get(0);
        var result = mockMvc.perform(get("/tasks/" + task.getId())).andReturn();
        var body = result.getResponse().getContentAsString();

        assertThatJson(body).isNotNull();
        assertThatJson(body).isEqualTo(om.writeValueAsString(task));

        mockMvc.perform(get("/tasks/" + 100)).andExpect(status().is(404));
    }

    @Test
    public void postTest() throws Exception {
        var newTask = generateTask();
        var request = (post("/tasks")
                .contentType(MediaType.APPLICATION_JSON))
                .content(om.writeValueAsString(newTask));
        mockMvc.perform(request).andExpect(status().isCreated());
    }

    @Test
    public void updateTest() throws Exception {
        var task = taskRepository.findAll()
                .stream()
                .findFirst()
                .get();

        String newTitle = faker.lorem().paragraph();
        task.setTitle(newTitle);

        var request = put("/tasks/" + task.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(task));

        mockMvc.perform(request)
                .andExpect(status().isOk());

        var updateTask = taskRepository.findByTitle(newTitle).get();

        assertThat(updateTask.getTitle()).isEqualTo(newTitle);

    }

    @Test
    public void deleteTest() throws Exception {
        var task = taskRepository.findAll()
                .stream()
                .findFirst()
                .get();

        mockMvc.perform(delete("/tasks/" + task.getId())).andExpect(status().isOk());

        assertThat(taskRepository.findAll().size()).isZero();
    }
}
