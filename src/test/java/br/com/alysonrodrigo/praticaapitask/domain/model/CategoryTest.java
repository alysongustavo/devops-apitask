package br.com.alysonrodrigo.praticaapitask.domain.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CategoryTest {

    @Test
    public void testar_objeto_criado(){
        Category category = new Category();
        category.setId(1L);
        category.setName("Test");
        category.setDescription("Test Description");

        Assertions.assertEquals(1L, category.getId());
        Assertions.assertEquals("Test", category.getName());
        Assertions.assertEquals("Test Description", category.getDescription());
    }
}
