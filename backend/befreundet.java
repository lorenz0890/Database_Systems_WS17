public class befreundet extends DatabaseObject {
    private String userID1;
    private String userID2;

    befreundet(String userID1, String userID2) {
        this.userID1 = userID1;
        this.userID2 = userID2;
    }

    public String getUserID1() {
        return userID1;
    }

    public String getUserID2() {
        return userID2;
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof befreundet) return getUserID1().compareTo(((befreundet) o).getUserID1());
        else throw new ClassCastException("Incompatible types for befreundet.compareTo(Object o).");
    }

    @Override
    public String toString() {
        return "befreundet{" +
                "userID1='" + userID1 + '\'' +
                ", userID2='" + userID2 + '\'' +
                '}' + '\n';
    }
}