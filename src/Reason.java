public enum Reason{
    Wohnung (0),
    Auto(1),
    Gehalt(2),
    Freizeit(3),
    Essen(4),
    Geschenk(5),
    Sonstiges(6),
    PC(7);
    public static final int size=8;
    Reason(int i){}
    public static String[] getStringValue(){
        String[] s={"Wohnung","Auto","Gehalt","Freizeit","Essen","Geschenk","Sonstiges","PC"};
        return s;
    }
}