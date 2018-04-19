public class Unterseite extends DatabaseObject {
    private String url;
    private String pfad;
    Unterseite(String url, String pfad){
        this.url = url;
        this.pfad = pfad;
    }
    public String getUrl() {
        return url;
    }
    public String getPfad() {
        return pfad;
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Unterseite) return getUrl().compareTo(((Unterseite) o).getUrl());
        else throw new ClassCastException("Incompatible types for Unterseite.compareTo(Object o).");
    }

    @Override
    public String toString() {
        return "Unterseite{" +
                "url='" + url + '\'' +
                ", pfad='" + pfad + '\'' +
                '}' + '\n';
    }
}
