package model.dao;

import java.sql.*;
import connection.ConnectionFactory; //pacote Java criado para o projeto
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.bean.Categoria;

public class CategoriaDAO {

    private Connection conexao = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    public CategoriaDAO() {
        conexao = ConnectionFactory.getConnection(); //variável conexao recebe o método getConnection
    }

    public boolean inserir(Categoria categoria) { //retorna boolean
        String sql = "INSERT INTO categoria (descricao) VALUES (?)";
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

    public List<Categoria> buscaTodos() {
        List<Categoria> ListCat = new ArrayList<>(); //lista para armazenar os dados do BD
        String sql = "SELECT * FROM categoria";

        try {
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();

            //percorrer os dados do "rs"
            while (rs.next()) {  //enquanto "rs" possuir dados ele vai para o "próximo"
                Categoria cat = new Categoria();
                cat.setDescricao(rs.getString("descricao")); //nome do título da coluna na tabela
                ListCat.add(cat);

            }
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);

        } finally {
            ConnectionFactory.closeConnection(conexao, stmt, rs); //fechando a conexão
        }
        return ListCat;
    }

}
