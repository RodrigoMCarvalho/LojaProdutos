package connection;

import java.sql.*;

public class ConnectionFactory {

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/dbloja";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() {

        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException("Erro na conexão: ", ex);
        }
    }

    //método para fechar a conexão(Connection)
    public static void closeConnection(Connection conexao) {
        if (conexao != null) {
            try {
                conexao.close();
            } catch (SQLException ex) {
                System.err.println("Erro:" + ex);
            }
        }
    }

    //método para fechar a conexão(PreparedStatement)(Sobrecarga)
    public static void closeConnection(Connection conexao, PreparedStatement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException ex) {
                System.err.println("Erro:" + ex);
            }
        }
        closeConnection(conexao);//chama o 1º método closeConncetion()
    }

    //método para fechar a conexão(ResultSet)(Sobrecarga)
    public static void closeConnection(Connection conexao, PreparedStatement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                System.err.println("Erro:" + ex);
            }
        }
        closeConnection(conexao, stmt);//chama o 2º método closeConncetion()
    }
}
