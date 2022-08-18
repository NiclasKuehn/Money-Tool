import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Storage {
	public static boolean isYear(int Year) {
		try {
			ObjectInputStream stream = new ObjectInputStream(new FileInputStream(Integer.toString(Year) + ".txt"));
			Year MainYear = (Year) stream.readObject();
			stream.close();
			return true;
		} catch (ClassNotFoundException cnfex) {
			
			System.err.println("Die Klasse des geladenen Objekts konnte nicht gefunden werden.");
			return false;
		} catch (IOException ioex) {
			
			
			return false;
		}
		
		
	}
	
	public static Year loadYear(int Year) {
		try {
			ObjectInputStream stream = new ObjectInputStream(new FileInputStream(Integer.toString(Year) + ".txt"));
			Year MainYear = (Year) stream.readObject();
			stream.close();
			return MainYear;
			
		} catch (ClassNotFoundException cnfex) {
			
			System.err.println("Die Klasse des geladenen Objekts konnte nicht gefunden werden.");
			return null;
			
		} catch (IOException ioex) {
			
			System.err.println("Das Objekt konnte nicht geladen werden.");
			ioex.printStackTrace();
			return null;
			
		}
		
		
	}
}
