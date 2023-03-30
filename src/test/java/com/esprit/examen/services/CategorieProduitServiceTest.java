package com.esprit.examen.services;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.esprit.examen.entities.CategorieProduit;
import com.esprit.examen.repositories.CategorieProduitRepository;

@SpringBootTest
public class CategorieProduitServiceTest {

    @Autowired
    private CategorieProduitRepository categorieProduitRepository;

    private CategorieProduitServiceImpl categorieProduitService;

    @BeforeEach
    public void setUp() {
        categorieProduitService = new CategorieProduitServiceImpl(categorieProduitRepository);
    }

    @Test
    public void retrieveAllCategorieProduits_ShouldReturnAllCategorieProduits() {
        // Given
        CategorieProduit cp1 = new CategorieProduit("category1");
        CategorieProduit cp2 = new CategorieProduit("category2");
        categorieProduitRepository.saveAll(Arrays.asList(cp1, cp2));

        // When
        List<CategorieProduit> categories = categorieProduitService.retrieveAllCategorieProduits();

        // Then
        assertThat(categories).hasSize(2).contains(cp1, cp2);
    }

    @Test
    public void addCategorieProduit_ShouldAddCategorieProduit() {
        // Given
        CategorieProduit cp = new CategorieProduit("category1");

        // When
        CategorieProduit addedCp = categorieProduitService.addCategorieProduit(cp);

        // Then
        assertThat(addedCp.getId()).isNotNull();
        assertThat(addedCp.getNom()).isEqualTo(cp.getNom());
        assertThat(categorieProduitRepository.findById(addedCp.getId())).isPresent().get().isEqualTo(addedCp);
    }

    @Test
    public void deleteCategorieProduit_ShouldDeleteCategorieProduit() {
        // Given
        CategorieProduit cp = categorieProduitRepository.save(new CategorieProduit("category1"));

        // When
        categorieProduitService.deleteCategorieProduit(cp.getId());

        // Then
        assertThat(categorieProduitRepository.findById(cp.getId())).isEmpty();
    }

    @Test
    public void updateCategorieProduit_ShouldUpdateCategorieProduit() {
        // Given
        CategorieProduit cp = categorieProduitRepository.save(new CategorieProduit("category1"));
        cp.setNom("category2");

        // When
        CategorieProduit updatedCp = categorieProduitService.updateCategorieProduit(cp);

        // Then
        assertThat(updatedCp.getId()).isEqualTo(cp.getId());
        assertThat(updatedCp.getNom()).isEqualTo(cp.getNom());
        assertThat(categorieProduitRepository.findById(cp.getId())).isPresent().get().isEqualTo(updatedCp);
    }

    @Test
    public void retrieveCategorieProduit_ShouldRetrieveCategorieProduitById() {
        // Given
        CategorieProduit cp = categorieProduitRepository.save(new CategorieProduit("category1"));

        // When
        CategorieProduit retrievedCp = categorieProduitService.retrieveCategorieProduit(cp.getId());

        // Then
        assertThat(retrievedCp).isEqualTo(cp);
    }

}
