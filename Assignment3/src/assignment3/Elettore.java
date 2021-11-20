package assignment3;

import java.util.Calendar;

class Date {
	/* OVERVIEW:
	 * Questa classe contene varie informazioni e funzioni riguardanti le date.
	 */
	public int day;
	public int month;
	public int year;
	
	public Date(int day, int month, int year){
		this.day = day;
		this.month = month;
		this.year = year;
	}
	
}

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
			String comuneDiNascita, String nazioneDiNascita, char[] codiceFiscale) {
		this.nome = nome;
		this.cognome = cognome;
		this.dataDiNascita = new Date(giorno, mese, anno);
		this.sesso = sesso;
		this.comuneDiNascita = comuneDiNascita;
		this.nazioneDiNascita = nazioneDiNascita;
		this.codiceFiscale = codiceFiscale;
		this.oggi = new Date(17, 11, 2021);
	}
	

	/*@ requires !voto 								&& 
	  @ (dataDiNascita.year + 18) < oggi.year 	
	  @  	||  ((dataDiNascita.year + 18) == oggi.year &&  dataDiNascita.month < oggi.month) 
	  @     ||  ((dataDiNascita.year + 18) == oggi.year &&  dataDiNascita.month == oggi.month 
	  @   											&&  dataDiNascita.day < oggi.day);
	  @*/
	public void esprimi_voto() {
		this.voto = true;
	}
	
	
	public static void main(String[] argv) {
		char[] f1 = {'A','A','A','A','A','A','A','A','A','A','A','A','A','A','A','A'};
			//Elettore elettore = new Elettore("Dianka Mevan", "Fernando", 4, 10, 2020, "J", "Milano", "Italia", f1);
			Elettore elettore2 = new Elettore("Lorenzo", "Romeo", 5, 10, 1800, "M", "Milano", "Italia", f1);
			elettore2.esprimi_voto();
			//elettore2.esprimi_voto();
	}

}
