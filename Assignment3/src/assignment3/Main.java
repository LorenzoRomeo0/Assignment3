package assignment3;
import dates.InvalidDateException;
import assignment3.Elettore.Sesso;;

public class Main {
	public static void main(String[] argv) {
		try {
			Elettore elettore = new Elettore("Dianka Mevan", "Fernando", 4, 10, 1582, Sesso.M, "Milano", "Italia", null);
			Elettore elettore2 = new Elettore("Lorenzo", "Romeo", 5, 10, 1582, Sesso.M, "Milano", "Italia", null);
		} catch (InvalidDateException e) {
			e.printStackTrace();
		}
	}
}
