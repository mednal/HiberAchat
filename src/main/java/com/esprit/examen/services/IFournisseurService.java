package com.esprit.examen.services;

import java.util.List;
import com.esprit.examen.entities.Fournisseur;

public interface IFournisseurService {

	List<Fournisseur> retrieveAllFournisseurs();

	Fournisseur addFournisseur(Fournisseur f);

	void deleteFournisseur(Long id);

	Fournisseur updateFournisseur(Fournisseur f);

	Fournisseur retrieveFournisseur(Long id);
	
	void assignSecteurActiviteToFournisseur(Long idSecteurActivite, Long idFournisseur);
	
	public void deleteFournisseurById(Long id);
	
	public Fournisseur getFournisseurById(Long id);
	
	public void UpdateLibelleFournisseurById(String libelle,Long id);

}
