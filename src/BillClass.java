

public class BillClass {
    public Reason reason;
    public int value;
    public String remark;
    
    public BillClass(int value, Reason reason, String remark){
        this.reason= reason;
        this. value = value;
        this.remark = remark;
    }
    public BillClass(String value, String reason, String remark){
        this. value =Integer.parseInt(value);
        
        this.reason= Reason.valueOf(reason);
        
        this.remark = remark;
    }
    public String toString(){
        String s = Integer.toString(value) + "€\t" +reason.toString() + "\t" + remark;
        return s;
    }
}
