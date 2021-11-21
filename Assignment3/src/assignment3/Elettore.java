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
	  @			( \forall int i; i>=0 && i<16 ; Character.isDigit(codiceFiscale[i])
	  @         || Character.isAlphabetic(codiceFiscale[i]) )
	  @			&& \forall int j; j>=0 && j<11 ; codiceFiscale[j] == generato.getCodiceFiscale()[j]
	  @			&& (codiceFiscale[11]=='Z' && !nazioneDiNascita.equals("Italia") || nazioneDiNascita.equals("Italia") && codiceFiscale[11]!='Z' )
	  @			&& Character.isAlphabetic(codiceFiscale[11]) && Character.isAlphabetic(codiceFiscale[15]) 
	  @			&& Character.isDigit(codiceFiscale[12]) && Character.isDigit(codiceFiscale[13]) 
	  @			&& Character.isDigit(codiceFiscale[14]);
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
		//FRNDKM00L23F205A
		//ZUXYIX00A41F205H
		//NPLPLG20A01Z353K
		char[] codDianka = {'F','R','N','D','K','M','0','0','L','2','3','A','1','1','1','A'};
		char[] codLore = {'R','M','O','L','N','Z','2','1','S','1','8','A','1','1','1','A'};
		char[] f2 = {'Z','U','X','Y','I','X','0','0','A','4','1','H','1','1','1','A'};
		char[] f3 = {'N','P','L','P','L','G','2','0','A','4','1','Z','1','1','1','A'};
		// Elettore che rispetta le invarianti
		Elettore dianka = new Elettore("Dianka Mevan", "Fernando", 23, 7, 2000, "M", "Milano", "Italia", codDianka);
		dianka.esprimi_voto();
		dianka.showInfo();
		
		//dianka.esprimi_voto();
		
		
		// Elettore nato dopo la data odierna
		//Elettore lorenzo = new Elettore("Lorenzo", "Romeo", 18, 11, 2021, "M", "Milano", "Italia", codLore);
		
		// Elettore con nome di meno di 3 lettere, femmina
		Elettore elettore2 = new Elettore("Yi", "Zu", 1, 1, 2000, "F", "Milano", "Italia", f2);
		
		//Elettore minorenne, nato all'estero
		Elettore elettore3 = new Elettore("Pier Luigi", "Napoli", 1, 1, 2020, "F", "", "Uganda", f3);
		
		//elettore3.esprimi_voto();
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
				codiceFiscale[i] = 'X';
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
					codiceFiscale[i + 3] = 'X';
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
