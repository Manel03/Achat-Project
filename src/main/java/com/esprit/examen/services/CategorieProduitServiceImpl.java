package com.esprit.examen.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

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

	@InjectMocks
	private CategorieProduitServiceImpl categorieProduitService;

	@Mock
	private CategorieProduitRepository categorieProduitRepository;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testRetrieveAllCategorieProduits() {
		List<CategorieProduit> expectedCategorieProduits = new ArrayList<>();
		expectedCategorieProduits.add(new CategorieProduit(1L, "TestCategory1"));
		expectedCategorieProduits.add(new CategorieProduit(2L, "TestCategory2"));

		doReturn(expectedCategorieProduits).when(categorieProduitRepository).findAll();

		List<CategorieProduit> actualCategorieProduits = categorieProduitService.retrieveAllCategorieProduits();

		assertEquals(expectedCategorieProduits, actualCategorieProduits);
	}

	@Test
	void testAddCategorieProduit() {
		CategorieProduit expectedCategorieProduit = new CategorieProduit(1L, "TestCategory1");

		doAnswer(invocation -> {
			Object[] args = invocation.getArguments();
			CategorieProduit cp = (CategorieProduit) args[0];
			cp.setId(1L);
			return cp;
		}).when(categorieProduitRepository).save(any(CategorieProduit.class));

		CategorieProduit actualCategorieProduit = categorieProduitService.addCategorieProduit(new CategorieProduit(null, "TestCategory1"));

		assertEquals(expectedCategorieProduit, actualCategorieProduit);
	}

	@Test
	void testDeleteCategorieProduit() {
		Long expectedId = 1L;

		doNothing().when(categorieProduitRepository).deleteById(expectedId);

		categorieProduitService.deleteCategorieProduit(expectedId);

		assertNotNull(categorieProduitRepository.findById(expectedId).orElse(null));
	}

	@Test
	void testUpdateCategorieProduit() {
		CategorieProduit expectedCategorieProduit = new CategorieProduit(1L, "TestCategory2");

		doReturn(expectedCategorieProduit).when(categorieProduitRepository).save(any(CategorieProduit.class));

		CategorieProduit actualCategorieProduit = categorieProduitService.updateCategorieProduit(expectedCategorieProduit);

		assertEquals(expectedCategorieProduit, actualCategorieProduit);
	}

	@Test
	void testRetrieveCategorieProduit() {
		Long expectedId = 1L;
		CategorieProduit expectedCategorieProduit = new CategorieProduit(expectedId, "TestCategory1");

		doReturn(Optional.of(expectedCategorieProduit)).when(categorieProduitRepository).findById(expectedId);

		CategorieProduit actualCategorieProduit = categorieProduitService.retrieveCategorieProduit(expectedId);

		assertEquals(expectedCategorieProduit, actualCategorieProduit);
	}
	
}

