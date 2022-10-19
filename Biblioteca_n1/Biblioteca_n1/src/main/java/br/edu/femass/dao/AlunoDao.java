package br.edu.femass.dao;

import br.edu.femass.model.Aluno;
import com.fasterxml.jackson.core.type.TypeReference;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class AlunoDao extends Persistencia<Aluno> implements Dao<Aluno> {
    private final static String NOMEARQUIVO = "alunos.json";

    @Override
    public void salvar(Aluno aluno) throws Exception{
        List<Aluno> alunos = getAll();
        alunos.add(aluno);
        String json = getObjectmapper().writerWithDefaultPrettyPrinter().writeValueAsString(alunos);

        FileOutputStream out = new FileOutputStream(NOMEARQUIVO);
        out.write(json.getBytes());
        out.close();
        Aluno.atualizarProxCod(alunos);
      /*  JOptionPane.showMessageDialog(null, Aluno.proxCod);
        JOptionPane.showMessageDialog(null,
                "Nome: " + aluno.getNome() + " Nacionalidade: "+ aluno.getNacionalidade() + " Codigo: "+ aluno.getCodigo() +"\nSalvo") ;
    */}
    public void remover(Aluno aluno) throws Exception{
        List<Aluno> alunos = getAll();
        alunos.remove(aluno);
        JOptionPane.showMessageDialog(null, alunos);
        String json = getObjectmapper().writerWithDefaultPrettyPrinter().writeValueAsString(alunos);

        FileOutputStream out = new FileOutputStream(NOMEARQUIVO);
        out.write(json.getBytes());
        out.close();
        Aluno.atualizarProxCod(alunos);
     /*   JOptionPane.showMessageDialog(null,
                "Nome: " + aluno.getNome() + " Nacionalidade: "
                        + aluno.getNacionalidade() + " Codigo: "+ aluno.getCodigo() +"\nRemovido") ;*/
    }

    @Override
    public List getAll() throws Exception {
        try {
            FileInputStream in = new FileInputStream(NOMEARQUIVO);
            String json = new String(in.readAllBytes());
            List<Aluno> alunos = getObjectmapper().readValue(json, new TypeReference<List<Aluno>>(){});
            Aluno.atualizarProxCod(alunos);
            return alunos;
        } catch (FileNotFoundException f) {
            return new ArrayList();
        }
    }
}
