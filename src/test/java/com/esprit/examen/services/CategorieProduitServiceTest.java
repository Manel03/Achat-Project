package com.esprit.examen.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.esprit.examen.entities.CategorieProduit;
import com.esprit.examen.repositories.CategorieProduitRepository;

@SpringBootTest
public class CategorieProduitServiceTest {

    @Autowired
    private ICategorieProduitService categorieProduitService;

    @Autowired
    private CategorieProduitRepository categorieProduitRepository;

    @Test
    public void testRetrieveAllCategorieProduits() {
        CategorieProduit cp1 = new CategorieProduit(null, "Cat 1", "Description 1", null);
        CategorieProduit cp2 = new CategorieProduit(null, "Cat 2", "Description 2", null);
        categorieProduitService.addCategorieProduit(cp1);
        categorieProduitService.addCategorieProduit(cp2);
        List<CategorieProduit> result = categorieProduitService.retrieveAllCategorieProduits();
        assertEquals(2, result.size());
        assertEquals("Cat 1", result.get(0).getNom());
        assertEquals("Cat 2", result.get(1).getNom());
    }

    @Test
    public void testAddCategorieProduit() {
        CategorieProduit cp = new CategorieProduit(null, "Cat 1", "Description 1", null);
        CategorieProduit result = categorieProduitService.addCategorieProduit(cp);
        assertNotNull(result);
        assertEquals("Cat 1", result.getNom());
    }

    @Test
    public void testDeleteCategorieProduit() {
        CategorieProduit cp = new CategorieProduit(null, "Cat 1", "Description 1", null);
        CategorieProduit result = categorieProduitService.addCategorieProduit(cp);
        categorieProduitService.deleteCategorieProduit(result.getId());
        CategorieProduit deleted = categorieProduitService.retrieveCategorieProduit(result.getId());
        assertNull(deleted);
    }

    @Test
    public void testUpdateCategorieProduit() {
        CategorieProduit cp = new CategorieProduit(null, "Cat 1", "Description 1", null);
        CategorieProduit result = categorieProduitService.addCategorieProduit(cp);
        result.setNom("Updated Cat 1");
        CategorieProduit updated = categorieProduitService.updateCategorieProduit(result);
        assertEquals("Updated Cat 1", updated.getNom());
    }

    @Test
    public void testRetrieveCategorieProduit() {
        CategorieProduit cp = new CategorieProduit(null, "Cat 1", "Description 1", null);
        CategorieProduit result = categorieProduitService.addCategorieProduit(cp);
        CategorieProduit retrieved = categorieProduitService.retrieveCategorieProduit(result.getId());
        assertNotNull(retrieved);
        assertEquals("Cat 1", retrieved.getNom());
    }
}
