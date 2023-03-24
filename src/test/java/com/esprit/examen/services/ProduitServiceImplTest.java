package com.esprit.examen.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.esprit.examen.entities.CategorieProduit;
import com.esprit.examen.entities.Produit;
import com.esprit.examen.entities.Stock;
import com.esprit.examen.repositories.ProduitRepository;

@SpringBootTest
public class ProduitServiceImplTest {

    @Autowired
    private IProduitService produitService;

    @Autowired
    private ProduitRepository produitRepository;

    @Test
    public void testRetrieveAllProduits() {
        Produit p1 = new Produit(1L, "P001", "Produit 1", 10.0f, new Date(), null, null, null, null);
        Produit p2 = new Produit(2L, "P002", "Produit 2", 20.0f, new Date(), null, null, null, null);
        List<Produit> produits = new ArrayList<>();
        produits.add(p1);
        produits.add(p2);
        when(produitRepository.findAll()).thenReturn(produits);
        List<Produit> result = produitService.retrieveAllProduits();
        assertEquals(2, result.size());
        assertEquals("Produit 1", result.get(0).getLibelleProduit());
        assertEquals("Produit 2", result.get(1).getLibelleProduit());
    }

    @Test
    public void testAddProduit() {
        Produit p1 = new Produit(null, "P001", "Produit 1", 10.0f, new Date(), null, null, null, null);
        when(produitRepository.save(any())).thenReturn(p1);
        Produit result = produitService.addProduit(p1);
        assertNotNull(result);
        assertEquals("Produit 1", result.getLibelleProduit());
    }

    @Test
    public void testDeleteProduit() {
        Long id = 1L;
        produitService.deleteProduit(id);
        verify(produitRepository, times(1)).deleteById(id);
    }

    @Test
    public void testUpdateProduit() {
        Produit p1 = new Produit(1L, "P001", "Produit 1", 10.0f, new Date(), null, null, null, null);
        when(produitRepository.save(any())).thenReturn(p1);
        Produit result = produitService.updateProduit(p1);
        assertNotNull(result);
        assertEquals("Produit 1", result.getLibelleProduit());
    }

    @Test
    public void testRetrieveProduit() {
        Long id = 1L;
        Produit p1 = new Produit(id, "P001", "Produit 1", 10.0f, new Date(), null, null, null, null);
        when(produitRepository.findById(id)).thenReturn(java.util.Optional.of(p1));
        Produit result = produitService.retrieveProduit(id);
        assertNotNull(result);
        assertEquals("Produit 1", result.getLibelleProduit());
    }

}