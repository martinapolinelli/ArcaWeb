package business;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import integration.AnimaleDto;
import presentation.servlets.ArcaDao;

public class Arca {

	public Boolean imbarca(AnimaleDto animale) throws ImbarcaException {
		Map<Integer, AnimaleDto> animali = new HashMap<>();
		animali = ArcaDao.select();

		int id;
		int quantitaAttuale = 0;
		int numSpecieMax = 2;

		if (animale == null) {
			System.out.println("non posso imbarcare un animale nullo");
			return false;
		}

		id = animale.getId();

		if (animali.containsKey(id)) {
			System.out.println("Animale già presente sull'arca");
			return false;
		}

		quantitaAttuale = checkNumSpecie(animale.getSpecie(), animali);

		if (quantitaAttuale >= numSpecieMax) {
			stampaErrore("imbarcare", "raggiunto numero massimo per specie");
			return false;
		}

		if (quantitaAttuale < numSpecieMax) {
			ArcaDao.insert(animale);
			stampaSuccesso("Imbarco");
			return true;
		} else {
			return false;
		}

	}

	public Boolean sbarca(Integer id) throws AnimaleNonPresenteException {
		Map<Integer, AnimaleDto> animali = new HashMap<>();

		animali = ArcaDao.select();

		// se esiste un animale con quell'id
		if (animali.containsKey(id)) {
			ArcaDao.delete(id);
			stampaSuccesso("Sbarco");
			return true;
		}

		throw new AnimaleNonPresenteException(
				"Non è stato possibile far sbarcare " + id + " perchè non è presente sull'arca");
	}

	public List<AnimaleDto> getAnimaliPerPeso() {
		Map<Integer, AnimaleDto> animali = new HashMap<>();
		animali = ArcaDao.select();

		Collection<AnimaleDto> valoreAnimali = animali.values();
		List<AnimaleDto> listaDiValori = new ArrayList<>(valoreAnimali);

		// peso1 > peso2 -> res pos
		// peso1 < peso2 -> res neg
		Collections.sort(listaDiValori, new Comparator<AnimaleDto>() {
			public int compare(AnimaleDto a1, AnimaleDto a2) {
				int peso1 = a1.getPeso();
				int peso2 = a2.getPeso();
				return peso1 - peso2;
			}
		});

		return listaDiValori;
	}

	public Boolean modifyPeso(Integer id, Integer peso) {
		Map<Integer, AnimaleDto> animali = new HashMap<>();
		animali = ArcaDao.select();

		AnimaleDto animale = new AnimaleDto();
		animale.setId(id);
		animale.setPeso(peso);
		System.out.println("cavallo");

		if (ArcaDao.update(id, peso)) {
			stampaSuccesso("Aggiornamento peso");
			return true;
		}
		return false;
	}

	public AnimaleDto getAnimaleSingolo(Integer id) throws AnimaleNonPresenteException {
		Map<Integer, AnimaleDto> animali = new HashMap<>();
		animali = ArcaDao.select();
		AnimaleDto animale = new AnimaleDto();
		animale.setId(id);

		// se esiste un animale con quell'id
		if (animali.containsKey(id)) {
			ArcaDao.selectOne(id);
			stampaSuccesso("Processo di ricerca");
			return animale;
		}

		throw new AnimaleNonPresenteException(
				"Non è stato trovato l'animale di id: " + id + " perchè non è presente sull'arca");
	}

	/****************************************
	 * PRIVATE
	 ************************************/

	private int checkNumSpecie(String specie, Map<Integer, AnimaleDto> animali) {
		int quantitaAnimale = 0;
		for (AnimaleDto animaleTmp : animali.values()) {
			if (animaleTmp.getSpecie().equals(specie)) {
				quantitaAnimale++;
			}
		}
		return quantitaAnimale;

	}

	// STAMPE
	private void stampaErrore(String azioneImpossibile, String erroreSpecifico) {
		System.out.println("Impossibile " + azioneImpossibile + ", " + erroreSpecifico);
	}

	private void stampaSuccesso(String azioneEseguita) {
		System.out.println(azioneEseguita + " eseguito con successo");
	}

}
