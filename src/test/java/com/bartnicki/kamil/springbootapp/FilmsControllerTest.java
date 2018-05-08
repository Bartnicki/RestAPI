package com.bartnicki.kamil.springbootapp;

import com.bartnicki.kamil.springbootapp.model.Films;
import com.bartnicki.kamil.springbootapp.model.FilmJpaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootAppApplication.class)
@WebAppConfiguration
public class FilmsControllerTest {

    private MockMvc mockMvc;


    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private FilmJpaRepository filmJpaRepository;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        this.filmJpaRepository.deleteAllInBatch();

        Films film = new Films("Shrek", "Film o ogrze", 2000);
        this.filmJpaRepository.save(film);

    }

    @Test
    public void testFindAll() throws Exception {
        mockMvc.perform(get("/films/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(filmJpaRepository.findAll().size())));

    }

    @Test
    public void testFindByName() throws Exception {

        mockMvc.perform(get("/films/Shrek")
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", Matchers.is("Shrek")))
                .andExpect(jsonPath("$.description", Matchers.is("Film o ogrze")))
                .andExpect(jsonPath("$.productionYear", Matchers.is(2000)));
    }

    @Test
    public void testAddNewFilm() throws Exception {
        mockMvc.perform(post("/films/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new Films("Killer", "Killerowy", 1999)))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


}
