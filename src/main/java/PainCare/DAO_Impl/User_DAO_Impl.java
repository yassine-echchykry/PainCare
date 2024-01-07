package PainCare.DAO_Impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.JDBC.DAO.*;
import PainCare.Beans.*;
import PainCare.DAO.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class User_DAO_Impl implements User_DAO {
	private DAOFactory daoFactory;

    public User_DAO_Impl( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }
    
    private static User_Bean getBean(ResultSet res) throws SQLException {
    	User_Bean bean = new User_Bean();
    	
    	bean.setID(res.getInt("id"));
    	bean.setName(res.getString("name"));
    	
    	bean.setEmail(res.getString("email"));
    	
    	return bean;
    }
    
    public User_Bean login(String email, String password) throws SQLException {
    	Connection conn = daoFactory.getConnection();
    	String SQL = "SELECT * FROM users WHERE email = ? AND password = ?;";
    	PreparedStatement statement = conn.prepareStatement(SQL);
    	
    	statement.setString(1, email);
    	statement.setString(2, password);
    	
    	ResultSet res = statement.executeQuery();
    	User_Bean bean = res.next() ? getBean(res) : null;
    	
    	res.close();
    	statement.close();
    	conn.close();
    	
    	return bean;
    }
    
    @Override
    public void register(String username, String email, String password) throws SQLException {
    	Connection conn = daoFactory.getConnection();
    	String SQL = "INSERT INTO users (name, email, password) VALUES (?, ?, ?);";
    	PreparedStatement statement = conn.prepareStatement(SQL);
    	
    	statement.setString(1, username);
    	statement.setString(2, email);
    	statement.setString(3, password);
    	
    	statement.execute();
    	
    	statement.close();
    	conn.close();
    }
    
    @Override
    public void update(User_Bean bean) throws SQLException {
    	Connection conn = daoFactory.getConnection();
    	String SQL = "UPDATE users SET name = ?, email = ?, avatar = ?, birthDay = ? WHERE id = ?;";
    	PreparedStatement statement = conn.prepareStatement(SQL);
    	
    	statement.setString(1, bean.getName());
    	statement.setString(2, bean.getEmail());
    	statement.setInt(5, bean.getID());
    	
    	statement.execute();
    	
    	statement.close();
    	conn.close();
    }
    
    @Override
    public void delete(int id) throws SQLException {
    	Connection conn = daoFactory.getConnection();
    	String SQL = "DELETE FROM users WHERE id = ?;";
    	PreparedStatement statement = conn.prepareStatement(SQL);
    	
    	statement.setInt(1, id);
    	
    	statement.execute();
    	
    	statement.close();
    	conn.close();
    }
    
    
  
    
    @Override
    public User_Bean auth(HttpServletRequest request) {
    	HttpSession session = request.getSession();
    	User_Bean bean = (User_Bean) session.getAttribute("user");
    	
    	return bean;
    }
}

