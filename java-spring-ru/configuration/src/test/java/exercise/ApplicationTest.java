package exercise;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class ApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper om;

    @Test
    public void testIndex() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk());
    }

    @Test
    public void testShow() throws Exception {
        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAdmins() throws Exception {
        String[] expected = {
            "Emmit Brundle",
            "Glynn Joinsey",
            "Sarina Crosi"
        };

        mockMvc.perform(get("/admins"))
                .andExpect(status().isOk())
                .andExpect(content().json(om.writeValueAsString(expected)));
    }
}
