package br.edu.femass.dao;

import br.edu.femass.model.Exemplar;
import br.edu.femass.model.Livro;
import com.fasterxml.jackson.core.type.TypeReference;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ExemplarDao extends Persistencia<Exemplar> implements Dao<Exemplar> {
    private Livro livro;
    public ExemplarDao(Livro livro) {
        this.livro = livro;
    }

    @Override
    public void salvar(Exemplar exemplar) throws Exception{
        livro.getExemplares().add(exemplar);
        new LivroDao().salvar(livro);
    }
    public void remover(Exemplar exemplar) throws Exception{
        livro.getExemplares().remove(exemplar);
        new LivroDao().salvar(livro);
    }

    @Override
    public List getAll() throws Exception {
        List<Exemplar> exemplares = livro.getExemplares();
        Exemplar.atualizarProxCod(exemplares);
        return exemplares;
    }
}

