package com.esprit.examen.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import com.esprit.examen.entities.Operateur;
import com.esprit.examen.repositories.OperateurRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
public class OperateurTest {

    @Autowired
    private IOperateurService operateurService;

    @Autowired
    private OperateurRepository operateurRepository;

    List<Operateur> operateurList = new ArrayList<Operateur>() {

        {
            add(new Operateur(null, "123456789", "Biscuit", "dd", null));
            add(new Operateur(null, "55522233", "Javel", "", null));
            add(new Operateur(null, "456897321", "Tv", "456", null));
        }};

    @Test
    @Order(2)
    public void testRetrieveAllOp() {
        Operateur p1 = new Operateur(17511L, "Global", "Fournissuer", "fourniss1",null);
        Operateur p2 = new Operateur(287452L, "Global", "Fournissuer", "fourniss1",null);
        Operateur result1 = operateurService.addOperateur(p1);
        Operateur result2 = operateurService.addOperateur(p2);
        List<Operateur> result = operateurService.retrieveAllOperateurs();
        assertEquals(2, result.size());
        assertEquals("Global", result.get(0).getNom());
        assertEquals("Global", result.get(1).getNom());
        log.info(" operateur 1 : " +result.get(0));
        log.info(" operateur2 : " +result.get(1));
    }

    @Test
    public void testAddOperateur() {
        Operateur p1 = new Operateur(17511L, "opertaeur 1", "Ope2", "test1",null);
        //when(produitRepository.save(any())).thenReturn(p1);
        Operateur result = operateurService.addOperateur(p1);
        assertNotNull(result);
        assertEquals("opertaeur 1", result.getNom());
        log.info(" produit : " +result);
    }

    @Test
    @Order(1)
    public void testDeleteAllOperateur() {
        Operateur p1 = new Operateur(1L, "to be deleted", "F7", "test7",  null);
        //Operateur p1 = new Operateur() ;
        //when(produitRepository.save(any())).thenReturn(p1);
        Operateur result2 = operateurService.addOperateur(p1);
        List<Operateur> lOpera= operateurService.retrieveAllOperateurs();
        lOpera.forEach(op -> System.out.println(op.getNom()));
        lOpera.forEach(op -> { operateurService.deleteOperateur(op.getIdOperateur());
            log.info(" The deleted operateurs are : " +op.getNom());
        });

    }
    @Test
    public void testDeleteAllO() {
        operateurList.forEach(op -> operateurService.addOperateur(op));

        operateurList.forEach(op -> System.out.println(op.getNom()));
        operateurList.forEach(op -> { operateurService.deleteOperateur(op.getIdOperateur());
            log.info(" The deleted operateurs are : " +op.getNom());
        });

    }

    @Test
    public void testDeleteOperateur() {
        Operateur p1 = new Operateur(null, "P001", "Produit 1", "10.0f",null);
        Operateur result2 = operateurService.addOperateur(p1);
        operateurService.deleteOperateur(result2.getIdOperateur());
        Operateur result = operateurService.retrieveOperateur(result2.getIdOperateur());
        assertNull(result);
        log.info(" Operateur supprimé : " +result2.getNom());
    }



    @Test
    public void testUpdateOperateur() {
        Operateur p1 = new Operateur(78L, "F8", "f8 1", "test8", null);

        Operateur result = operateurService.updateOperateur(p1);
        assertNotNull(result);
        assertEquals("F8", result.getNom());
        log.info(" The updated Operateur is : " +result);
    }

    @Test
    public void testRetrieveOperateur() {
        Operateur p1 = new Operateur(null,"O 9", "O9", "test9", null);
        Operateur result2 = operateurService.addOperateur(p1);
        log.info(" result2 : " +result2.getIdOperateur());

        Operateur result = operateurService.retrieveOperateur(result2.getIdOperateur());
        System.out.println(result.getNom()+"******************");
        assertNotNull(result);
        assertEquals("O 9", result.getNom());
        log.info(" opertauer retrived : " +result);
        operateurService.deleteOperateur(result.getIdOperateur());
    }
}

