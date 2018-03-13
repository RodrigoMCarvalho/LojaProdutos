package model.dao;

import model.bean.Categoria;
import model.bean.Produto;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author rodri_000
 */
public class ProdutoDAOTest {

    public ProdutoDAOTest() {
    }

    @Test
    @Ignore
    public void inserir() {
        Categoria cat = new Categoria();
        cat.setId(3);   //cat setado para o ID 1 da tabela Categoria.

        Produto produto = new Produto();
        produto.setDescricao("macarrão");
        produto.setQtd(35);
        produto.setValor(3.50);
        produto.setCategoria(cat);

        ProdutoDAO dao = new ProdutoDAO();

        if (dao.inserir(produto)) {
            System.out.println("Dados inserido com sucesso!");
        } else {
            fail("Falha ao inserir dados!");
        }

    }

    @Test
    @Ignore
    public void alterar() {
        Produto produto = new Produto();          //necessário instanciar Produto para setar os dados
        produto.setValor(7.00);
        produto.setId(2);

        ProdutoDAO dao = new ProdutoDAO();        //necessário instalar ProdutoDAO para executar os comandos SQL

        if (dao.alterar(produto)) {
            System.out.println("Dados alterados com sucesso!");
        } else {
            fail("Falha ao alterar os dados!");
        }
    }

    @Test
    public void busca() {
        ProdutoDAO dao = new ProdutoDAO();

        for (Produto p : dao.BuscaTodos()) {
            System.out.println("Descrição Produto: " + p.getDescricao()
                    + " - Valor: R$" + p.getValor()
                    + " - Descrição Categoria:" + p.getCategoria().getDescricao()); //Composição da classe Categoria
        }
    }

}
