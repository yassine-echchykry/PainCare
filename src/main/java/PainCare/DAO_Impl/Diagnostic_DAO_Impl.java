package PainCare.DAO_Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.JDBC.DAO.*;
import PainCare.DAO.*;
import PainCare.Beans.*;

public class Diagnostic_DAO_Impl implements Diagnostic_DAO {
    private DAOFactory daoFactory;

    public Diagnostic_DAO_Impl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    private static Diagnostic_Bean getBean(ResultSet res) throws SQLException {
        Diagnostic_Bean bean = new Diagnostic_Bean();
        
        bean.setId(res.getInt("id"));
        bean.setUser_id(res.getInt("user_id"));
        bean.setReponses(res.getString("reponses"));
        
        return bean;
    }

    @Override
    public void creer(String reponses, int user_id) throws SQLException {
        Connection conn = daoFactory.getConnection();
        String SQL = "INSERT INTO diagnostics (reponses, user_id) VALUES (?, ?);";
        PreparedStatement statement = conn.prepareStatement(SQL);
        
        statement.setString(1, reponses);
        statement.setInt(2, user_id);
        
        statement.execute();
        
        statement.close();
        conn.close();
    }

    @Override
    public Diagnostic_Bean Dernier_Test(int user_id) throws SQLException {
        Connection conn = daoFactory.getConnection();
        String SQL = "SELECT * FROM diagnostics WHERE user_id = ? ORDER BY id DESC LIMIT 1;";
        PreparedStatement statement = conn.prepareStatement(SQL);
        
        statement.setInt(1, user_id);
        
        ResultSet res = statement.executeQuery();
        Diagnostic_Bean bean = res.next() ? getBean(res) : null;
        
        statement.close();
        conn.close();
        
        return bean;
    }
}
