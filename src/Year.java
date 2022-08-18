import java.io.*;
import java.util.ArrayList;

public class Year extends BillContainer implements Serializable {
	private final int year;
	private ArrayList<BillClass> BillListL = new ArrayList<BillClass>();
	
	public void save() {
		try {
			ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(Integer.toString(year) + ".txt"));
			stream.writeObject(this);
			stream.close();
		} catch (IOException ioex) {
			System.err.println("Fehler beim Schreiben des Objekts aufgetreten.");
			ioex.printStackTrace();
		}
	}
	
	
	private final ArrayList<Month> MonthList = new ArrayList<>();
	
	
	public Year(int year) {
		MonthList.add(new Month(MonthName.Januar));
		MonthList.add(new Month(MonthName.Febuar));
		MonthList.add(new Month(MonthName.März));
		MonthList.add(new Month(MonthName.April));
		MonthList.add(new Month(MonthName.Mai));
		MonthList.add(new Month(MonthName.Juni));
		MonthList.add(new Month(MonthName.Juli));
		MonthList.add(new Month(MonthName.August));
		MonthList.add(new Month(MonthName.September));
		MonthList.add(new Month(MonthName.Oktober));
		MonthList.add(new Month(MonthName.November));
		MonthList.add(new Month(MonthName.Dezember));
		this.year = year;
	}
	
	public Month getMonth(int index) {
		return MonthList.get(index);
	}
	
	@Override
	public String getName() {
		return Integer.toString(this.year);
	}
	
	public ArrayList<BillClass> getBillList() {
		for (Month M : MonthList
		) {
			BillListL.addAll(M.getBillList());
		}
		return BillListL;
	}
	
	public int getYear() {
		return this.year;
	}
	
}
