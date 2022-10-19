package br.edu.femass.model;

import java.util.List;

public class Leitor {
    protected String endereco;
    protected Integer codigo;
    protected String telefone;
    protected String nome;
    protected Integer prazoMaxDevolucao;

    public static int proxCod = 1;

    public Leitor (){}
    public Leitor(String endereco, String telefone, String nome, Integer prazoMaxDevolucao) {
        this.endereco = endereco;
        this.telefone = telefone;
        this.nome = nome;
        this.prazoMaxDevolucao = prazoMaxDevolucao;
        this.codigo = proxCod;
        proxCod++;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getPrazoMaxDevolucao() {
        return prazoMaxDevolucao;
    }

    public void setPrazoMaxDevolucao(Integer prazoMaxDevolucao) {
        this.prazoMaxDevolucao = prazoMaxDevolucao;
    }


}
