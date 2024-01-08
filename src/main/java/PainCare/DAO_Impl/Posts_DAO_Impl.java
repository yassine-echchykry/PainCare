package PainCare.DAO_Impl;

import com.JDBC.DAO.DAOFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
    
    public ArrayList<Posts_Bean> all() throws SQLException {
    	Connection conn = daoFactory.getConnection();
    	
    	String SQL = "SELECT posts.*, users.name AS user_name "
    			+ "FROM posts "
    			+ "JOIN users ON posts.user_id = users.id "
    			+ "ORDER BY id DESC;";
    	
    	System.out.printf("Noooo");
    	PreparedStatement statement = conn.prepareStatement(SQL);
    	
    	ResultSet res = statement.executeQuery();
    	ArrayList<Posts_Bean> list = new ArrayList<Posts_Bean>();
    	
    	while(res.next()) list.add(getBean(res));
    	
    	res.close();
    	statement.close();
    	conn.close();
    	
    	return list;
    }

    private static Posts_Bean getBean(ResultSet res) throws SQLException {
    	Posts_Bean bean = new Posts_Bean();
    	
    	bean.setID(res.getInt("id"));
    	bean.setUserID(res.getInt("user_id"));
    	bean.setTitle(res.getString("title"));
    	bean.setDescription(res.getString("description"));
    	bean.setDate(res.getDate("date"));
    	
    	
    	return bean;
    }
    
    
}
