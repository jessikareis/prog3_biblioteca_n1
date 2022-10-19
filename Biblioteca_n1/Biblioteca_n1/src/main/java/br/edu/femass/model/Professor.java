package br.edu.femass.model;

import java.util.List;

public class Professor extends Leitor{
    private String disciplina;

    public Professor(){}
    public Professor(String endereco, String telefone, String nome, String disciplina) {
        super(endereco, telefone, nome, 30);
        this.disciplina = disciplina;
    }
    public static void atualizarProxCod(List<Professor> professores){
        for (Professor professor : professores){
            if (professor.getCodigo() > proxCod){
                proxCod = professor.getCodigo();
                return;
            }
        }
    }

    @Override
    public String toString() {
        return ("Código: " + this.codigo + ", " +
                "Nome: " + this.nome + ", " +
                "Endereço: " + this.endereco + ", " +
                "Telefone: " + this.telefone + ", " +
                "Disciplina: " + this.disciplina);
    }
}
