package PainCare.DAO;
import java.sql.SQLException;
import java.util.ArrayList;

import PainCare.Beans.*;


public interface Posts_DAO {
	
		public void create(int user_id, String title, String description, String image) throws SQLException;
		public void update(Posts_Bean post) throws SQLException;
		public void delete(int post_id) throws SQLException;
		public ArrayList<Posts_Bean> all() throws SQLException;
}
