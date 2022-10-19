//package br.edu.femass.gui;
//
//import br.edu.femass.dao.ExemplarDao;
//import br.edu.femass.model.Exemplar;
//import br.edu.femass.model.Livro;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.ArrayList;
//
//public class GuiExemplares {
//
//    private JPanel GuiExemplares;
//    private JLabel titulo;
//    private JTextField txtTitulo;
//    private JList listaExemplares;
//    private JTextField txtDataAquisicao;
//    private JButton botaoSalvar;
//    private JList listaAutores;
//    private JButton botaoRemover;
//    private Livro livro;
//
//    public GuiExemplares() {
//        botaoSalvar.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                try {
//                    Exemplar exemplar = new Exemplar(txtDataAquisicao.getText(),
//                            Exemplar.getProxCod(),
//                            this.livro);
//                    new ExemplarDao(this.livro).salvar(exemplar);
//                    atualizarLista();
//
//                } catch (Exception ex) {
//                    JOptionPane.showMessageDialog(null, ex.getMessage());
//                }
//            }
//        });
//        botaoRemover.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                try {
//                    if (!((Exemplar) listaExemplares.getSelectedValue() == null)) {
//                        Exemplar exemplar = (Exemplar) listaExemplares.getSelectedValue();
//                        new ExemplarDao(livro).remover(exemplar);
//                        atualizarLista();
//                    }
//                    else {
//                        JOptionPane.showMessageDialog(null,"Nenhum exemplar selecionado.");
//                    }
//
//                } catch (Exception ex) {
//                    JOptionPane.showMessageDialog(null, ex.getMessage());
//                }
//            }
//        });
//    }
//
//
//    private void atualizarLista() {
//        try {
//            if (!(livro.getExemplares()==null)){
//                listaExemplares.setListData(
//                    livro.getExemplares().toArray()
//                );
//            }
//            else listaExemplares.setListData(new ArrayList().toArray());
//            if (!livro.getAutores().isEmpty()){
//                listaAutores.setListData(
//                    livro.getAutores().toArray()
//                );
//            }
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e.getMessage());
//        }
//    }
//
//    public Livro abrirTelaModal(Livro livro) {
//        JDialog frame = new JDialog(new Frame(), true);
//        GuiExemplares gui = new GuiExemplares();
//        gui.livro = livro;
//        gui.txtTitulo.setText(livro.getTitulo());
//        JOptionPane.showMessageDialog(null, gui.livro);
//        gui.atualizarLista();
//        frame.setContentPane(gui.GuiExemplares);
//        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        frame.setTitle("Cadastro de Exemplares");
//        frame.pack();
//        frame.setVisible(true);
//        return this.livro;
//    }
//}
