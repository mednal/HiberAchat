package com.esprit.examen.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.esprit.examen.entities.DetailFournisseur;
import com.esprit.examen.entities.Fournisseur;
import com.esprit.examen.entities.SecteurActivite;
import com.esprit.examen.repositories.DetailFournisseurRepository;
import com.esprit.examen.repositories.FournisseurRepository;
import com.esprit.examen.repositories.ProduitRepository;
import com.esprit.examen.repositories.SecteurActiviteRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FournisseurServiceImpl implements IFournisseurService {

	@Autowired
	FournisseurRepository fournisseurRepository;
	@Autowired
	DetailFournisseurRepository detailFournisseurRepository;
	@Autowired
	ProduitRepository produitRepository;
	@Autowired
	SecteurActiviteRepository secteurActiviteRepository;

	@Override
	public List<Fournisseur> retrieveAllFournisseurs() {
		List<Fournisseur> fournisseurs = (List<Fournisseur>) fournisseurRepository.findAll();
		for (Fournisseur fournisseur : fournisseurs) {
			log.info(" fournisseur : " + fournisseur);
		}
		return fournisseurs;
	}


	public Fournisseur addFournisseur(Fournisseur f /*Master*/) {
		DetailFournisseur df= new DetailFournisseur();//Slave
		df.setDateDebutCollaboration(new Date()); //util
		//On affecte le "Slave" au "Master"
		f.setDetailFournisseur(df);	
		fournisseurRepository.save(f);
		return f;
	}
	
	private DetailFournisseur  saveDetailFournisseur(Fournisseur f){
		DetailFournisseur df = f.getDetailFournisseur();
		detailFournisseurRepository.save(df);
		return df;
	}

	public Fournisseur updateFournisseur(Fournisseur f) {
		/*DetailFournisseur df = saveDetailFournisseur(f);
		f.setDetailFournisseur(df);   */
		fournisseurRepository.save(f);
		return f;
	}

	@Override
	public void deleteFournisseur(Long fournisseurId) {
		fournisseurRepository.deleteById(fournisseurId);

	}

	@Override
	public Fournisseur retrieveFournisseur(Long fournisseurId) {

		Fournisseur fournisseur = fournisseurRepository.findById(fournisseurId).orElse(null);
		return fournisseur;
	}

	@Override
	public void assignSecteurActiviteToFournisseur(Long idSecteurActivite, Long idFournisseur) {
		Fournisseur fournisseur = fournisseurRepository.findById(idFournisseur).orElse(null);
		SecteurActivite secteurActivite = secteurActiviteRepository.findById(idSecteurActivite).orElse(null);
        if (fournisseur==(null))
		{
			log.info("Error");  
		}
		else
		{
			 fournisseur.getSecteurActivites().add(secteurActivite);
		        fournisseurRepository.save(fournisseur);
		
		}
	}
	
	@Override
	@Transactional

	public void deleteFournisseurById(Long id) {
			log.debug("methode deleteFournisseurById ");
			try {
				Optional<Fournisseur> foo = fournisseurRepository.findById(id);
				if(foo.isPresent()){
				Fournisseur fo = foo.get();
				fournisseurRepository.delete(fo);
				log.debug("deleteFournisseurById fini avec succes ");
				}
				else {
					log.error("erreur methode deleteFournisseureById : " );
				}
			} catch (Exception e) {
				log.error("erreur methode deleteFournisseurById : " +e);
			}

		}

	@Override
	public Fournisseur getFournisseurById(Long id) {
			log.debug("methode getFournisseurById ");
			try {
				Fournisseur f= fournisseurRepository.findById(id).orElse(null);
				log.debug("getFournisseurById fini avec succes ");
				return f;
			} catch (Exception e) {
				log.error("erreur methode getFournisseurById : " +e);
				return null;
			}
		}

	@Override
	public void UpdateLibelleFournisseurById(String libelle, Long id) {
		Fournisseur f = fournisseurRepository.findById(id).orElse(null);
		if(f!= null) {
			f.setLibelle(libelle);
			fournisseurRepository.save(f);
		}
		
	}

	

}