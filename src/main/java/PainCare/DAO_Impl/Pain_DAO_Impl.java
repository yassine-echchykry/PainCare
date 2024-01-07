// Pain_DAO_Impl.java
package PainCare.DAO_Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.JDBC.DAO.DAOFactory;

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
                         "VALUES (?, ?, ?, ?, ?, ?)"
            		    ;
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
    }
    
    
    public List<Integer> getLatestPainLevels(int userId) {
    	Connection conn;
		try {
			conn = daoFactory.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        List<Integer> painLevels = new ArrayList<>();

        try {
            conn = daoFactory.getConnection();
            String SQL = "SELECT pain_level FROM pain WHERE user_id = ? ORDER BY id DESC LIMIT 7";
            PreparedStatement statement = conn.prepareStatement(SQL);
            statement.setInt(1, userId);

            ResultSet res = statement.executeQuery();

            while (res.next()) {
                int painLevel = res.getInt("pain_level");
                painLevels.add(painLevel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources in the finally block
            // ...
        }

        return painLevels;
    }

    


}

    // Add more methods if needed

   
