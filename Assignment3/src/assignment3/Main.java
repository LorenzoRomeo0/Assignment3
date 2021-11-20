package assignment3;
import dates.InvalidDateException;

public class Main {
	public static void main(String[] argv) {
		try {
			Elettore elettore = new Elettore("Dianka Mevan", "Fernando", 4, 10, 1582, "M", "Milano", "Italia", null);
			Elettore elettore2 = new Elettore("Lorenzo", "Romeo", 5, 10, 1582, "J", "Milano", "Italia", null);
			System.out.println("Hello world!");
		} catch (InvalidDateException e) {
			e.printStackTrace();
		}
	}
}
