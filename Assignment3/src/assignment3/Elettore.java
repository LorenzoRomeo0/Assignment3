package assignment3;

import dates.Date;
import dates.InvalidDateException;

public class Elettore {
	private Date oggi;
	
	private /*@ non_null @*/ String nome;
	private /*@ non_null @*/ String cognome;
	
	/*@ public invariant (dataDiNascita.getYear() + 18) < oggi.getYear() || 
	  @ (dataDiNascita.getYear() + 18) == oggi.getYear() && dataDiNascita.getMonth() < oggi.getMonth() 
	  @ || (dataDiNascita.getYear() + 18) == oggi.getYear() && dataDiNascita.getMonth() == oggi.getMonth()
	  @														   && dataDiNascita.getDay() < oggi.getDay();
	  @*/
	private /*@ spec_public @*/ Date dataDiNascita;
	private /*@ spec_public @*/ String comuneDiNascita;

	/*@ public invariant (nazioneDiNascita.equals("Italia") && comuneDiNascita != null) || 
	  @           (!comuneDiNascita.equals("Italia"));
	  @*/
	private /*@ spec_public @*/ String nazioneDiNascita;

	/*@ public invariant sesso.equals("M") || sesso.equals("F"); @*/
	private /*@ spec_public @*/ String sesso;
	
	/*@ public invariant codiceFiscale.length == 16 &&
	  @ \forall int i; i>=0 && i<codiceFiscale.length ; (Character.isDigit(codiceFiscale[i]) 
	  @                                               || Character.isAlphabetic(codiceFiscale[i]));
	  @*/
	private /*@ spec_public @*/ char[] codiceFiscale;
	
	/**
	 * ha valore true nel caso in cui l'elettore abbia espresso il suo voto, false
	 * nel caso in cui l’elettore non abbia ancora espresso il voto
	 * Calendar.getInstance().set(dataDiNascita.add(Calendar.YEAR, 18))
	 */
	/*@ public invariant dataDiNascita.getYear()>0; @*/
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

	/*@ requires !voto; @*/
	public void esprimi_voto() {
		this.voto = true;
	}

}
