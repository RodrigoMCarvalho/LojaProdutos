package model.dao;

import org.junit.Test;
import static org.junit.Assert.*;
import model.bean.*;

/**
 *
 * @author rodri_000
 */
public class CategoriaDAOTest {

    public CategoriaDAOTest() {
    }

    @Test
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

}
