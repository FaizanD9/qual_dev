package com.example.demo.service;

import com.example.demo.data.Voiture;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class StatistiqueTests {

    @MockBean
    StatistiqueImpl statistiqueImpl;

    @Test
    public void testPrixMoyenSimule() {
        Echantillon echantillonFictif = new Echantillon(3, 25000);
        
        when(statistiqueImpl.prixMoyen()).thenReturn(echantillonFictif);

        Echantillon resultat = statistiqueImpl.prixMoyen();

        assertNotNull(resultat, "L'échantillon ne devrait pas être nul");
        assertEquals(3, resultat.getNombreDeVoitures(), "Le nombre de voitures doit être de 3");
        assertEquals(25000, resultat.getPrixMoyen(), "Le prix moyen doit être de 25000");

        verify(statistiqueImpl, times(1)).prixMoyen();
    }
}