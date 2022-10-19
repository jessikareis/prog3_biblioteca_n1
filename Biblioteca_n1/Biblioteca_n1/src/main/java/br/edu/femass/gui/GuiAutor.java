package br.edu.femass.gui;

import br.edu.femass.dao.AutorDao;
import br.edu.femass.model.Autor;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GuiAutor {
    private JPanel GuiAutor;
    private JLabel Nome;
    private JLabel Nacionalidade;
    private JLabel ListaAutores;
    private JTextField TxtAutorNome;
    private JTextField TxtAutorNacionalidade;
    private JList listaAutores;
    private JButton botaoSalvar;
    private JButton botaoRemover;

    public GuiAutor() {
        botaoSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Autor autor = new Autor(TxtAutorNome.getText(),
                            TxtAutorNacionalidade.getText());
                    new AutorDao().salvar(autor);
                    atualizarLista();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
        botaoRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Autor autor = (Autor) listaAutores.getSelectedValue();
                    new AutorDao().remover(autor);
                    atualizarLista();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
    }
    public void abrirTelaModal() {
        JDialog frame = new JDialog(new Frame(), true);
        GuiAutor gui = new GuiAutor();
        gui.atualizarLista();
        frame.setContentPane(gui.GuiAutor);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setTitle("Cadastro de Autores");
        frame.pack();
        frame.setVisible(true);
    }

    private void atualizarLista() {
        try {
            listaAutores.setListData(
                    new AutorDao().getAll().toArray()
            );
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
