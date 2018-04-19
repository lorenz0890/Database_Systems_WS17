import java.sql.*;
import java.util.List;

import oracle.jdbc.driver.*;

import javax.security.auth.callback.ConfirmationCallback;



final public class DatabaseDAO implements DAO {

    private String database;
    private String user;
    private String pass;

    // establish connection to database
    private Connection con;
    private Statement stmt;

    DatabaseDAO() throws ClassNotFoundException, SQLException{
        // load drivers and save user data
        Class.forName("oracle.jdbc.driver.OracleDriver");
        database = "jdbc:oracle:thin:@oracle-lab.cs.univie.ac.at:1521:lab";
        user = "a00947188";
        pass = "lorenzk220890";
    }

    public void openConnection(String user, String database, String pass) throws SQLException{
            con = DriverManager.getConnection(database, user, pass);
            stmt = con.createStatement();
    }

    public void openConnection() throws SQLException{
        openConnection(user, database, pass);
    }

    public void closeConnection() { //local exception handling so method can be called in finally block, seems safer
        try {
            stmt.close();
            con.close();
        }
        catch (Exception e) {
            System.err.println("Fehler beim Verbindungsabbau in DatabaseDAO.closeConnection(): " + e.getMessage());
            System.exit(1);
        }

    }

    public void createDB() throws SQLException {
        String s = "CREATE TABLE Firma (" +
                "  SteuernummerUID VARCHAR(64) CONSTRAINT uid_nn NOT NULL," +
                "  Name VARCHAR(64)," +
                "  Firmenbuchnummer VARCHAR(64) CONSTRAINT fbnr_nn NOT NULL," +
                "  CONSTRAINT firma_pk PRIMARY KEY (SteuernummerUID)," +
                "  CONSTRAINT fbnr_uq UNIQUE (Firmenbuchnummer)" +
                ")";
        stmt.executeQuery(s);
        s =     "CREATE TABLE Template (" +
                "  URL VARCHAR(64) CONSTRAINT url_nn NOT NULL," +
                "  Sprache VARCHAR(64)," +
                "  IPAdresse VARCHAR(64) CONSTRAINT ip_nn NOT NULL," +
                "  CONSTRAINT temp_pk PRIMARY KEY (URL)," +
                "  CONSTRAINT ip_uq UNIQUE (IPAdresse)" +
                ")";
        stmt.executeQuery(s);
        s =     "CREATE TABLE verwendet (" +
                "  SteuernummerID VARCHAR(64) CONSTRAINT st_uid_nn NOT NULL," +
                "  URL            VARCHAR(64) CONSTRAINT st_url_nn NOT NULL," +
                "  CONSTRAINT vw_fk1 FOREIGN KEY (SteuernummerID) REFERENCES Firma (SteuernummerUID) ON DELETE CASCADE," +
                "  CONSTRAINT vw_fk2 FOREIGN KEY (URL) REFERENCES Template (URL) ON DELETE CASCADE" +
                ")";
        stmt.executeQuery(s);
        s =     "CREATE TABLE Unterseite(" +
                "  URL VARCHAR(64) CONSTRAINT us_url_nn NOT NULL," +
                "  Pfad VARCHAR(64) CONSTRAINT pfad_nn NOT NULL," +
                "  CONSTRAINT us_fk FOREIGN KEY (URL) REFERENCES Template(URL) ON DELETE CASCADE," +
                "  CONSTRAINT us_pk PRIMARY KEY (URL, Pfad)" +
                ")";
        stmt.executeQuery(s);
        s =     "CREATE TABLE Verkaufsseite(" +
                "  URL VARCHAR(64) CONSTRAINT vs_url_nn NOT NULL," +
                "  Pfad VARCHAR(64) CONSTRAINT vs_pfad_nn NOT NULL," +
                "  Titel VARCHAR(64)," +
                "  CONSTRAINT vs_fk FOREIGN KEY (URL, Pfad) REFERENCES Unterseite(URL, Pfad) ON DELETE CASCADE," +
                "  CONSTRAINT vs_pk PRIMARY KEY (URL, Pfad)" +
                ")";
        stmt.executeQuery(s);
        s =     "CREATE TABLE UserAccount(\n" +
                "  Email VARCHAR(64) CONSTRAINT ua_mail_nn NOT NULL,\n" +
                "  UserID INTEGER CONSTRAINT ua_uid_nn NOT NULL," +
                "  Bank VARCHAR(128)," +
                "  Kontonummer VARCHAR(128)," +
                "  Adresse VARCHAR(128)," +
                "  CONSTRAINT ua_pk PRIMARY KEY (Email)," +
                "  CONSTRAINT ua_uid_uq UNIQUE (UserID)" +
                ")";

        stmt.executeQuery(s);
        s =     "CREATE TABLE befreundet (" +
                "  UserID1 INTEGER CONSTRAINT verw_uid1_nn NOT NULL," +
                "  UserID2 INTEGER CONSTRAINT verw_uid2_nn NOT NULL," +
                "  CONSTRAINT verw_check CHECK (UserID1 != UserID2)," +
                "  CONSTRAINT verw_uq UNIQUE (UserID1, UserID2)," +
                "  CONSTRAINT verw_fk1 FOREIGN KEY (UserID1) REFERENCES UserAccount(UserID) ON DELETE CASCADE," +
                "  CONSTRAINT verw_fk2 FOREIGN KEY (UserID2) REFERENCES UserAccount(UserID) ON DELETE CASCADE" +
                ")";
        stmt.executeQuery(s);
        s =     "CREATE TABLE Produkt(" +
                "  ProduktID INTEGER CONSTRAINT pid_nn NOT NULL ," +
                "  Preis FLOAT," +
                "  Produktname VARCHAR(128)," +
                "  CONSTRAINT prod_pk PRIMARY KEY (ProduktID)," +
                "  CONSTRAINT min_preis CHECK (Preis >= 0)" +
                ")";
        stmt.executeQuery(s);
        s =     "CREATE TABLE kauftEin(" +
                "  URL VARCHAR(64) CONSTRAINT ke_url_nn NOT NULL," +
                "  UserID INTEGER CONSTRAINT ke_uid_nn NOT NULL," +
                "  Pfad VARCHAR(64) CONSTRAINT ke_pfad_nn NOT NULL," +
                "  ProduktID INTEGER CONSTRAINT ke_pid_nn NOT NULL," +
                "  Anzahl INTEGER CONSTRAINT anz_nn NOT NULL," +
                "  CONSTRAINT ke_fk1 FOREIGN KEY (URL, Pfad) REFERENCES Verkaufsseite(URL, Pfad) ON DELETE CASCADE," +
                "  CONSTRAINT ke_fk2 FOREIGN KEY (ProduktID) REFERENCES Produkt(ProduktID) ON DELETE CASCADE," +
                "  CONSTRAINT ke_fk3 FOREIGN KEY (UserID) REFERENCES UserAccount(UserID) ON DELETE CASCADE," +
                "  CONSTRAINT kaufChk CHECK (Anzahl > 0)" +
                ")";
        stmt.executeQuery(s);
        s=  "CREATE SEQUENCE userid_seq START WITH 1";
        stmt.executeQuery(s);
        s =     "CREATE OR REPLACE TRIGGER userid_t BEFORE INSERT ON UserAccount FOR EACH ROW BEGIN SELECT userid_seq.NEXTVAL INTO :new.UserID FROM dual; END;";
        stmt.executeQuery(s);
        s=      "CREATE OR REPLACE PROCEDURE firma_temp( arg1 IN VARCHAR2, arg2 OUT VARCHAR2) IS" +
                " BEGIN" +
                "  SELECT URL INTO arg2 FROM verwendet WHERE SteuernummerID = arg1;" +
                "  EXCEPTION" +
                "    WHEN NO_DATA_FOUND THEN arg2 := 'Keine';" +
                " END;";
        stmt.executeQuery(s);
    }

    public void dropDB(){
        String s = "";
        try {  s = "DROP TABLE befreundet"; stmt.executeQuery(s); } catch (Exception e) {System.out.println(e.getMessage());}
        try {  s = "DROP TABLE kauftEin";; stmt.executeQuery(s); } catch (Exception e) {System.out.println(e.getMessage());}
        try {  s = "DROP TABLE verwendet";; stmt.executeQuery(s); } catch (Exception e) {System.out.println(e.getMessage());}
        try {  s = "DROP TABLE Verkaufsseite";; stmt.executeQuery(s); } catch (Exception e) {System.out.println(e.getMessage());}
        try {  s = "DROP TABLE Unterseite";; stmt.executeQuery(s); } catch (Exception e) {System.out.println(e.getMessage());}
        try {  s = "DROP TABLE Template";; stmt.executeQuery(s); } catch (Exception e) {System.out.println(e.getMessage());}
        try {  s = "DROP TABLE UserAccount";; stmt.executeQuery(s); } catch (Exception e) {System.out.println(e.getMessage());}
        try {  s = "DROP TABLE Produkt";; stmt.executeQuery(s); } catch (Exception e) {System.out.println(e.getMessage());}
        try {  s = "DROP TABLE Firma";; stmt.executeQuery(s); } catch (Exception e) {System.out.println(e.getMessage());}
        try {  s = "DROP SEQUENCE userid_seq";; stmt.executeQuery(s); } catch (Exception e) {System.out.println(e.getMessage());}
        try {  s = "DROP PROCEDURE firma_temp";; stmt.executeQuery(s); } catch (Exception e) {System.out.println(e.getMessage());}
    }

    public void printTableSize(String tablename) throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM " + tablename);
        if (rs.next()) {
            int count = rs.getInt(1);
            System.out.println("Number of datasets in table " + tablename + ": "+ count);
        }
        rs.close();
    }

    private boolean insertSQL (String values, String tablename) throws SQLException {
        String insertSql = "INSERT INTO " + tablename + " VALUES " + values;
        stmt.executeUpdate(insertSql);
        return true;
    }

    public void flushTable(String tablename) throws SQLException{
        stmt.executeQuery("DELETE FROM " + tablename);
    }

    public void flushDatabase() throws  SQLException {
        for ( Relation r : Relation.values())
            flushTable(r.toString());
    }
/*
    public ArrayList<DatabaseObject> getDatabaseEntry(Relation type, DatabaseObject key) throws ClassNotFoundException, SQLException, IllegalArgumentException {

    }
*/
/*
    public boolean deleteDatabaseEntry(Relation type, String key) throws ClassNotFoundException, SQLException, IllegalArgumentException{}
 */
    public boolean saveDatabaseEntry(Relation type, DatabaseObject entry){
        boolean success = false;
        try{
            switch (type) {
                case Firma: {
                    Firma e = (Firma)entry;
                    String values = "(" + e.getSteuerbuchummer() + "," + e.getName() + "," + e.getFirmenbuchnummer() + ")";
                    //printTableSize(Relation.Firma.toString());
                    success = insertSQL(values, Relation.Firma.toString());
                } break;
                case Template: {
                    Template e = (Template) entry;
                    String values = "(" + e.getUrl() + "," + e.getSprache() + "," + e.getIp() + ")";
                    //printTableSize(Relation.Template.toString());
                    success = insertSQL(values, Relation.Template.toString());
                } break;
                case verwendet: {
                    verwendet e = (verwendet) entry;
                    String values = "(" + e.getSteuerbuchummer() + "," + e.getUrl() + ")";
                    //printTableSize(Relation.verwendet.toString());
                    success = insertSQL(values, Relation.verwendet.toString());
                } break;
                case Unterseite: {
                    Unterseite e = (Unterseite) entry;
                    String values = "(" + e.getUrl() + "," + e.getPfad() + ")";
                    //printTableSize(Relation.Unterseite.toString());
                    success = insertSQL(values, Relation.Unterseite.toString());
                } break;
                case Verkaufsseite: {
                    Verkaufsseite e = (Verkaufsseite) entry;
                    String values = "(" + e.getUrl() + "," + e.getPfad() + ", " + e.getTitel() + ")";
                    //printTableSize(Relation.Verkaufsseite.toString());
                    success = insertSQL(values, Relation.Verkaufsseite.toString());
                } break;
                case UserAccount: {
                    UserAccount e = (UserAccount) entry;
                    String values = "(" + e.getEmail() + "," + e.getUserID() + "," + e.getBankname() + "," + e.getKontonummer() + "," + e.getAdresse() + ")";
                    //printTableSize(Relation.UserAccount.toString());
                    success = insertSQL(values, Relation.UserAccount.toString());
                } break;
                case befreundet: {
                    befreundet e = (befreundet) entry;
                    String values = "(" + e.getUserID1() + "," + e.getUserID2() + ")";
                    //printTableSize(Relation.befreundet.toString());
                    success = insertSQL(values, Relation.befreundet.toString());
                } break;
                case Produkt: {
                    Produkt e = (Produkt) entry;
                    String values = "(" + e.getProduktID() + "," + e.getPreis() + "," + e.getProduktname() + ")";
                    //printTableSize(Relation.Produkt.toString());
                    success = insertSQL(values, Relation.Produkt.toString());
                } break;
                case kauftEin: {
                    kauftEin e = (kauftEin) entry;
                    String values = "(" + e.getUrl() + "," + e.getUserID() + "," + e.getPfad() + "," + e.getProduktID() + "," + e.getAnzahl() + ")";
                    //printTableSize(Relation.kauftEin.toString());
                    success = insertSQL(values, Relation.kauftEin.toString());
                } break;
                default:
                    throw new IllegalArgumentException("Parameter ungueltig in DatabaseDAO.saveDatabaseEntry(Relation type, DatabaseObject entry).");
            }
        }
        catch (Exception e)
        {
            System.err.println("Fehler beim Einfuegen des Datensatzes in DatabaseDAO.saveDatabaseEntry(Relation type, DatabaseObject entry): " + e.getMessage());
            success = false;
        }
        return success;
    }

    public Connection getCon() {
        return con;
    }

    public Statement getStmt() {
        return stmt;
    }
}
