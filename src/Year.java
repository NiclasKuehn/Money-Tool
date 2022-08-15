import java.util.ArrayList;

public class Year {
    public int year;
    private final ArrayList<Month> MonthList = new ArrayList<>();

    public Year(int year) {
        this.year = year;
    }

    public void addMonth(Month Month) {
        this.MonthList.add(Month);
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
