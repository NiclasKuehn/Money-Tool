import java.io.Serializable;

public class BillClass implements Serializable {
    public Reason reason;
    public double value =0.00;
    public String remark;
    
    public BillClass(int value, Reason reason, String remark){
        this.reason= reason;
        this. value = value;
        this.remark = remark;
    }
    public BillClass(String value, String reason, String remark){
        this. value =Double.parseDouble(value);
        
        this.reason= Reason.valueOf(reason);
        
        this.remark = remark;
    }
    public String toString(){

        String s =  value + "€\t" +reason.toString() + "\t" + remark;
        return s;
    }
}
