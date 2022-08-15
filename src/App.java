public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        Year T= new Year(2001);
        Month M = new Month(MonthName.Januar);
        M.addBill(new BillClass(200, Reason.Auto, "remark"));
        M.addBill(new BillClass(100, Reason.Wohnung, "remark"));
        M.addBill(new BillClass(800, Reason.Auto, "remark"));
        T.addMonth(M);

        Month n = new Month(MonthName.Febuar);
        n.addBill(new BillClass(200, Reason.Auto, "remark"));
        n.addBill(new BillClass(100, Reason.Wohnung, "remark"));
        n.addBill(new BillClass(800, Reason.Gehalt, "remark"));
        T.addMonth(n);
        //System.out.println( T.getDiagramData(200));
    }
}
