package model.dao;

import org.junit.Test;
import static org.junit.Assert.*;
import model.bean.*;
import org.junit.Ignore;

/**
 *
 * @author rodri_000
 */
public class CategoriaDAOTest {

    public CategoriaDAOTest() {
    }

    @Test
    @Ignore   //ignora o método abaixo, para testar o seguinte
    public void inserir() {

        Categoria cat = new Categoria("Roupas");
        CategoriaDAO dao = new CategoriaDAO();

        //método inserir() retorna um boolean
        if (dao.inserir(cat)) {
            System.out.println("Salvo com sucesso!");
        } else {
            fail("Erro ao salvar!");
        }
    }

    @Test   //necessário para testar com o JUnit
    public void listar() {
        CategoriaDAO dao = new CategoriaDAO();

        for (Categoria cat : dao.buscaTodos()) { //realizando o forEach na lista
            System.out.println("Descrição: " + cat.getDescricao());

        }
    }

}
