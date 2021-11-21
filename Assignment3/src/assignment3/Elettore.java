package assignment3;

import java.awt.print.PrinterAbortException;
import java.util.Arrays;

import javax.lang.model.type.PrimitiveType;
import javax.swing.MenuSelectionManager;

import org.jmlspecs.annotation.Pure;

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
	  @                     || Character.isAlphabetic(codiceFiscale[i]) )
	  @						&& \forall int j; j>=0 && j<12 ; Character.toUpperCase(codiceFiscale[j]) == generato.getCodiceFiscale()[j];
	  @*/
	private /*@ spec_public@*/ CodiceFiscale generato;
	private /*@ spec_public@*/ char[] codiceFiscale;
	
	// Se l'utente ha gia' espresso il voto ha valore true
	private /*@ spec_public @*/ boolean voto;

	
	
	public Elettore(String nome, String cognome, int giorno, int mese, int anno, String sesso,
			String comuneDiNascita, String nazioneDiNascita, char[] codiceFiscale) {
		this.nome = nome;
		this.cognome = cognome;
		this.dataDiNascita = new Date(giorno, mese, anno);
		this.sesso = sesso;
		this.comuneDiNascita = (comuneDiNascita == null || comuneDiNascita.equals("") )?" ":comuneDiNascita;
		this.nazioneDiNascita = nazioneDiNascita;
		this.codiceFiscale = codiceFiscale;
		this.oggi = new Date(17, 11, 2021);
		this.generato = new CodiceFiscale(nome, cognome, giorno, mese, anno, sesso, comuneDiNascita, nazioneDiNascita);
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
			
			Elettore elettore3 = new Elettore("A", "B", 16, 11, 2021, "M", " ", "Svezia", f1);
			elettore2.showInfo();
			elettore2.esprimi_voto();
			elettore2.showInfo();
			
			CodiceFiscale codiceFiscale = new CodiceFiscale("Lorenzo", "romeo", 5, 10, 1800, "M", "Milano", "Italiaa");
			//elettore2.esprimi_voto();
	}
	
}

class CodiceFiscale {
	private char[] codiceFiscale;
	
	public CodiceFiscale(String nome, String cognome, int giorno, int mese, int anno, String sesso,
			String comuneDiNascita, String nazioneDiNascita) {
		String cognomeConsonanti = getConsonanti(cognome);
		String cognomeVocali = getVocali(cognome);
		codiceFiscale = new char[16];
		for (int i = 0; i < 3; i++) {
			if(i < cognomeConsonanti.length()) {
				codiceFiscale[i] = cognomeConsonanti.charAt(i);
			} else if(i-(cognomeConsonanti.length()) < cognomeVocali.length()) {
				codiceFiscale[i] = cognomeVocali.charAt(i-(cognomeConsonanti.length()));
			} else {
				codiceFiscale[i] = 'x';
			}
		}
		String nomeConsonanti = getConsonanti(nome);
		String nomeVocali = getVocali(nome);
		for (int i = 0; i < 3; i++) {
			if(nomeConsonanti.length()>=4) {
				codiceFiscale[i + 3] = nomeConsonanti.charAt(i);
				if (i>0) {
					codiceFiscale[i + 3] = nomeConsonanti.charAt(i+1);
				}
			} else {
				if(i < nomeConsonanti.length()) {
					codiceFiscale[i + 3] = nomeConsonanti.charAt(i);
				} else if(i-(nomeConsonanti.length()) < nomeVocali.length()) {
					codiceFiscale[i + 3] = nomeVocali.charAt(i-(nomeConsonanti.length()));
				} else {
					codiceFiscale[i + 3] = 'x';
				}
			}
		}
		String annoString = String.valueOf(anno);
		codiceFiscale[6] = annoString.charAt(annoString.length()-2);
		codiceFiscale[7] = annoString.charAt(annoString.length()-1);
		codiceFiscale[8] = meseToCharCodiceFiscale(mese);
		if(sesso.equals("F")) {
			giorno += 40;
		}
		String giornoString = String.valueOf(giorno);
		if(giornoString.length() == 1) {
			codiceFiscale[9] = '0';
			codiceFiscale[10] = giornoString.charAt(0);
		} else {
			codiceFiscale[9] = giornoString.charAt(0);
			codiceFiscale[10] = giornoString.charAt(1);
		}
		if(!nazioneDiNascita.equals("Italia")) {
			codiceFiscale[11] = 'Z';
		}
	}
	
	public /*@ pure @*/ char[] getCodiceFiscale() {
		return codiceFiscale;
	}

	private char meseToCharCodiceFiscale(int mese) {
		switch (mese) {
		case 1:
			return 'A';
		case 2:
			return 'B';
		case 3:
			return 'C';
		case 4:
			return 'D';
		case 5:
			return 'E';
		case 6:
			return 'H';
		case 7:
			return 'L';
		case 8:
			return 'M';
		case 9:
			return 'P';
		case 10:
			return 'R';
		case 11:
			return 'S';
		case 12:
			return 'T';
		default:
			return 'X';
		}
	}
	
	private String getConsonanti(String string) {
		String consonanti = new String();
		String lower = string.toLowerCase();
		for (int i = 0; i < string.length(); i++) {
			char letter = lower.charAt(i);
			switch (letter) {
				//a
			case 'b':
			case 'c':
			case 'd':
				//e
			case 'f':
			case 'g':
			case 'h':
				//i
			case 'j':
			case 'k':
			case 'l':
			case 'm':
			case 'n':
				//o
			case 'p':
			case 'q':
			case 'r':
			case 's':
			case 't':
				//u
			case 'v':
			case 'w':
			case 'x':
			case 'y':
			case 'z':
				consonanti = consonanti.concat(String.valueOf(letter)).toUpperCase();
				break;
			default:
				break;
			}
		}
		return consonanti;
	}
	
	private String getVocali(String string) {
		String vocali = new String();
		String lower = string.toLowerCase();
		for (int i = 0; i < string.length(); i++) {
			char letter = lower.charAt(i);
			switch (letter) {
			case 'a':
			case 'e':
			case 'i':
			case 'o':
			case 'u':
				vocali = vocali.concat(String.valueOf(letter)).toUpperCase();
				break;
			default:
				break;
			}
		}
		return vocali;
	}
	
}
