// Session_DAO.java
package PainCare.DAO;

import PainCare.Beans.Session_Bean;

public interface Session_DAO {
    void insertSession(Session_Bean sessionBean);
    String getUsernameByToken(String token);
}
