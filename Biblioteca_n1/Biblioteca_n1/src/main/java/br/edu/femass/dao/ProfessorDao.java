package br.edu.femass.dao;

import br.edu.femass.model.Professor;
import com.fasterxml.jackson.core.type.TypeReference;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ProfessorDao extends Persistencia<Professor> implements Dao<Professor> {
    private final static String NOMEARQUIVO = "professores.json";

    @Override
    public void salvar(Professor professor) throws Exception{
        List<Professor> professores = getAll();
        professores.add(professor);
        String json = getObjectmapper().writerWithDefaultPrettyPrinter().writeValueAsString(professores);

        FileOutputStream out = new FileOutputStream(NOMEARQUIVO);
        out.write(json.getBytes());
        out.close();
        Professor.atualizarProxCod(professores);
      /*  JOptionPane.showMessageDialog(null, Professor.proxCod);
        JOptionPane.showMessageDialog(null,
                "Nome: " + professor.getNome() + " Nacionalidade: "+ professor.getNacionalidade() + " Codigo: "+ professor.getCodigo() +"\nSalvo") ;
    */}
    public void remover(Professor professor) throws Exception{
        List<Professor> professores = getAll();
        professores.remove(professor);
        JOptionPane.showMessageDialog(null, professores);
        String json = getObjectmapper().writerWithDefaultPrettyPrinter().writeValueAsString(professores);

        FileOutputStream out = new FileOutputStream(NOMEARQUIVO);
        out.write(json.getBytes());
        out.close();
        Professor.atualizarProxCod(professores);
     /*   JOptionPane.showMessageDialog(null,
                "Nome: " + professor.getNome() + " Nacionalidade: "
                        + professor.getNacionalidade() + " Codigo: "+ professor.getCodigo() +"\nRemovido") ;*/
    }

    @Override
    public List getAll() throws Exception {
        try {
            FileInputStream in = new FileInputStream(NOMEARQUIVO);
            String json = new String(in.readAllBytes());
            List<Professor> professores = getObjectmapper().readValue(json, new TypeReference<List<Professor>>(){});
            Professor.atualizarProxCod(professores);
            return professores;
        } catch (FileNotFoundException f) {
            return new ArrayList();
        }
    }
}
