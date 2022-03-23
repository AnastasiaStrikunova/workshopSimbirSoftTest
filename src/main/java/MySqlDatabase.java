import java.sql.*;
import java.util.LinkedHashMap;

public class MySqlDatabase {
    String userName = "root";
    String password = "1234";
    String connectionUrl = "jdbc:mysql://localhost:3306/statistics";

    void createTable () throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.cj.jdbc.Driver");

        try(java.sql.Connection connection = DriverManager.getConnection(connectionUrl, userName, password);
            Statement statement = connection.createStatement()) {

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Tmp (\n" +
                    "    id MEDIUMINT NOT NULL AUTO_INCREMENT,\n" +
                    "    word CHAR(30),\n" +
                    "    count int,\n" +
                    "    PRIMARY KEY (id)\n" +
                    ")");
        }
    }

    void addInTable (LinkedHashMap<String, Integer> map) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        try(java.sql.Connection connection = DriverManager.getConnection(connectionUrl, userName, password);
            Statement statement = connection.createStatement()) {

            for (String key : map.keySet()) {
                String query = "insert  into tmp (word, count) values(?,?)";//,
                PreparedStatement preparedStmt = connection.prepareStatement(query);
                preparedStmt.setString(1, key);
                preparedStmt.setString(2, map.get(key).toString());
                preparedStmt.execute();
            }
        }
    }

    String showTable () throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.cj.jdbc.Driver");

        try(java.sql.Connection connection = DriverManager.getConnection(connectionUrl, userName, password);
            Statement statement = connection.createStatement()) {

            StringBuilder str = new StringBuilder();
            ResultSet resultSet = statement.executeQuery("select * from tmp");
            while (resultSet.next()){
                str.append(resultSet.getString("word")).append(" - ").append(resultSet.getString("count")).append("\n");
            }
            return str.toString();
        }
    }

    void dropTable() throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.cj.jdbc.Driver");

        try(java.sql.Connection connection = DriverManager.getConnection(connectionUrl, userName, password);
            Statement statement = connection.createStatement()) {

            statement.executeUpdate("drop table tmp");
        }
    }
}
