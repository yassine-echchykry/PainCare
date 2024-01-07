// Pain_DAO.java
package PainCare.DAO;
import java.util.List;


public interface Pain_DAO {
	void insertPainData(int userId, int painLevel, String[] locations, String[] symptoms, String[] worsePain, String[] feelings);
    // Add more methods if needed
	List<Integer> getLatestPainLevels(int userId);
}
