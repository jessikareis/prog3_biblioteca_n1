package br.edu.femass.gui;

import br.edu.femass.dao.AutorDao;
import br.edu.femass.dao.LivroDao;
import br.edu.femass.model.Autor;
import br.edu.femass.model.Exemplar;
import br.edu.femass.model.Livro;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GuiLivro {
    private JPanel GuiLivro;
    private JLabel Título;
    private JLabel ListaAutoresLivro;
    private JTextField TxtLivroTitulo;
    private JList listaAutores;
    private JButton botaoSalvar;
    private JList listaLivros;
    private JList listaAutoresLivro;
    private JButton botaoAddAutorLivro;
    private JButton botaoAddAutores;
    private JButton botaoRemover;
    private JTextField txtDataAquisicao;
    private JButton botaoRemoverExemplar;
    private JList listaExemplares;
    private JButton botaoSalvarExemplar;
    private JButton botaoLeitores;
    private JButton botaoEmprestimos;

    private ArrayList<Autor> autoresAddLivro = new ArrayList<>();
    private ArrayList<Autor> autoresDisponiveis = new ArrayList<>();
    private Livro livroSelecionado;

    public GuiLivro() {
        botaoSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Livro livro = new Livro(TxtLivroTitulo.getText(),
                            Livro.getCodAtual());
                    livro.setAutores(autoresAddLivro);
                    new LivroDao().salvar(livro);

                    autoresDisponiveis.clear();
                    autoresDisponiveis.addAll(new AutorDao().getAll());
                    autoresAddLivro.clear();
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
                    livroSelecionado = (Livro) listaLivros.getSelectedValue();
                    new LivroDao().remover(livroSelecionado);
                    atualizarLista();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });

        botaoAddAutorLivro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Autor autorSelecionado = (Autor) listaAutores.getSelectedValue();
                if (autorSelecionado == null) {
                    JOptionPane.showMessageDialog(null, "Nenhum autor selecionado.");
                } else try {
                    autoresAddLivro.add(autorSelecionado);
                    autoresDisponiveis.remove(autorSelecionado);
                    atualizarLista();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }

            }
        });

        botaoAddAutores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GuiAutor().abrirTelaModal();
                try {
                    autoresDisponiveis.clear();
                    autoresDisponiveis.addAll(new AutorDao().getAll());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                atualizarLista();
            }
        });

        botaoSalvarExemplar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    livroSelecionado = (Livro) listaLivros.getSelectedValue();
                    Exemplar exemplar = new Exemplar(txtDataAquisicao.getText(),
                            Exemplar.getProxCod());
                    //TODO apagar JOptionPane.showMessageDialog(null, "teste" + livroSelecionado.getExemplares());
                    new LivroDao().remover(livroSelecionado);
                    livroSelecionado.getExemplares().add(exemplar);
                    listaExemplares.setListData(
                            livroSelecionado.getExemplares().toArray()
                    );
                    new LivroDao().salvar(livroSelecionado);
                    //TODO apagar JOptionPane.showMessageDialog(null, "teste" + livroSelecionado.getExemplares());
                    atualizarLista();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
        botaoRemoverExemplar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!((Exemplar) listaExemplares.getSelectedValue() == null
                            && (Livro)listaLivros.getSelectedValue() == null)) {
                        Exemplar exemplar = (Exemplar) listaExemplares.getSelectedValue();
                        new LivroDao().remover(livroSelecionado);
                        livroSelecionado.getExemplares().remove(exemplar);
                        listaExemplares.setListData(
                                livroSelecionado.getExemplares().toArray()
                        );
                        new LivroDao().salvar(livroSelecionado);
                        atualizarLista();
                    } else {
                        JOptionPane.showMessageDialog(null, "Selecione um livro e um exemplar.");
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
        listaLivros.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                livroSelecionado = (Livro) listaLivros.getSelectedValue();
                if (!(livroSelecionado == null)) {
                    listaExemplares.setListData(
                            livroSelecionado.getExemplares().toArray()
                    );
                    //TODO apagar JOptionPane.showMessageDialog(null, "Mostrando exemplares do livro " + livroSelecionado);
                }
            }
        });
        botaoLeitores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GuiLeitor().abrirTelaModal();
            }
        });
        botaoEmprestimos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GuiEmprestimo().abrirTelaModal();
            }
        });
    }

    public void abrirTela() {
        try {
            JFrame frame = new JFrame();
            GuiLivro gui = new GuiLivro();
            gui.autoresDisponiveis.addAll(new AutorDao().getAll());
            gui.atualizarLista();
            frame.setContentPane(gui.GuiLivro);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setTitle("Cadastro de Livros");
            frame.pack();
            frame.setVisible(true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    private void atualizarLista() {
        try {
            listaAutores.setListData(
                    autoresDisponiveis.toArray()
            );
            listaLivros.setListData(
                    new LivroDao().getAll().toArray()
            );
            listaAutoresLivro.setListData(
                    autoresAddLivro.toArray()
            );

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }
}

