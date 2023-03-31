package com.esprit.examen.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.esprit.examen.entities.SecteurActivite;
import com.esprit.examen.repositories.SecteurActiviteRepository;

@SpringBootTest
public class SecteurActiviteServicesImplTest {

    @Mock
    private SecteurActiviteRepository secteurActiviteRepository;

    @InjectMocks
    private SecteurActiviteServiceImpl secteurActiviteService;

    @Test
    public void testRetrieveAllSecteurActivite() {
        // arrange
        SecteurActivite sa1 = new SecteurActivite(null,"secteur1","",null);
        SecteurActivite sa2 = new SecteurActivite(null,"secteur2","",null);
        when(secteurActiviteRepository.findAll()).thenReturn(Arrays.asList(sa1, sa2));

        // act
        List<SecteurActivite> secteurs = secteurActiviteService.retrieveAllSecteurActivite();

        // assert
        assertThat(secteurs).hasSize(2);
        assertThat(secteurs).contains(sa1, sa2);
    }

    @Test
    public void testAddSecteurActivite() {
        // arrange
        SecteurActivite sa = new SecteurActivite(null,"secteur test","",null);
        when(secteurActiviteRepository.save(sa)).thenReturn(sa);

        // act
        SecteurActivite savedSa = secteurActiviteService.addSecteurActivite(sa);

        // assert
        assertThat(savedSa).isEqualTo(sa);
    }

    @Test
    public void testDeleteSecteurActivite() {
        List<SecteurActivite> lSectAct= secteurActiviteService.retrieveAllSecteurActivite();
        lSectAct.forEach(sa -> System.out.println(sa.getLibelleSecteurActivite()));
        lSectAct.forEach(sa -> {
            secteurActiviteService.deleteSecteurActivite(sa.getIdSecteurActivite());
            System.out.println("secteur : " + sa.getLibelleSecteurActivite() + " est supprimé");
        });
    }

    @Test
    public void testUpdateSecteurActivite() {
        // arrange
        SecteurActivite sa = new SecteurActivite(null,"secteur test","",null);
        when(secteurActiviteRepository.save(sa)).thenReturn(sa);

        // act
        SecteurActivite updatedSa = secteurActiviteService.updateSecteurActivite(sa);

        // assert
        assertThat(updatedSa).isEqualTo(sa);
    }

    @Test
    public void testRetrieveSecteurActivite() {
        // arrange
        Long id = 1L;
        SecteurActivite sa = new SecteurActivite(null,"secteur test","",null);
        when(secteurActiviteRepository.findById(id)).thenReturn(Optional.of(sa));

        // act
        SecteurActivite retrievedSa = secteurActiviteService.retrieveSecteurActivite(id);

        // assert
        assertThat(retrievedSa).isEqualTo(sa);
    }

}
