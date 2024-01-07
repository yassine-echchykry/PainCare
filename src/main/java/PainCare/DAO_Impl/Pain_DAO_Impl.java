// Pain_DAO_Impl.java
package PainCare.DAO_Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.JDBC.DAO.DAOFactory;
import PainCare.Beans.Pain_Bean;
import PainCare.DAO.Pain_DAO;

public class Pain_DAO_Impl implements Pain_DAO {
    private DAOFactory daoFactory;

    public Pain_DAO_Impl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void insertPainData(int userId, int painLevel, String[] locations, String[] symptoms, String[] worsePain, String[] feelings) {
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = daoFactory.getConnection();
            String SQL = "INSERT INTO pain (user_id, pain_level, locations, symptoms, worse_pain, feelings) " +
                         "VALUES (?, ?, ?, ?, ?, ?)";
            statement = conn.prepareStatement(SQL);

            statement.setInt(1,userId);
            statement.setInt(2, painLevel);
            statement.setString(3, String.join(",", locations));
            statement.setString(4, String.join(",", symptoms));
            statement.setString(5, String.join(",", worsePain));
            statement.setString(6, String.join(",", feelings));

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            
        }
    }}

    // Add more methods if needed

   
