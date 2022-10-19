package br.edu.femass.model;

import java.util.List;

public class Aluno extends Leitor{


    private String matricula;

    public Aluno (){}
    public Aluno(String endereco, String telefone, String nome, String matricula) {
        super(endereco, telefone, nome, 15);
        this.matricula = matricula;
    }
    public static void atualizarProxCod(List<Aluno> alunos){
        for (Aluno aluno : alunos){
            if (aluno.getCodigo() > proxCod){
                proxCod = aluno.getCodigo();
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
                "Matrícula: " + this.matricula);
    }
}
