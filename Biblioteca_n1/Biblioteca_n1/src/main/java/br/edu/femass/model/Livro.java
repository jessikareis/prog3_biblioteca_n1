package br.edu.femass.model;

import java.util.ArrayList;
import java.util.List;

public class Livro {
    private String titulo;
    private Integer codigo;
    private ArrayList<Autor> autores;
    public static int proxCod = 1;
    private ArrayList<Exemplar> exemplares;
    public Livro() {
        this.autores = new ArrayList<>();
        this.exemplares = new ArrayList<>();
    }
    public Livro(String titulo, int codigo) {
        this.titulo = titulo;
        this.codigo = proxCod;
        proxCod++;
        this.autores = new ArrayList<>();
        this.exemplares = new ArrayList<>();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public ArrayList<Autor> getAutores() {
        return autores;
    }

    public void setAutores(ArrayList<Autor> autores) {
        this.autores = autores;
    }
    public static int getCodAtual() {
        proxCod++;
        return proxCod;
    }
    public ArrayList<Exemplar> getExemplares() {
        return exemplares;
    }

    public void setExemplares(ArrayList<Exemplar> exemplares) {
        this.exemplares = exemplares;
    }

    public static void atualizarProxCod(List<Livro> livros){
        for (Livro livro : livros){
            if (livro.getCodigo() > proxCod){
                proxCod = livro.getCodigo();
                //TODO apagar JOptionPane.showMessageDialog(null,"Cod autor " + autor.getCodigo()+" diferente de proxcod " + proxCod);
                return;
            }
        }
    }

    @Override
    public String toString() {
        return (this.titulo + ", " + this.getAutores().toString() + " Código: " + this.codigo);
    }

    @Override
    public boolean equals(Object obj) {
        Livro livro = (Livro) obj;
        return this.codigo.equals(livro.getCodigo());
    }
}

