package model.bean;

public class Categoria {

    private int id;
    private String descricao;

    //Construtor para testes
    public Categoria() {
    }

    public Categoria(String descricao) {
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
