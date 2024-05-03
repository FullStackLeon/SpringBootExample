package example.one;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerByMockMvcTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    void getUserTest() {
        try {
            this.mockMvc.perform(
                    MockMvcRequestBuilders.get("/user/{id}", 1)
                                    .accept(MediaType.APPLICATION_JSON)
            ).andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.data.id").value(1))
                    .andDo(MockMvcResultHandlers.print());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void addUser() {
        try {
            String userInfo = "{\"name\": \"Jack\",\"addr\": \"Berlin\"}";
            this.mockMvc.perform(
                    MockMvcRequestBuilders.post("/user/add")
                                   .contentType(MediaType.APPLICATION_JSON)
                                   .accept(MediaType.APPLICATION_JSON)
                                   .content(userInfo)
            ).andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.data['6']").exists())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.data['6'].id").value(6))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.data['6'].name").value("Jack"))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.data['6'].addr").value("Berlin"))
                   .andDo(MockMvcResultHandlers.print());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void updateUser() {
        try {
            this.mockMvc.perform(
                    MockMvcRequestBuilders.put("/user/update")
                                   .contentType(MediaType.APPLICATION_JSON)
                                   .accept(MediaType.APPLICATION_JSON)
                                   .content("{\"id\":1,\"name\":\"Jack\",\"addr\":\"Berlin\"}")
            ).andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.data['1']").exists())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.data['1'].id").value(1))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.data['1'].name").value("Jack"))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.data['1'].addr").value("Berlin"))
                   .andDo(MockMvcResultHandlers.print());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void deleteUser() {
        try {
            this.mockMvc.perform(
                    MockMvcRequestBuilders.delete("/user/delete/{id}", 1)
                                   .accept(MediaType.APPLICATION_JSON)
            ).andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.data['1']").doesNotExist())
                   .andDo(MockMvcResultHandlers.print());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
