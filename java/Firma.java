public class Firma extends DatabaseObject {
    private String name;
    private String steuerbuchummer;
    private String firmenbuchnummer;
    Firma(String name, String steuerbuchnummer, String firmenbuchnummer){
        this.name = name;
        this.steuerbuchummer = steuerbuchnummer;
        this.firmenbuchnummer = firmenbuchnummer;
    }
    public String getName() {
        return name;
    }
    public String getSteuerbuchummer() {
        return steuerbuchummer;
    }

    public String getFirmenbuchnummer() {
        return firmenbuchnummer;
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Firma) return getName().compareTo(((Firma) o).getName());
        else throw new ClassCastException("Incompatible types for Firma.compareTo(Object o).");
    }

    @Override
    public String toString() {
        return "Firma{" +
                "name='" + name + '\'' +
                ", steuerbuchummer='" + steuerbuchummer + '\'' +
                ", firmenbuchnummer='" + firmenbuchnummer + '\'' +
                '}' +'\n';
    }
}
