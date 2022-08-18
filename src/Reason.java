public enum Reason {
    Wohnung(0),
    Auto(1),
    Gehalt(2),
    Freizeit(3),
    Essen(4),
    Geschenk(5),
    Sonstiges(6),
    PC(7);
    public static final int size = 8;
    
    Reason(int i) {
    }
    
    public static String[] getStringValue() {
        String[] s = {"Wohnung", "Auto", "Gehalt", "Freizeit", "Essen", "Geschenk", "Sonstiges", "PC"};
        return s;
    }
    
    public static Reason StringToReason(String s) {
        if (s == null || s.length() == 0) return null;
        return switch (s) {
            case "Wohnung" -> Wohnung;
            case "Auto" -> Auto;
            case "Gehalt" -> Gehalt;
            case "Freizeit" -> Freizeit;
            case "Essen" -> Essen;
            case "Geschenk" -> Geschenk;
            case "Sonstiges" -> Sonstiges;
            case "PC" -> PC;
            default -> null;
        };
    }
}