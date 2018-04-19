import java.sql.SQLException;
import java.util.List;

public interface DAO {

    //public ArrayList<DatabaseObject> getDatabaseEntry(Relation type, DatabaseObject key) throws ClassNotFoundException, SQLException, IllegalArgumentException;

    public boolean saveDatabaseEntry(Relation type, DatabaseObject entry) throws ClassNotFoundException, SQLException, IllegalArgumentException;

    //public boolean deleteDatabaseEntry(Relation type, String key) throws ClassNotFoundException, SQLException, IllegalArgumentException;

    public void flushDatabase() throws SQLException;
}