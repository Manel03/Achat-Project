package com.esprit.examen.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
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

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRetrieveAllCategorieProduits() {
        List<CategorieProduit> expectedCategorieProduits = new ArrayList<>();
        expectedCategorieProduits.add(new CategorieProduit(1L, "category 1"));
        expectedCategorieProduits.add(new CategorieProduit(2L, "category 2"));

        when(categorieProduitRepository.findAll()).thenReturn(expectedCategorieProduits);

        List<CategorieProduit> actualCategorieProduits = categorieProduitService.retrieveAllCategorieProduits();

        assertEquals(expectedCategorieProduits.size(), actualCategorieProduits.size());
        assertEquals(expectedCategorieProduits.get(0).getNom(), actualCategorieProduits.get(0).getNom());
        assertEquals(expectedCategorieProduits.get(1).getNom(), actualCategorieProduits.get(1).getNom());
    }

    @Test
    public void testAddCategorieProduit() {
        CategorieProduit expectedCategorieProduit = new CategorieProduit(1L, "category 1");

        when(categorieProduitRepository.save(expectedCategorieProduit)).thenReturn(expectedCategorieProduit);

        CategorieProduit actualCategorieProduit = categorieProduitService.addCategorieProduit(expectedCategorieProduit);

        assertEquals(expectedCategorieProduit.getNom(), actualCategorieProduit.getNom());
    }

    @Test
    public void testDeleteCategorieProduit() {
        Long idToDelete = 1L;

        categorieProduitService.deleteCategorieProduit(idToDelete);

        // Verify that the deleteById method of the repository is called once with the correct id
        verify(categorieProduitRepository, times(1)).deleteById(idToDelete);
    }

    @Test
    public void testUpdateCategorieProduit() {
        CategorieProduit expectedCategorieProduit = new CategorieProduit(1L, "category 1");

        when(categorieProduitRepository.save(expectedCategorieProduit)).thenReturn(expectedCategorieProduit);

        CategorieProduit actualCategorieProduit = categorieProduitService.updateCategorieProduit(expectedCategorieProduit);

        assertEquals(expectedCategorieProduit.getNom(), actualCategorieProduit.getNom());
    }

    @Test
    public void testRetrieveCategorieProduit() {
        Long idToRetrieve = 1L;
        CategorieProduit expectedCategorieProduit = new CategorieProduit(idToRetrieve, "category 1");

        when(categorieProduitRepository.findById(idToRetrieve)).thenReturn(Optional.of(expectedCategorieProduit));

        CategorieProduit actualCategorieProduit = categorieProduitService.retrieveCategorieProduit(idToRetrieve);

        assertEquals(expectedCategorieProduit.getNom(), actualCategorieProduit.getNom());
    }

}
