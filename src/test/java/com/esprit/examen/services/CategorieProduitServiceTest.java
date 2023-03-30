package com.esprit.examen.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import com.esprit.examen.entities.CategorieProduit;
import com.esprit.examen.entities.Produit;

@SpringBootTest
public class CategorieProduitServiceTest {

    @Autowired
    private ICategorieProduitService categorieProduitService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void setUp() {
        jdbcTemplate.update("DELETE FROM categorie_produit");
    }

    @Test
    public void testRetrieveAllCategorieProduits() {
        CategorieProduit cp1 = new CategorieProduit(null, "C001", "Categorie 1");
        CategorieProduit cp2 = new CategorieProduit(null, "C002", "Categorie 2");
        CategorieProduit result1 = categorieProduitService.addCategorieProduit(cp1);
        CategorieProduit result2 = categorieProduitService.addCategorieProduit(cp2);
        List<CategorieProduit> result = categorieProduitService.retrieveAllCategorieProduits();
        assertEquals(2, result.size());
        assertEquals("Categorie 1", result.get(0).getLibelleCategorieProduit());
        assertEquals("Categorie 2", result.get(1).getLibelleCategorieProduit());
    }

    @Test
    public void testAddCategorieProduit() {
        CategorieProduit cp1 = new CategorieProduit(null, "C001", "Categorie 1");
        CategorieProduit result = categorieProduitService.addCategorieProduit(cp1);
        assertNotNull(result);
        assertEquals("Categorie 1", result.getLibelleCategorieProduit());
    }

    @Test
    public void testDeleteCategorieProduit() {
        CategorieProduit cp1 = new CategorieProduit(null, "C001", "Categorie 1");
        CategorieProduit result2 = categorieProduitService.addCategorieProduit(cp1);
        categorieProduitService.deleteCategorieProduit(result2.getIdCategorieProduit());
        CategorieProduit result = categorieProduitService.retrieveCategorieProduit(result2.getIdCategorieProduit());
        assertNull(result);
    }

    @Test
    public void testUpdateCategorieProduit() {
        CategorieProduit cp1 = new CategorieProduit(null, "C001", "Categorie 1");
        CategorieProduit result = categorieProduitService.updateCategorieProduit(cp1);
        assertNotNull(result);
        assertEquals("Categorie 1", result.getLibelleCategorieProduit());
    }

    @Test
    public void testRetrieveCategorieProduit() {
        CategorieProduit cp1 = new CategorieProduit(null, "C001", "Categorie 1");
        CategorieProduit result2 = categorieProduitService.addCategorieProduit(cp1);
        CategorieProduit result = categorieProduitService.retrieveCategorieProduit(result2.getIdCategorieProduit());
        assertNotNull(result);
        assertEquals("Categorie 1", result.getLibelleCategorieProduit());
    }
}
