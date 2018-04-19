public class verwendet extends DatabaseObject { //class name in lower caps to maintain coherency with nomenclature in database
    private String url;
    private String steuerbuchummer;

    verwendet(String url, String steuerbuchnummer) {
        this.url = url;
        this.steuerbuchummer = steuerbuchnummer;
    }

    public String getUrl() {
        return url;
    }

    public String getSteuerbuchummer() {
        return steuerbuchummer;
    }


    @Override
    public int compareTo(Object o) {
        if (o instanceof verwendet) return getUrl().compareTo(((verwendet) o).getUrl());
        else throw new ClassCastException("Incompatible types for verwendet.compareTo(Object o).");
    }

    @Override
    public String toString() {
        return "verwendet{" +
                "url='" + url + '\'' +
                ", steuerbuchummer='" + steuerbuchummer + '\'' +
                '}' + '\n';
    }
}




