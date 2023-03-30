package tn.esprit.rh.achat.services.SecteurActivite;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.MethodOrderer;

import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import tn.esprit.rh.achat.entities.SecteurActivite;
import tn.esprit.rh.achat.services.ISecteurActiviteService;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SecteurActiviteServiceTest {

    @Mock
    ISecteurActiviteService secteurActiviteServiceMock;

    @InjectMocks
    SecteurActiviteService secteurActiviteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @Order(1)
    public void testRetrieveAllSecteurActivite() {
        List<SecteurActivite> listSecteurActivite = new ArrayList<>();
        listSecteurActivite.add(new SecteurActivite("aa","aa"));
        when(secteurActiviteServiceMock.retrieveAllSecteurActivite()).thenReturn(listSecteurActivite);

        List<SecteurActivite> result = secteurActiviteService.retrieveAllSecteurActivite();
        assertEquals(1, result.size());
    }

    @Test
    @Order(2)
    public void testAddSecteurActivite() {
        SecteurActivite sa = new SecteurActivite("aa","aa");
        when(secteurActiviteServiceMock.addSecteurActivite(sa)).thenReturn(sa);

        SecteurActivite savedSecteur = secteurActiviteService.addSecteurActivite(sa);
        assertEquals(sa.getLibelleSecteurActivite(), savedSecteur.getLibelleSecteurActivite());
    }

    @Test
    @Order(3)
    public void testDeleteSecteurActivite() {
        Long id = 1L;
        when(secteurActiviteServiceMock.retrieveSecteurActivite(id)).thenReturn(null);

        secteurActiviteService.deleteSecteurActivite(id);
        assertNull(secteurActiviteService.retrieveSecteurActivite(id));
    }

}
