package br.com.alysonrodrigo.praticaapitask.controller;

import br.com.alysonrodrigo.praticaapitask.domain.model.Category;
import br.com.alysonrodrigo.praticaapitask.domain.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CategoryService categoryService;

    @Test
    @WithMockUser(username = "user", roles = {"ADMIN"})
    public void testGetAllAuthenticated() throws Exception {

        Category category = new Category(1L, "Technology", "Tech-related");

        Mockito.when(categoryService.findAll()).thenReturn(Collections.singletonList(category));

        mockMvc.perform(MockMvcRequestBuilders.get("/categories")
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Technology"));;
    }

    @Test
    void testGetAllCategoriesUnauthorized() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/categories")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }
}
