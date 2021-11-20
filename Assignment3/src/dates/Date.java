package dates;

import java.util.Calendar;

public class Date {
	private final int GREGORIAN_DAY_INTRO = 4;
	private final int GREGORIAN_MONTH_INTRO = 10;
	private final int GREGORIAN_YEAR_INTRO = 1582;

	private /*@ spec_public @*/ int day;
	private /*@ spec_public @*/ int month;
	private /*@ spec_public @*/ int year;

	public /*@ pure @*/ int getDay() {
		return day;
	}

	public void setDay(int day) throws InvalidDateException {
		InvalidDateException e = check(day, this.month, this.year);
		if(e != null) throw e;
		this.day = day;
	}

	public /*@ pure @*/ int getMonth() {
		return month;
	}

	public void setMonth(int month) throws InvalidDateException {
		InvalidDateException e = check(this.day, month, this.year);
		if(e != null) throw e;
		this.month = month;
	}

	public /*@ pure @*/ int getYear() {
		return year;
	}

	public void setYear(int year) throws InvalidDateException {
		InvalidDateException e = check(this.day, this.month, year);
		if(e != null) throw e;
		this.year = year;
	}

	public Date(int day, int month, int year) throws InvalidDateException {
		InvalidDateException e = check(day, month, year);
		if (null != e)
			throw e;
		this.day = day;
		this.month = month;
		this.year = year;
	}

	private InvalidDateException check(int day, int month, int year) {
		InvalidDateException e = null;
		if (year < GREGORIAN_YEAR_INTRO || year == GREGORIAN_YEAR_INTRO && month < GREGORIAN_MONTH_INTRO ||
				year == GREGORIAN_YEAR_INTRO && month == GREGORIAN_MONTH_INTRO && day < GREGORIAN_DAY_INTRO)
			e = new InvalidDateException("Inserted date is before gregorian calendar introduction.");
		else if (month < 1 || month > 12)
			e = new InvalidDateException("Invalid month value \"" + month + "\".");
		else if (day < 1 || (month == 1 && day > 31) || (month == 2 && day > 29) || (month == 3 && day > 31)
				|| (month == 4 && day > 30) || (month == 5 && day > 31) || (month == 6 && day > 30)
				|| (month == 7 && day > 31) || (month == 8 && day > 31) || (month == 9 && day > 30)
				|| (month == 10 && day > 31) || (month == 11 && day > 30) || (month == 12 && day > 31))
			e = new InvalidDateException("Invalid day value \"" + day + "/" + month + "\".");
		else if ((day == 29 && month == 2) && (year % 4 != 0 || year % 400 != 0))
			e = new InvalidDateException("Year " + year + "isn't a leap year.");
		return e;
	}

	public /*@ pure @*/ static Date now() {
		Calendar today = Calendar.getInstance();
		int day = today.get(Calendar.DAY_OF_MONTH);
		int month = today.get(Calendar.MONTH);
		int year = today.get(Calendar.YEAR);
		Date todayDate = null;
		try {
			todayDate = new Date(day, month, year);
		} catch (InvalidDateException e) {
			return null;
		}
		return todayDate;
	}
}