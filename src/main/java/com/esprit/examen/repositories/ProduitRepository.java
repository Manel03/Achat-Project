package com.esprit.examen.repositories;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.esprit.examen.entities.Produit;
import org.springframework.transaction.annotation.Transactional;
@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {

	
	
	
	
	
	
	
	
	
	
	/*@Transactional
	@Query("DELETE FROM Produit p where 1=1")
	public void deleteAllProduit();*/
}
