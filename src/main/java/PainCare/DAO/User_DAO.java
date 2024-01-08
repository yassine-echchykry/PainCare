package PainCare.DAO;


import java.sql.SQLException;

import jakarta.servlet.http.HttpServletRequest;
import PainCare.Beans.*; 

public interface User_DAO {
	public void update(User_Bean bean) throws SQLException;
	public void delete(int id) throws SQLException;
	public User_Bean login(String email, String password) throws SQLException;
	public void register(String username, String email, String password) throws SQLException;
	public User_Bean getUserById(int userId) throws SQLException;
}

