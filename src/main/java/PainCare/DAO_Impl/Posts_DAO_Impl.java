package PainCare.DAO_Impl;

import com.JDBC.DAO.DAOFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.JDBC.DAO.*;
import PainCare.DAO.*;
import PainCare.Beans.*;

public class Posts_DAO_Impl {
	
	private DAOFactory daoFactory;
	
	public Posts_DAO_Impl(DAOFactory daoFactory) {
	    
		 this.daoFactory = daoFactory;
		
	}
	
    public void create(int user_id, String title, String description, String image) throws SQLException {
    	System.out.printf("dao dkhl");
    	Connection conn = daoFactory.getConnection();
    	System.out.printf("dao dkhl");
		String SQL = "INSERT INTO posts (user_id, title, description, image) VALUES(?, ?, ?, ?);";
		PreparedStatement statement = conn.prepareStatement(SQL);
    	
		statement.setInt(1, user_id);
		statement.setString(2, title);
		statement.setString(3, description);
		statement.setString(4, image);
		
		
		statement.execute();
		
		statement.close();
		conn.close();
    }
    
   
    public void delete(int post_id) throws SQLException {
    	Connection conn = daoFactory.getConnection();
    	String SQL = "DELETE FROM blogs WHERE id = ?;";
    	PreparedStatement statement = conn.prepareStatement(SQL);
    	
    	statement.setInt(1, post_id);
    	
    	statement.execute();
    	
    	statement.close();
    	conn.close();
    }
    
    public void update(Posts_Bean bean) throws SQLException {
    	Connection conn = daoFactory.getConnection();
    	String SQL = "UPDATE blogs SET title = ?, description = ?, image = ? WHERE id = ?;";
    	PreparedStatement statement = conn.prepareStatement(SQL);
    	
    	statement.setString(1, bean.getTitle());
    	statement.setString(2, bean.getDescription());
    	statement.setString(3, bean.getImage());
    	statement.setInt(4, bean.getID());
    	
    	statement.execute();
    	
    	statement.close();
    	conn.close();
    }
}
