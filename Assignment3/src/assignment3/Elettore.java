package assignment3;

import dates.Date;
import dates.InvalidDateException;

public class Elettore {
	
	//Contiene informazioni riguardanti la data corrente
	private /*@spec_public@*/ Date oggi;
	
	private /*@ non_null @*/ String nome;
	private /*@ non_null @*/ String cognome;
	
	private /*@ spec_public @*/ Date dataDiNascita;
	private /*@ spec_public @*/ String comuneDiNascita;

	/*@ public invariant 	(nazioneDiNascita.equals("Italia") && 
	  @ 					comuneDiNascita != null) || (!comuneDiNascita.equals("Italia"));
	  @*/
	private /*@ spec_public @*/ String nazioneDiNascita;

	
	/*@ public invariant 	sesso.equals("M") || sesso.equals("F"); @*/
	private /*@ spec_public @*/ String sesso;
	
	/*@ public invariant 	codiceFiscale.length == 16 &&
	  @						( \forall int i; i>=0 && i<16 ; Character.isDigit(codiceFiscale[i])
	  @                     || Character.isAlphabetic(codiceFiscale[i]) );
	  @*/
	
	private /*@ spec_public@*/ char[] codiceFiscale;
	
	// Se l'utente ha gia' espresso il voto ha valore true
	private /*@ spec_public @*/ boolean voto;

	public Elettore(String nome, String cognome, int giorno, int mese, int anno, String sesso,
			String comuneDiNascita, String nazioneDiNascita, char[] codiceFiscale) throws InvalidDateException {
		this.nome = nome;
		this.cognome = cognome;
		this.dataDiNascita = new Date(giorno, mese, anno);
		this.sesso = sesso;
		this.comuneDiNascita = comuneDiNascita;
		this.nazioneDiNascita = nazioneDiNascita;
		this.codiceFiscale = codiceFiscale;
		this.oggi = new Date();
	}

	/*@ requires !voto 								&& 
	  @ 	(dataDiNascita.year + 18) < oggi.year 	
	  	||  ((dataDiNascita.year + 18) == oggi.year &&  dataDiNascita.month < oggi.month) 
	    ||  ((dataDiNascita.year + 18) == oggi.year &&  dataDiNascita.month == oggi.month 
	    											&&  dataDiNascita.day < oggi.day);
	  @*/
	public void esprimi_voto() {
		this.voto = true;
	}

}
