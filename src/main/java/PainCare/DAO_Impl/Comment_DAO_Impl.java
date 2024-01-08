package PainCare.DAO_Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import com.JDBC.DAO.*;
import PainCare.DAO.*;
import PainCare.Beans.*;

public class Comment_DAO_Impl implements Comment_DAO {
	private DAOFactory daoFactory;

    public Comment_DAO_Impl( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }
    
    
    private static Comment_Bean getBean(ResultSet res) throws SQLException {
    	Comment_Bean bean = new Comment_Bean();
    	
    	bean.setID(res.getInt("id"));
    	bean.setUserID(res.getInt("user_id"));
    	bean.setPostID(res.getInt("post_id"));
    	bean.setContenu(res.getString("contenu"));
    	bean.setDate(res.getTimestamp("date"));
    	
    	return bean;
    }
    
    
    @Override
    public void create(int user_id, int post_id, String contenu) throws SQLException {
    	Connection conn = daoFactory.getConnection();
		String SQL = "INSERT INTO comments (user_id, post_id, contenu) VALUES(?, ?, ?);";
		PreparedStatement statement = conn.prepareStatement(SQL);
    	
		statement.setInt(1, user_id);
		statement.setInt(2, post_id);
		statement.setString(3, contenu);
		
		statement.execute();
		
		statement.close();
		conn.close();
    }
    
    @Override
    public void delete(int comment_id) throws SQLException {
    	Connection conn = daoFactory.getConnection();
    	String SQL = "DELETE FROM comments WHERE id = ?;";
    	PreparedStatement statement = conn.prepareStatement(SQL);
    	
    	statement.setInt(1, comment_id);
    	
    	statement.execute();
    	
    	statement.close();
    	conn.close();
    }
    
    public ArrayList<Comment_Bean> postComments(int post_id) throws SQLException {
    	
    	Connection conn = daoFactory.getConnection();
    	
    	String SQL = 
    		"SELECT comments.*, users.name AS user_name "
			+ "FROM comments "
			+ "JOIN users ON comments.user_id = users.id "
			+ "JOIN posts ON comments.post_id = posts.id "
			+ "WHERE posts.id = ? ORDER BY comments.id DESC;";
    	
    	PreparedStatement statement = conn.prepareStatement(SQL);
    	
    	statement.setInt(1, post_id);
    	
    	ResultSet res = statement.executeQuery();
    	System.out.printf("§§§§§");
    	ArrayList<Comment_Bean> list = new ArrayList<Comment_Bean>();
    	System.out.printf("§§§§§");
    	while(res.next()) list.add(getBean(res));
    	System.out.println("\n List size: " + list.size());
    	res.close();
    	statement.close();
    	conn.close();
    	System.out.printf("§§§§§", list);
    	return list;
    }
 
    
}
