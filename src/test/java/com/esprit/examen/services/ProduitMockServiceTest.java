package com.esprit.examen.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.esprit.examen.entities.CategorieProduit;
import com.esprit.examen.entities.Produit;
import com.esprit.examen.entities.Stock;
import com.esprit.examen.repositories.ProduitRepository;
import lombok.extern.slf4j.Slf4j;
import org.mockito.Mock;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
@Slf4j
public class ProduitMockServiceTest {

    @Autowired
    private IProduitService produitService;

    @Mock
    private ProduitRepository produitRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        MockitoAnnotations.openMocks(this);
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void setUp1() {
    // Delete all entries in the table
     jdbcTemplate.update("DELETE FROM produit");
    }
    @Test
    public void testRetrieveAllProduits() {
        Produit p1 = new Produit(1L, "P001", "Produit 1", 10.0f, new Date(), null, null, null, null);
        Produit p2 = new Produit(2L, "P002", "Produit 2", 20.0f, new Date(), null, null, null, null);
        List<Produit> produits = new ArrayList<>();
        produits.add(p1);
        produits.add(p2);
        Produit result1 = produitService.addProduit(p1);
        Produit result2 = produitService.addProduit(p2);
        when(produitRepository.findAll()).thenReturn(produits);

        List<Produit> result = produitService.retrieveAllProduits();
        assertEquals(2, result.size());
        assertEquals("Produit 1", result.get(0).getLibelleProduit());
        assertEquals("Produit 2", result.get(1).getLibelleProduit());
        log.info(" with mock-produit1 : " +result.get(0).getLibelleProduit());
        log.info(" with mock-produit2 : " +result.get(1).getLibelleProduit());
    }
    
    @Test
    public void testAddProduit() {
        Produit p1 = new Produit(null, "P001", "Produit 1", 10.0f, new Date(), null, null, null, null);
        when(produitRepository.save(any())).thenReturn(p1);
        Produit result = produitService.addProduit(p1);
        assertNotNull(result);
        assertEquals("Produit 1", result.getLibelleProduit());
        log.info(" with mock-added-produit : " +result.getLibelleProduit());
    }

    @Test
    public void testDeleteProduit() {
        Produit p1 = new Produit(null, "P001", "Produit 1", 10.0f, new Date(), null, null, null, null);
        when(produitRepository.save(any())).thenReturn(p1);
        Produit result2 = produitService.addProduit(p1);
        produitService.deleteProduit(result2.getIdProduit());
        when(produitRepository.findById(result2.getIdProduit())).thenReturn(java.util.Optional.empty());
        Produit result = produitService.retrieveProduit(result2.getIdProduit());
        assertNull(result);
        log.info(" with mock-produit deleted : " +result2.getLibelleProduit());
    }



    @Test
    public void testUpdateProduit() {
        Produit p1 = new Produit(1L, "P001", "Produit 1", 10.0f, new Date(), null, null, null, null);
        when(produitRepository.save(any())).thenReturn(p1);
        Produit result = produitService.updateProduit(p1);
        assertNotNull(result);
        assertEquals("Produit 1", result.getLibelleProduit());
        log.info(" with mock-produit updated : " +result.getLibelleProduit());
    }
        @Test
    public void testRetrieveProduit() {
        Long id = 1L;
        Produit p1 = new Produit(null,"P005", "Produit 1", 10.0f, new Date(), null, null, null, null);
        Produit result1 = produitService.addProduit(p1);

        when(produitRepository.findById(result1.getIdProduit())).thenReturn(Optional.of(p1));
        Produit result = produitService.retrieveProduit(result1.getIdProduit());
        assertNotNull(result);
        assertEquals("Produit 1", result.getLibelleProduit());
        log.info(" with mock-produit retrived : " +result.getLibelleProduit());
        produitService.deleteProduit(result.getIdProduit());
    }
}