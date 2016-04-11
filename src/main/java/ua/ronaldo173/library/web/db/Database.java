package ua.ronaldo173.library.web.db;

import ua.ronaldo173.library.web.beans.Genre;

import java.sql.*;

/**
 * Created by Developer on 11.04.2016.
 */
public class Database {
    private static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/library";
        String user = "root";
        String password = "root";

        return DriverManager.getConnection(url, user, password);
    }

    /**
     * return ResultSet by query, ResultSet have to be closed after use!
     *
     * @param query
     * @return
     */
    public static ResultSet getResultByQuery(String query) {

        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            return statement.executeQuery(query);

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    public static void main(String[] args) {
        ResultSet resultByQuery = null;
        try {
            resultByQuery = Database.getResultByQuery("select * from genre");

            while (resultByQuery.next()) {
                Genre genre = new Genre();
                genre.setId(resultByQuery.getInt("id"));
                genre.setName(resultByQuery.getString("name"));

                System.out.println(genre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultByQuery != null) {
                try {
                    resultByQuery.getStatement().getConnection().close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
