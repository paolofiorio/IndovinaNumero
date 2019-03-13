package it.polito.tdp.numero.model;

import java.security.InvalidParameterException;
import java.util.LinkedList;
import java.util.List;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class NumeroModel {
	
	
	
	private List<Integer> tentativi;
	
	private final int NMAX = 100;
	private final int TMAX = 8;
	private int segreto;
	
	
	//private int tentativiFatti;
	private IntegerProperty tentativiFatti;
	
	
	private boolean inGioco = false;

	
	public NumeroModel() {
		inGioco = false;
		tentativiFatti = new SimpleIntegerProperty();
		tentativi = new LinkedList<Integer>();
	}

	/**
	 * Avvia nuova partita
	 */
	public void newGame() {
		inGioco = true;
		this.segreto = (int) (Math.random() * NMAX) + 1;
		this.tentativiFatti.set(0);
		this.tentativi = new LinkedList<Integer>();

	}
	
	/**
	 * Metodo per effettuare un tentativo
	 * 
	 * @param t il tentativo
	 * @return 1 se il tentativo è troppo alto, -1 se è troppo basso, 0 se l'utente ha indovinato
	 */
	public int tentativo(int t) {
		
		//controllo se la partita è in corso
		if(!inGioco) {
			throw new IllegalStateException("La partita è terminata");
		}
		
		//controllo se l'input è nel range corretto
		if(!tentativoValido(t)) {
			throw new InvalidParameterException(String.format("Devi inserire un numero "
					+ "tra %d e %d", 1, NMAX));
		}
		
		//gestisci tentativo
		this.tentativiFatti.set(this.tentativiFatti.get() + 1);
		
		this.tentativi.add(t);
		
		
		if(this.tentativiFatti.get() == this.TMAX) {
			//la partita è finita perchè ho esaurito i tentativi
			this.inGioco = false;
		}
		
		if(t == this.segreto) {
			//ho indovinato
			this.inGioco = false;

			return 0;
		}
		
		if(t > this.segreto) {
			return 1;
		}
		
		return -1;
		
	}
	
	public boolean tentativoValido(int t) {
		
		if(t<1 || t>NMAX ) {
			return false;
		} else {
			if(this.tentativi.contains(t))
				return false;
			else
				return true;
		}
		
		
	}

	public int getSegreto() {
		return segreto;
	}

	

	public boolean isInGioco() {
		return inGioco;
	}

	public int getTMAX() {
		return TMAX;
	}

	
	public int getNMAX() {
		return NMAX;
	}

	public final IntegerProperty tentativiFattiProperty() {
		return this.tentativiFatti;
	}
	

	public final int getTentativiFatti() {
		return this.tentativiFattiProperty().get();
	}
	

	public final void setTentativiFatti(final int tentativiFatti) {
		this.tentativiFattiProperty().set(tentativiFatti);
	}
	
	
	
	
	
	
	
	

}