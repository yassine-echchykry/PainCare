package PainCare.DAO;



import java.sql.SQLException;
import java.util.ArrayList;

import PainCare.Beans.Comment_Bean;

public interface Comment_DAO {
	public void create(int user_id, int blog_id, String content) throws SQLException;
	public void delete(int comment_id) throws SQLException;
	public ArrayList<Comment_Bean> postComments(int post_id) throws SQLException;
}
