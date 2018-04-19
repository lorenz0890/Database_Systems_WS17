public class Produkt extends DatabaseObject {
    private String ProduktID;
    private String Preis;
    private String Produktname;

    Produkt(String ProduktID, String Preis, String Produktname) {
        this.ProduktID = ProduktID;
        this.Preis = Preis;
        this.Produktname = Produktname;
    }

    public String getProduktID() {
        return ProduktID;
    }

    public String getPreis() {
        return Preis;
    }

    public String getProduktname() {
        return Produktname;
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Produkt) return getProduktID().compareTo(((Produkt) o).getProduktID());
        else throw new ClassCastException("Incompatible types for Produkt.compareTo(Object o).");
    }

    @Override
    public String toString() {
        return "Produkt{" +
                "ProduktID='" + ProduktID + '\'' +
                ", Preis='" + Preis + '\'' +
                ", Produktname='" + Produktname + '\'' +
                '}' + '\n';
    }
}