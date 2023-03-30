package com.esprit.examen.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.esprit.examen.entities.CategorieProduit;
import com.esprit.examen.repositories.CategorieProduitRepository;

public class CategorieProduitServiceImplTest {

    @Mock
    private CategorieProduitRepository categorieProduitRepository;

    @InjectMocks
    private CategorieProduitServiceImpl categorieProduitService;

    private CategorieProduit categorieProduit1;
    private CategorieProduit categorieProduit2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        categorieProduit1 = new CategorieProduit();
        categorieProduit1.setId(1L);
        categorieProduit1.setNom("Category 1");

        categorieProduit2 = new CategorieProduit();
        categorieProduit2.setId(2L);
        categorieProduit2.setNom("Category 2");
    }

    @Test
    public void retrieveAllCategorieProduitsTest() {
        List<CategorieProduit> categorieProduits = new ArrayList<>();
        categorieProduits.add(categorieProduit1);
        categorieProduits.add(categorieProduit2);

        when(categorieProduitRepository.findAll()).thenReturn(categorieProduits);

        List<CategorieProduit> result = categorieProduitService.retrieveAllCategorieProduits();

        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getNom()).isEqualTo("Category 1");
        assertThat(result.get(1).getNom()).isEqualTo("Category 2");
    }

    @Test
    public void addCategorieProduitTest() {
        when(categorieProduitRepository.save(categorieProduit1)).thenReturn(categorieProduit1);

        CategorieProduit result = categorieProduitService.addCategorieProduit(categorieProduit1);

        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getNom()).isEqualTo("Category 1");
    }

    @Test
    public void deleteCategorieProduitTest() {
        categorieProduitService.deleteCategorieProduit(1L);

        // Verify that deleteById was called with the correct id
        verify(categorieProduitRepository, times(1)).deleteById(1L);
    }

    @Test
    public void updateCategorieProduitTest() {
        when(categorieProduitRepository.save(categorieProduit1)).thenReturn(categorieProduit1);

        CategorieProduit result = categorieProduitService.updateCategorieProduit(categorieProduit1);

        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getNom()).isEqualTo("Category 1");
    }

    @Test
    public void retrieveCategorieProduitTest() {
        when(categorieProduitRepository.findById(1L)).thenReturn(Optional.of(categorieProduit1));

        CategorieProduit result = categorieProduitService.retrieveCategorieProduit(1L);

        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getNom()).isEqualTo("Category 1");
    }
}

