import java.sql.SQLException;

public class UserInterface {
    private enum Command {
        filldb, flushdb, createdb, dropdb
    }

    private static final Command checkInput(String s){
        Command[] command = Command.values();
        for(int i = 0; i < command.length; i++) if (command[i].toString().equals(s)) return command[i];
        throw new IllegalArgumentException("Commandline Parameter nicht erkannt. Kommandos: createdb, dropdb, filldb, flushdb");
    }

    public static void main(String args[]) {
        DatabaseDAO db = null;
        try {
            Command command = checkInput(args[0]);
            db = new DatabaseDAO();
            db.openConnection();

            switch(command){
                case createdb: {
                    System.out.println("Creating DB...");
                    db.createDB();
                    System.out.println("Done.");
                } break;
                case dropdb:{
                    System.out.println("Dropping DB...");
                    db.dropDB();
                    System.out.println("Done.");
                } break;
                case filldb: {
                    System.out.println("Generating data and filling DB (this may take some time).");
                    TestDataGenerator tdg =  new TestDataGenerator(db);
                    tdg.testData(Relation.Firma, 2000);
                    tdg.testData(Relation.Template, 2000);
                    tdg.testData(Relation.verwendet, 750);
                    tdg.testData(Relation.Unterseite, 850);
                    tdg.testData(Relation.Verkaufsseite, 850);
                    tdg.testData(Relation.UserAccount, 3000);
                    tdg.testData(Relation.Produkt, 3000);
                    tdg.testData(Relation.befreundet, 500);
                    tdg.testData(Relation.kauftEin, 900);
                    System.out.println("Done.");
                } break;
                case flushdb: {
                    System.out.println("Flushing DB");
                    db.flushDatabase();
                    System.out.println("Done.");
                } break;
                default: {throw new IllegalArgumentException("Commandline Parameter nicht erkannt. Kommandos: createdb, dropdb, filldb, flushdb");}
            }
        }
        catch (SQLException e) {
            System.err.println("Ein Fehler ist bei der Kommunikation mit der Datenbank aufgetreten: "+ e.getMessage());
            if (!db.equals(null)) db.closeConnection();
            System.exit(1);
        }
        catch (ClassNotFoundException e) {
            System.out.println("Klasse nicht gefunden: " + e.getMessage() + "\n"
                + "Wurde der Oracle Treiber korrekt eingebunden? Sytanx: java -cp .:/home/<Pfad>/ojdbc14.jar UserInterface <Kommando>."
            );
            if (!db.equals(null)) db.closeConnection();
            System.exit(1);
        }
        catch (Exception e) {
            System.err.println("Ein Fehler ist aufgetreten: "+ e.getMessage()+ "\n"
                    + "Wurde der Oracle Treiber korrekt eingebunden? Sytanx: java -cp .:/home/<Pfad>/ojdbc14.jar UserInterface <Kommando>."
            );
            if (!db.equals(null)) db.closeConnection();
            System.exit(1);
        }
        finally{
            if (!db.equals(null)) db.closeConnection();
            System.exit(0);
        }
    }
}


// run from commandline java -cp .:/home/lorenz/Code/dbs_java_l/dbs_java_l/ojdbc14.jar UserInterface filldb