package com.pragma.scheduleassessment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragma.scheduleassessment.dto.SchedulingRequest;
import lombok.var;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@AutoConfigureMockMvc
class ChapterCalendarControllerTest {
    @Autowired
    private  MockMvc mockMvc ;
    private final ObjectMapper mapper = new ObjectMapper();
    @Test
    void scheduleEventOk() throws Exception {
        var request = new SchedulingRequest(4L,"Java","oscar.alvaradoz@pragma.edu.co");
        mockMvc.perform(MockMvcRequestBuilders
                .post("/")
                .content(mapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    void scheduleEventWhitChapterIdThatDoesNotExistInDB() throws Exception {
        var request = new SchedulingRequest(1L,"Java","oscar.alvaradoz@pragma.edu.co");
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/")
                        .content(mapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message",is("The chapterId and/or specialty do not correspond to values stored in the database")));
    }
    @Test
    void testExampleEndpoint() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }
}