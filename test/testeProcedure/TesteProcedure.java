package testeProcedure;

import java.util.List;
import model.bean.Produto;
import model.dao.ProdutoDAO;

public class TesteProcedure {

    public static void main(String[] args) {
        ProdutoDAO dao = new ProdutoDAO();

        List<Produto> produtos = dao.buscarProdutosProcedure("%ca%");
        for (Produto produto : produtos) {
            System.out.println("Descricao: " + produto.getDescricao()
                    + " - Quantidade:" + produto.getQtd()
                    + " - Valor:" + produto.getValor());
        }
    }

}
