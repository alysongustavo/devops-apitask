package br.com.alysonrodrigo.praticaapitask.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category {

    @Id
    @SequenceGenerator(name = "category_seq", sequenceName = "category_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "category_seq")
    private Long id;
    private String name;
    private String description;
}
