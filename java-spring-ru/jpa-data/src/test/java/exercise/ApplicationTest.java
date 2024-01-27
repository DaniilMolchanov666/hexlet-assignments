package exercise;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;

import exercise.repository.PersonRepository;
import exercise.model.Person;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private PersonRepository personRepository;

    private Person testPerson;

    @BeforeEach
    public void setUp() {
        testPerson = new Person();
        personRepository.save(testPerson);
    }

    @Test
    public void testWelcomePage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    public void testIndex() throws Exception {
        var result = mockMvc.perform(get("/people"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThatJson(body).isArray();
    }

    @Test
    public void testShow() throws Exception {
        mockMvc.perform(get("/people/" + testPerson.getId()))
                .andExpect(status().isOk())
                .andExpect(content().json(om.writeValueAsString(testPerson)));
    }

    @Test
    public void testCreate() throws Exception {
        Map person = Map.of(
            "firstName", "test",
            "lastName", "user"
        );

        var request = post("/people")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(person));

        mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(content().json(om.writeValueAsString(person)));

        assertThat(personRepository.findAll()).hasSize(2);
    }

    @Test
    public void testDelete() throws Exception {

        mockMvc.perform(delete("/people/" + testPerson.getId()))
                .andExpect(status().isOk());

        assertThat(personRepository.findAll()).isEmpty();
    }
}
