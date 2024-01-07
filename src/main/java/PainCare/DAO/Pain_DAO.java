// Pain_DAO.java
package PainCare.DAO;

import PainCare.Beans.Pain_Bean;

public interface Pain_DAO {
	void insertPainData(int userId, int painLevel, String[] locations, String[] symptoms, String[] worsePain, String[] feelings);
    // Add more methods if needed
}
