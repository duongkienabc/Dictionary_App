package DictionaryCommandLine.data;

import java.sql.*;


public class SQLDatabase {
    //Khởi tạo cac thong so ket noi toi file csdl
    private static final String URL = "jdbc:mysql://localhost:3306/ten_cua_co_so_du_lieu";
    private static final String username = "username";
    private static final String password = "password";
    //Thiet lap ket noi toi csdl
    private static Connection getConnect() throws SQLException {
        return DriverManager.getConnection(URL, username, password);
    }
    //Thuc thi truy van select
    public static ResultSet executeQuery(String query) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnect();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(connection, statement);
        }
        return resultSet;
    }
    //truy van insert, update hoac delete
    public static int executeUpdate(String query) {
        Connection connection = null;
        PreparedStatement statement = null;
        int result = 0;
        try {
            connection = getConnect();
            statement = connection.prepareStatement(query);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            //tat ca cac resource dc dong khi khong can thiet
            close(connection, statement);
        }
        return result;
    }
    //dong ket noi resource va tuyen bo PreparedStatement
    private static void close(Connection connection, PreparedStatement statement) {
        try {
            if(statement != null) {
                statement.close();
            } if (connection != null) {
                statement.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
