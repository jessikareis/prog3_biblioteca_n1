package br.edu.femass.dao;

import br.edu.femass.model.Autor;
import com.fasterxml.jackson.core.type.TypeReference;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class AutorDao  extends Persistencia<Autor> implements Dao<Autor> {
    private final static String NOMEARQUIVO = "autores.json";

    @Override
    public void salvar(Autor autor) throws Exception{
        List<Autor>  autores = getAll();
        autores.add(autor);
        String json = getObjectmapper().writerWithDefaultPrettyPrinter().writeValueAsString(autores);

        FileOutputStream out = new FileOutputStream(NOMEARQUIVO);
        out.write(json.getBytes());
        out.close();
        Autor.atualizarProxCod(autores);
        JOptionPane.showMessageDialog(null, Autor.proxCod);
        JOptionPane.showMessageDialog(null,
                "Nome: " + autor.getNome() + " Nacionalidade: "+ autor.getNacionalidade() + " Codigo: "+ autor.getCodigo() +"\nSalvo") ;
    }
    public void remover(Autor autor) throws Exception{
        List<Autor> autores = getAll();
        autores.remove(autor);
        JOptionPane.showMessageDialog(null, autores);
        String json = getObjectmapper().writerWithDefaultPrettyPrinter().writeValueAsString(autores);

        FileOutputStream out = new FileOutputStream(NOMEARQUIVO);
        out.write(json.getBytes());
        out.close();
        Autor.atualizarProxCod(autores);
        JOptionPane.showMessageDialog(null,
                "Nome: " + autor.getNome() + " Nacionalidade: "
                        + autor.getNacionalidade() + " Codigo: "+ autor.getCodigo() +"\nRemovido") ;
    }

    @Override
    public List getAll() throws Exception {
        try {
            FileInputStream in = new FileInputStream(NOMEARQUIVO);
            String json = new String(in.readAllBytes());
            List<Autor> autores = getObjectmapper().readValue(json, new TypeReference<List<Autor>>(){});
            Autor.atualizarProxCod(autores);
            return autores;
        } catch (FileNotFoundException f) {
            return new ArrayList();
        }
    }
}
