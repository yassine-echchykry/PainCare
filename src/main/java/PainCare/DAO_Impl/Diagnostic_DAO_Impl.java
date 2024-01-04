package PainCare.DAO_Impl;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import com.JDBC.DAO.DAOException;
import com.JDBC.DAO.DAOFactory;
import PainCare.DAO.*;
import PainCare.Beans.*;

public class Diagnostic_DAO_Impl implements Diagnostic_DAO {

    private DAOFactory daoFactory;

    public Diagnostic_DAO_Impl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void create(Diagnostic_Bean diagnostic) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = daoFactory.getConnection();
            String sql = "INSERT INTO Diagnostic (user_id, reponses) VALUES (?, ?)";
            preparedStatement = initPreparedStatement(connection, sql,
                    diagnostic.getUser_id(), arrayToString(diagnostic.getReponses()));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            // Gestion de la fermeture des ressources (à ajouter)
        }
    }

   

    // Ajoutez d'autres méthodes si nécessaire

    private static PreparedStatement initPreparedStatement(Connection connection, String sql, Object... objects) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        for (int i = 0; i < objects.length; i++) {
            preparedStatement.setObject(i + 1, objects[i]);
        }
        return preparedStatement;
    }

    private static String arrayToString(int[] array) {
        StringBuilder result = new StringBuilder();
        for (int i : array) {
            result.append(i).append(",");
        }
        return result.length() > 0 ? result.substring(0, result.length() - 1) : "";
    }
}

