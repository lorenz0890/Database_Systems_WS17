public class Template extends DatabaseObject  {
    private String url;
    private String sprache;
    private String ip;
    Template(String url, String sprache, String ip){
        this.url = url;
        this.sprache = sprache;
        this.ip = ip;
    }
    public String getUrl() {
        return url;
    }
    public String getSprache() {
        return sprache;
    }

    public String getIp() {
        return ip;
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Template) return getUrl().compareTo(((Template) o).getUrl());
        else throw new ClassCastException("Incompatible types for Template.compareTo(Object o).");
    }

    @Override
    public String toString() {
        return "Template{" +
                "url='" + url + '\'' +
                ", sprache='" + sprache + '\'' +
                ", ip='" + ip + '\'' +
                '}' + '\n';
    }
}
