package br.edu.femass.model;

import java.util.List;

public class Exemplar {
    private String dataAquisicao;
    private Integer codigo;

    private Boolean emprestado;
    public static int proxCod = 1;
    public Exemplar() {};

    public Exemplar(String dataAquisicao, int codigo) {
        this.dataAquisicao = dataAquisicao;
        this.codigo = proxCod;
        proxCod++;
        this.emprestado = false;
    }

    public String getDataAquisicao() {
        return dataAquisicao;
    }

    public void setDataAquisicao(String dataAquisicao) {
        this.dataAquisicao = dataAquisicao;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public static int getProxCod() {
        proxCod++;
        return proxCod;
    }

    public Boolean getEmprestado() {
        return emprestado;
    }

    public void setEmprestado(Boolean emprestado) {
        this.emprestado = emprestado;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public static void setProxCod(int proxCod) {
        Exemplar.proxCod = proxCod;
    }


    public static void atualizarProxCod(List<Exemplar> exemplares){
        for (Exemplar exemplar : exemplares){
            if (exemplar.getCodigo() > proxCod){
                proxCod = exemplar.getCodigo();
                //TODO apagar JOptionPane.showMessageDialog(null,"Cod autor " + autor.getCodigo()+" diferente de proxcod " + proxCod);
                return;
            }
        }
    }

    @Override
    public String toString() {
        return ("Data de aquisição: " +this.dataAquisicao + ", " + " Código: " + this.codigo);
    }


    @Override
    public boolean equals(Object obj) {
        Exemplar exemplar = (Exemplar) obj;
        return this.codigo.equals(exemplar.getCodigo());
    }

}
