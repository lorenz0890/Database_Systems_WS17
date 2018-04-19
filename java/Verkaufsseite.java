public class Verkaufsseite extends DatabaseObject {
    private String url;
    private String pfad;
    private String titel;
    Verkaufsseite(String url, String pfad, String titel){
        this.url = url;
        this.pfad = pfad;
        this.titel = titel;
    }
    public String getUrl() {
        return url;
    }
    public String getPfad() {
        return pfad;
    }
    public String getTitel() {
        return titel;
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Verkaufsseite) return getUrl().compareTo(((Verkaufsseite) o).getUrl());
        else throw new ClassCastException("Incompatible types for Verkaufsseite.compareTo(Object o).");
    }

    @Override
    public String toString() {
        return "Verkaufsseite{" +
                "url='" + url + '\'' +
                ", pfad='" + pfad + '\'' +
                ", titel='" + titel + '\'' +
                '}' + '\n';
    }
}
