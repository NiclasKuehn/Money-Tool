package Data;

import Data.Year;

import java.io.*;

public class Storage {
	public static boolean isYear(int Year) {
		try {
			ObjectInputStream stream = new ObjectInputStream(new FileInputStream(Integer.toString(Year) + ".txt"));
			Year MainYear = (Data.Year) stream.readObject();
			stream.close();
			return true;
		} catch (ClassNotFoundException cnfex) {
			
			System.err.println("Die Klasse des geladenen Objekts konnte nicht gefunden werden.");
			return false;
		} catch (IOException ioex) {
			
			
			return false;
		}
		
		
	}
	
	/*public static Data.Year loadYear(int Data.Year) {
		Data.Year year = new Data.Year();
		year.StringToYear();
		try {
			ObjectInputStream stream = new ObjectInputStream(new FileInputStream(Integer.toString(Data.Year) + ".txt"));
			Data.Year MainYear = (Data.Year) stream.readObject();
			stream.close();
			return MainYear;
			
		} catch (ClassNotFoundException cnfex) {
			
			System.err.println("Die Klasse des geladenen Objekts konnte nicht gefunden werden.");
			
			
		} catch (IOException ioex) {
			
			System.err.println("Das Objekt konnte nicht geladen werden.");
			ioex.printStackTrace();
			
			
		}
		return new Data.Year(Data.Year);
	}*/
	public static Year loadYear(int Year) {
		
		try {
			
			
			ObjectInputStream stream = new ObjectInputStream(new FileInputStream(Integer.toString(Year) + ".txt"));
			String MainYearString = (String) stream.readObject();
			String y = MainYearString.substring(1, 5);
			Year year = new Year(Integer.parseInt(y));
			year.StringToYear(MainYearString);
			stream.close();
			return year;
			
		} catch (ClassNotFoundException cnfex) {
			
			System.err.println("Die Klasse des geladenen Objekts konnte nicht gefunden werden.");
			
			
		} catch (IOException ioex) {
			
			System.err.println("Das Objekt konnte nicht geladen werden.");
			ioex.printStackTrace();
			
			
		}
		return new Year(Year);
	}
	
	public static void saveYear(Year year) {
		System.out.printf(year.toSaveString());
		try {
			ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(year.getName() + ".txt"));
			stream.writeObject(year.toSaveString());
			stream.close();
		} catch (IOException ioex) {
			System.err.println("Fehler beim Schreiben des Objekts aufgetreten.");
			ioex.printStackTrace();
		}
		
		
	}
}
