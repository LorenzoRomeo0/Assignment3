package assignment3;

import java.util.Arrays;

class Date {
	public int day;
	public int month;
	public int year;
	
	public Date(int day, int month, int year){
		this.day = day;
		this.month = month;
		this.year = year;
	}
	
	public String toString(){
		return day+"/"+month+"/"+year;
	}
	
}

public class Elettore {
	
	//Contiene informazioni riguardanti la data corrente
	private /*@spec_public@*/ Date oggi;
	
	private /*@ non_null @*/ String nome;
	private /*@ non_null @*/ String cognome;
	
	/*@ public invariant dataDiNascita.year < oggi.year 	
	  @  	||  (dataDiNascita.year == oggi.year &&  dataDiNascita.month < oggi.month) 
	  @     ||  (dataDiNascita.year == oggi.year &&  dataDiNascita.month == oggi.month 
	  @   										 &&  dataDiNascita.day < oggi.day);
	  @*/
	private /*@ spec_public @*/ Date dataDiNascita;
	private /*@ spec_public @*/ String comuneDiNascita;

	/*@ public invariant 	(nazioneDiNascita.equals("Italia") &&  !comuneDiNascita.equals(" ")) 
	  @						|| (!nazioneDiNascita.equals("Italia"));
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
		this.comuneDiNascita = (comuneDiNascita == null)?" ":comuneDiNascita;
		this.nazioneDiNascita = nazioneDiNascita;
		this.codiceFiscale = codiceFiscale;
		this.oggi = new Date(17, 11, 2021);
	}
	

	/*@ requires !voto 									&& 
	  @ (dataDiNascita.year + 18) < oggi.year 	
	  @  	||  ((dataDiNascita.year + 18) == oggi.year &&  dataDiNascita.month < oggi.month) 
	  @     ||  ((dataDiNascita.year + 18) == oggi.year &&  dataDiNascita.month == oggi.month 
	  @   												&&  dataDiNascita.day < oggi.day);
	  @*/
	public void esprimi_voto() {
		this.voto = true;
	}
	
	public void showInfo(){
		System.out.println("---");
		System.out.println("Today = "+oggi);
		System.out.println("Nome, cognome = "+nome+" "+cognome);
		System.out.println("Data di nascita = "+dataDiNascita);
		System.out.println("Nazione di nascita= "+ nazioneDiNascita);
		System.out.println("Comune di nascita = "+comuneDiNascita);
		System.out.println("Codice fiscale = "+Arrays.toString(codiceFiscale));
		System.out.println("Has voted = "+((voto)?"Yes":"No"));
		System.out.println("Genere = "+sesso);
		System.out.println("---");
	}
	
	
	public static void main(String[] argv) {
		char[] f1 = {'A','A','A','A','A','A','A','A','A','A','A','A','A','A','A','A'};
			//Elettore elettore = new Elettore("Dianka Mevan", "Fernando", 4, 10, 2020, "J", "Milano", "Italia", f1);
			Elettore elettore2 = new Elettore("Lorenzo", "Romeo", 5, 10, 1800, "M", "Milano", "Italia", f1);
			
			//TODO null da errore anche se l'invariante e' rispettata
			Elettore elettore3 = new Elettore("A", "B", 21, 11, 2021, "M", " ", "Svezia", f1);
			elettore2.showInfo();
			elettore2.esprimi_voto();
			elettore2.showInfo();
			//elettore2.esprimi_voto();
	}

}
