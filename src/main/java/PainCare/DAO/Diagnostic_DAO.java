package PainCare.DAO;

import PainCare.Beans.*;

import java.sql.SQLException;

public interface Diagnostic_DAO {
    void creer(String reponses, int user_id) throws SQLException;
    Diagnostic_Bean Dernier_Test(int user_id) throws SQLException;
}
