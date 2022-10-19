package br.edu.femass.model;

import java.util.List;

public class Autor {
    private String nome;
    private String nacionalidade;
    private int codigo;
    public static int proxCod = 1;


    public Autor() {}

    public Autor(String nome, String nacionalidade) {
        this.nome = nome;
        this.nacionalidade = nacionalidade;
        this.codigo = proxCod;
        proxCod++;
    }

    public String getNome() {
        return nome;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public int getCodigo() {
        return codigo;
    }

    @Override
    public String toString() {
        return (this.nome + ", " + this.nacionalidade + ", " + this.codigo);
    }

    public static int getProxCod() {
        proxCod++;
        return proxCod;
    }

    @Override
    public boolean equals(Object obj) {
        Autor autor = (Autor) obj;
        return this.nome.equals(autor.getNome()) && this.nacionalidade.equals(autor.getNacionalidade());
    }

    public static void atualizarProxCod(List<Autor> autores){
        for (Autor autor : autores){
            if (autor.getCodigo() > proxCod){
                proxCod = autor.getCodigo();
                //TODO apagar JOptionPane.showMessageDialog(null,"Cod autor " + autor.getCodigo()+" diferente de proxcod " + proxCod);
                return;
            }
        }
    }
}

