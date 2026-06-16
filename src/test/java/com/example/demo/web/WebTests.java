package com.example.demo.web;

import com.example.demo.data.Voiture;
import com.example.demo.service.Echantillon;
import com.example.demo.service.StatistiqueImpl;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class WebTests {

    @MockBean
    StatistiqueImpl statistiqueImpl;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testGetStatistiques() throws Exception {
        Echantillon echantillonSimule = new Echantillon(2, 15000);
        when(statistiqueImpl.prixMoyen()).thenReturn(echantillonSimule);

        mockMvc.perform(get("/statistique"))
                .andExpect(status().isOk()) 
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)) 
                .andExpect(jsonPath("$.nombreDeVoitures").value(2)) 
                .andExpect(jsonPath("$.prixMoyen").value(15000))
                .andDo(print()); 
    }

    @Test
    public void testCreerVoiture() throws Exception {
        String voitureJson = "{\"marque\":\"Ferrari\",\"prix\":100000}";

        mockMvc.perform(post("/voiture")
                .contentType(MediaType.APPLICATION_JSON)
                .content(voitureJson))
                .andExpect(status().isOk()) 
                .andDo(print());

        verify(statistiqueImpl, times(1)).ajouter(any(Voiture.class));
    }
}