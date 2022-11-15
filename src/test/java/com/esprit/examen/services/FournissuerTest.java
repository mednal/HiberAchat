package com.esprit.examen.services;
import org.junit.jupiter.api.Test;

import java.util.List;


import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.esprit.examen.entities.Fournisseur;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FournissuerTest {
	
	@Autowired
	IFournisseurService fs;
	 
	 @Test
	    void retrieveAllFournisseur() {
	    	List<Fournisseur> Fournisseurs = fs.retrieveAllFournisseurs();
	        Assertions.assertEquals(0, Fournisseurs.size());
	    }
	 
	 @Test
	    public void testAddFournisseur(){
	    List<Fournisseur> Fournisseurs = fs.retrieveAllFournisseurs();
	    int expected = Fournisseurs.size();
	    Fournisseur f = new Fournisseur("123136","banque");

	    Fournisseur saveFournisseur= fs.addFournisseur(f);
	    assertEquals(expected+1, fs.retrieveAllFournisseurs().size());
	    assertNotNull(saveFournisseur.getLibelle());
	    fs.deleteFournisseur(saveFournisseur.getIdFournisseur());

	    }
	 
	 @Test
	    public void testUpdateFournisseur() {
		 Fournisseur s = new Fournisseur("123136","banque");
		 Fournisseur saveFournisseur= fs.addFournisseur(s);
		saveFournisseur.setLibelle("64654654");
	    fs.updateFournisseur(saveFournisseur);
	    assertEquals(s.getLibelle(),saveFournisseur.getLibelle());
	    fs.deleteFournisseur(saveFournisseur.getIdFournisseur());
	    }

	    @Test
	    public void testDeleteFournisseur() {
	    	Fournisseur s = new Fournisseur("123136","banque");
	    	Fournisseur saFournisseur= fs.addFournisseur(s);
		fs.deleteFournisseur(saFournisseur.getIdFournisseur());
		assertNotNull(saFournisseur.getIdFournisseur());

	    }

	
}