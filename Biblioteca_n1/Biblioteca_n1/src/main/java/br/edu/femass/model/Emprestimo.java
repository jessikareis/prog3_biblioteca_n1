package br.edu.femass.model;

import java.time.LocalDate;

public class Emprestimo {
    private LocalDate dataEmprestimo;
    private LocalDate dataPrevistaDevolucao;
    private LocalDate dataDevolucao;
    private Exemplar exemplar;
    private Leitor leitor;

    public Emprestimo(){}
    public Emprestimo(LocalDate dataEmprestimo, Exemplar exemplar, Leitor leitor) {
        this.dataEmprestimo = dataEmprestimo;
        this.exemplar = exemplar;
        this.leitor = leitor;
        this.dataPrevistaDevolucao = dataEmprestimo.plusDays(leitor.getPrazoMaxDevolucao());
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataPrevistaDevolucao() {
        return dataPrevistaDevolucao;
    }

    public void setDataPrevistaDevolucao(LocalDate dataPrevistaDevolucao) {
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public Exemplar getExemplar() {
        return exemplar;
    }

    public void setExemplar(Exemplar exemplar) {
        this.exemplar = exemplar;
    }

    public Leitor getLeitor() {
        return leitor;
    }

    public void setLeitor(Leitor leitor) {
        this.leitor = leitor;
    }
    @Override
    public String toString() {
        return ("Código do exemplar: " + this.exemplar.getCodigo() + ", " +
                "Nome: " + this.leitor.getNome() + ", " +
                "Dia: " + this.dataEmprestimo + ", "
                );
    }
}
