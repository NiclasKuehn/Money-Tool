import java.io.*;
import java.util.ArrayList;

public class Year implements Serializable {
	public int year;
	
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
	
	public String getYear() {
		return Integer.toString(year);
	}
	
	private final ArrayList<Month> MonthList = new ArrayList<>();
	
	private Year() {
	}
	
	;
	
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



    /*public double[] getDiagramData(int maxPixelHeight) {
        ArrayList<BillClass> AllBills = new ArrayList<>();
        ArrayList<BillClass>[] SortedBills = new ArrayList[Reason.size]<>();
        double[] valueSum = new double[Reason.size];
        for (int i = 0; i < MonthList.size(); i++) {
            for (int j = 0; j < MonthList.get(i).getBillArray().size(); j++) {
                AllBills.add(MonthList.get(i).getBillArray().get(j));
            }
        }
        for (Reason R : Reason.values()) {

            for (int j = 0; j < AllBills.size(); j++) {
                if (AllBills.get(j).reason == R) {
                    SortedBills[R.ordinal()].add(AllBills.get(j));
                }
            }
        }
        double totalMoney = 0;
        double maxMoney = 0;
        for (ArrayList<BillClass> billofReason : SortedBills) {
            for (BillClass Bill : billofReason) {
                valueSum[Bill.reason.ordinal()] += Bill.value;
                totalMoney += Bill.value;

            }
        }

        double[] verhältnis = new double[Reason.size];
        for (Reason R : Reason.values()) {
            verhältnis[R.ordinal()] = valueSum[R.ordinal()]/totalMoney;
            
        }
        for (double value  : valueSum) {
            if(value>maxMoney)maxMoney=value;
        }
        double pixelProProzent= maxPixelHeight/maxMoney;
        double[] returnArray= new double[Reason.size];
        for (int i = 0; i < Reason.size; i++) {
            returnArray[i]=verhältnis[i]*pixelProProzent;
        }
        return returnArray;
    }*/
}
