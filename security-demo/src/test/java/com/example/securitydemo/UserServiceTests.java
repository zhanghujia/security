package com.example.securitydemo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.Instant;
import java.util.Date;

@SpringBootTest
class UserServiceTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void queryWhenSuccessList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/list")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()")
                        .value(3));
    }

    @Test
    void queryWhenSuccessName() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/name")
                .param("userName", "admin")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void queryWhenSuccessId() throws Exception {
        String users = mockMvc.perform(MockMvcRequestBuilders.get("/user/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.userName")
                        .value("admin"))
                .andReturn().getResponse().getContentAsString();
        System.out.println(users);

    }

    @Test
    void queryWhenSuccessCondition() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/condition")
                .param("id", "1")
                .param("userName", "admin")
                .param("password", "123456")
                .param("age", "20")
                .param("page", "1")
                .param("size", "1")
                .param("sort", "id.asc")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void createWhenSuccess() throws Exception {
//        long nowTime = Instant.now().plusMillis(TimeUnit.HOURS.toMillis(8)).toEpochMilli(); UTC时间 + 8小时
        long nowTime = Instant.now().toEpochMilli();
        System.out.println(new Date().getTime());
        System.out.println(nowTime);
        System.out.println(System.currentTimeMillis());
        String content = "{\"userName\":\"admin\",\"age\":20,\"birthDay\":" + nowTime + "}";
        mockMvc.perform(MockMvcRequestBuilders.post("/user/insert")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id")
                        .value("1"));
    }

    @Test
    void updateWhenSuccess() throws Exception {
        long nowTime = Instant.now().toEpochMilli();
        String content = "{\"id\":1,\"userName\":\"admin\",\"password\":\"root\",\"age\":20,\"birthDay\":" + nowTime + "}";
        mockMvc.perform(MockMvcRequestBuilders.put("/user/update")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void deleteWhenSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/user/delete/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


}
