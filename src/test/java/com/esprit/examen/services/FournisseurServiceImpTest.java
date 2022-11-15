package com.esprit.examen.services;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.esprit.examen.entities.Fournisseur;
import com.esprit.examen.repositories.FournisseurRepository;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FournisseurServiceImpTest {

@Mock
FournisseurRepository fr;
@InjectMocks
FournisseurServiceImpl fs;

Fournisseur f = new Fournisseur("x5Z5F2x","Adidas");

Long getId()
{
    for (Fournisseur fo: fr.findAll()) {
        return fo.getIdFournisseur();
    }
    return 0L;
}
@Test
@Order(0)
void TestaddFournisseur() {
	Fournisseur fo = new Fournisseur();
    List<Fournisseur> Fournisseurs = new ArrayList<>();
    for (Long i=1L;i<=10L;i++) {
        fo.setIdFournisseur(i);
        fo.setCode("ad5Sxcs45");
        fo.setLibelle("FOUR");

        Fournisseur ca=fr.save(fo);
        Fournisseurs.add(ca);
    }
    assertEquals(10,Fournisseurs.size());
}
@Test
@Order(3)
void TestdeleteAllFournisseur() {
    fr.deleteAll();
    assertEquals(0,fr.findAll().spliterator().estimateSize());
}
@Test
@Order(2)
void TestretrieveFournisseur() {
    Mockito.when(fr.findById(Mockito.anyLong())).thenReturn(Optional.of(f));

    Mockito.when(fr.findById(Mockito.anyLong())).thenReturn(Optional.of(f))
    ;
    Fournisseur fo = fs.retrieveFournisseur(2L);
    Assertions.assertNotNull(fo);


}
@Test
@Order(4)
void TestgetAllFournisseur(){
    Iterable<Fournisseur> Fournisseurs = fr.findAll();
    Assertions.assertNotNull(Fournisseurs);
}

}	


	
	
	
	

	
	
	


