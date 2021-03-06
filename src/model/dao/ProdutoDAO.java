package model.dao;

import connection.ConnectionFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.bean.Categoria;

import model.bean.Produto;

public class ProdutoDAO {

    private Connection conexao = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    CallableStatement cs = null;

    public ProdutoDAO() {
        conexao = ConnectionFactory.getConnection();
    }

    public boolean inserir(Produto produto) {
        String sql = "INSERT INTO produto (descricao,qtd,valor,categoria_id) VALUES(?,?,?,?)";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, produto.getDescricao());
            stmt.setInt(2, produto.getQtd());
            stmt.setDouble(3, produto.getValor());
            stmt.setInt(4, produto.getCategoria().getId()); //composição de Categoria, obtendo o ID em outra classe
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro:" + ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(conexao, stmt);
        }
    }

    public boolean alterar(Produto produto) {
        String sql = "UPDATE produto set valor = ? WHERE id = ? ";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setDouble(1, produto.getValor());
            stmt.setInt(2, produto.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro:" + ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(conexao, stmt); //fecha a conexão
        }
    }

    public List<Produto> BuscaTodos() {
        List<Produto> listProd = new ArrayList<>();
        String sql = "SELECT * FROM vw_produto_categoria";  //select usando a VIEW criada no MySql

        try {
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Produto produto = new Produto();
                produto.setDescricao(rs.getString("pnome"));
                produto.setValor(rs.getInt("valor"));           //obtém os dados da tabela produto
                produto.setId(rs.getInt("pid"));

                Categoria cat = new Categoria();
                cat.setId(rs.getInt("cid"));               //obtém os dados da tabela categoria
                cat.setDescricao(rs.getString("cdesc"));

                produto.setCategoria(cat);                //seta os dados de Categoria em Produto

                listProd.add(produto);       //adiciona os dados das tabelas na lista criada

            }
        } catch (SQLException ex) {
            System.err.println("Erro:" + ex);
        } finally {
            ConnectionFactory.closeConnection(conexao, stmt);  //fecha a conexão
        }
        return listProd;
    }

    public List<Produto> buscarProdutosProcedure(String nome) {
        List<Produto> produtos = new ArrayList<>();
        String procedure = "CALL `dbloja`.`sp_find_produtos`(?)";
        try {
            cs = conexao.prepareCall(procedure);
            cs.setString(1, "%" + nome + "%");
            cs.executeQuery();
            rs = cs.executeQuery();
            while (rs.next()) {         //obtém os dados da tabela produto
                Produto produto = new Produto();
                produto.setDescricao(rs.getString("descricao"));
                produto.setQtd(rs.getInt("qtd"));
                produto.setValor(rs.getDouble("valor"));

                produtos.add(produto);       //adiciona os dados das tabelas na lista criada
            }
            return produtos;
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
            //a linha abaixo fecha a conexão criada
        } finally {
            ConnectionFactory.closeConnection(conexao, stmt);
        }
        return null;
    }

}
