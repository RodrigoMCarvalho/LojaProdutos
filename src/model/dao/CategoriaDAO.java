package model.dao;

import java.sql.*;
import connection.ConnectionFactory; //pacote Java criado para o projeto
import model.bean.Categoria;

public class CategoriaDAO {

    private Connection conexao = null;
    PreparedStatement stmt = null;
    String sql = "INSERT INTO categoriasasa (descricao) VALUES (?)";

    public CategoriaDAO() {
        conexao = ConnectionFactory.getConnection(); //variável conexao recebe o método getConnection
    }

    public boolean inserir(Categoria categoria) { //retorna boolean
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, categoria.getDescricao());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
            return false;
            //a linha abaixo fecha a conexão criada
        } finally {
            ConnectionFactory.closeConnection(conexao, stmt);
        }
    }

}
