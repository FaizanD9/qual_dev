package com.example.demo.data;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class VoitureTest {

    private Voiture voiture;


    @Test
    void creerVoiture(){
        Voiture v = new Voiture("Ferrari", 2000);
        v.setPrix(3000);
        assertEquals(3000, v.getPrix());
    }

    @BeforeEach
    public void setUp() {
        // Avant chaque test, on crée notre voiture
        voiture = new Voiture("Ferrari", 100000);
    }
    
    @Test
    public void testGetMarque() {
        assertEquals("Ferrari", voiture.getMarque());
    }

    @Test
    public void testSetMarque() {
        voiture.setMarque("Porsche");
        assertEquals("Porsche", voiture.getMarque());
    }

    @Test
    public void testGetPrix() {
        assertEquals(100000, voiture.getPrix());
    }

    @Test
    public void testSetPrix() {
        voiture.setPrix(120000);
        assertEquals(120000, voiture.getPrix());
    }
    
}
