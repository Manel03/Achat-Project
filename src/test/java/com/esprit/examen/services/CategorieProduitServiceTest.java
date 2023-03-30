package com.esprit.examen.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.esprit.examen.entities.CategorieProduit;
import com.esprit.examen.repositories.CategorieProduitRepository;

@SpringBootTest
public class CategorieProduitServiceImplTest {
	
	@Mock
	private CategorieProduitRepository categorieProduitRepository;
	
	@InjectMocks
	private CategorieProduitServiceImpl categorieProduitService;
	
	@Test
	public void testRetrieveAllCategorieProduits() {
		List<CategorieProduit> categorieProduits = new ArrayList<CategorieProduit>();
		categorieProduits.add(new CategorieProduit(1L, "Categorie 1"));
		categorieProduits.add(new CategorieProduit(2L, "Categorie 2"));
		when(categorieProduitRepository.findAll()).thenReturn(categorieProduits);
		
		List<CategorieProduit> result = categorieProduitService.retrieveAllCategorieProduits();
		
		assertEquals(2, result.size());
		assertEquals("Categorie 1", result.get(0).getLibelleCategorie());
		assertEquals("Categorie 2", result.get(1).getLibelleCategorie());
	}
	
	@Test
	public void testAddCategorieProduit() {
		CategorieProduit categorieProduit = new CategorieProduit(null, "Categorie 1");
		when(categorieProduitRepository.save(categorieProduit)).thenReturn(categorieProduit);
		
		CategorieProduit result = categorieProduitService.addCategorieProduit(categorieProduit);
		
		assertNotNull(result);
		assertEquals("Categorie 1", result.getLibelleCategorie());
	}
	
	@Test
	public void testDeleteCategorieProduit() {
		Long id = 1L;
		
		categorieProduitService.deleteCategorieProduit(id);
		
		assertNull(categorieProduitRepository.findById(id).orElse(null));
	}
	
	@Test
	public void testUpdateCategorieProduit() {
		CategorieProduit categorieProduit = new CategorieProduit(1L, "Categorie 1");
		when(categorieProduitRepository.save(categorieProduit)).thenReturn(categorieProduit);
		
		CategorieProduit result = categorieProduitService.updateCategorieProduit(categorieProduit);
		
		assertNotNull(result);
		assertEquals("Categorie 1", result.getLibelleCategorie());
	}
	
}
