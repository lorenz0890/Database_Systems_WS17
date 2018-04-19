public class kauftEin extends DatabaseObject {
    private String url;
    private String userID;
    private String pfad;
    private String produktID;
    private String anzahl;

    kauftEin(String url, String userID, String pfad, String produktID, String anzahl) {
        this.url = url;
        this.userID = userID;
        this.pfad = pfad;
        this.produktID = produktID;
        this.anzahl = anzahl;
    }

    public String getUrl() {
        return url;
    }

    public String getUserID() {
        return userID;
    }

    public String getPfad() {
        return pfad;
    }

    public String getProduktID() {
        return produktID;
    }

    public String getAnzahl() {
        return anzahl;
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof kauftEin) return getUserID().compareTo(((kauftEin) o).getUserID());
        else throw new ClassCastException("Incompatible types for kauftEin.compareTo(Object o).");
    }

    @Override
    public String toString() {
        return "kauftEin{" +
                "url='" + url + '\'' +
                ", userID='" + userID + '\'' +
                ", pfad='" + pfad + '\'' +
                ", produktID='" + produktID + '\'' +
                ", anzahl='" + anzahl + '\'' +
                '}' + '\n';
    }
}