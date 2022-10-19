package br.edu.femass.gui;

import br.edu.femass.dao.*;
import br.edu.femass.model.Emprestimo;
import br.edu.femass.model.Exemplar;
import br.edu.femass.model.Leitor;
import br.edu.femass.model.Livro;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

public class GuiEmprestimo {
    private JLabel DataEmprestimo;
    private JLabel DataPrevistaDevolucao;
    private JTextField txtDataPrevistaDevolucao;
    private JButton botaoSalvarEmprestimo;
    private JLabel CodigoLivro;
    private JList listaAtrasados;
    private JButton botaoRemoverEmprestimo;
    private JPanel GuiEmprestimo;
    private JCheckBox checkBoxProfessor;
    private JCheckBox checkBoxAluno;
    private JComboBox comboBoxLeitor;
    private JComboBox comboBoxExemplar;
    private JComboBox comboBoxLivro;
    private JList listaEmprestimo;
    private JTextField txtFieldDia;
    private JTextField txtFieldMes;
    private JTextField txtFieldAno;
    private Emprestimo emprestimoSelecionado;

    public GuiEmprestimo() {

        checkBoxAluno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkBoxProfessor.setSelected(false);
                comboBoxLeitor.removeAllItems();
                try {
                    for (Object aluno : new AlunoDao().getAll()) {
                        comboBoxLeitor.addItem(aluno);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });

        checkBoxProfessor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkBoxAluno.setSelected(false);
                comboBoxLeitor.removeAllItems();
                try {
                    for (Object professor : new ProfessorDao().getAll()) {
                        comboBoxLeitor.addItem(professor);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });

        botaoSalvarEmprestimo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Emprestimo emprestimo = new Emprestimo(
                            LocalDate.parse(txtFieldAno.getText() + "-" + txtFieldMes.getText() + "-" + txtFieldDia.getText()),
                            (Exemplar) comboBoxExemplar.getSelectedItem(),
                            (Leitor) comboBoxLeitor.getSelectedItem());
                    emprestimo.getExemplar().setEmprestado(true);
                    new EmprestimoDao().salvar(emprestimo);
                    atualizarLista();
                    atualizarComboBoxExemplar();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });

        botaoRemoverEmprestimo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    emprestimoSelecionado = (Emprestimo) listaEmprestimo.getSelectedValue();
                    new EmprestimoDao().remover(emprestimoSelecionado);
                    atualizarLista();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });


        comboBoxLivro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarComboBoxExemplar();
            }
        });

        listaEmprestimo.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                Emprestimo emprestimoSelecionado = (Emprestimo) listaEmprestimo.getSelectedValue();
                txtDataPrevistaDevolucao.setText(emprestimoSelecionado.getDataPrevistaDevolucao().toString());
                txtFieldDia.setText(Integer.toString(emprestimoSelecionado.getDataEmprestimo().getDayOfMonth()));
                txtFieldMes.setText(Integer.toString(emprestimoSelecionado.getDataEmprestimo().getMonthValue()));
                txtFieldAno.setText(Integer.toString(emprestimoSelecionado.getDataEmprestimo().getYear()));
            }
        });

    }

    private void atualizarLista() {
        try {
            listaEmprestimo.setListData(
                    new EmprestimoDao().getAll().toArray()
            );

            ArrayList<Emprestimo> emprestimoTotal= new ArrayList<>();
            ArrayList<Emprestimo> emprestimoAtrasado= new ArrayList<>();

            for(Object emprestimo: new EmprestimoDao().getAll().toArray()){
                emprestimoTotal.add((Emprestimo) emprestimo);
            }
            for(Emprestimo emprestimo: emprestimoTotal){
                if (LocalDate.now().compareTo(emprestimo.getDataPrevistaDevolucao())<0)
                emprestimoAtrasado.add((Emprestimo) emprestimo);
            }
            listaAtrasados.setListData(emprestimoAtrasado.toArray());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    public void abrirTelaModal() {
        JDialog frame = new JDialog(new Frame(), true);
        GuiEmprestimo gui = new GuiEmprestimo();
        gui.atualizarLista();
        gui.atualizarComboBoxLivro();
        frame.setContentPane(gui.GuiEmprestimo);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setTitle("Tela de Emprestimos");
        frame.pack();
        frame.setVisible(true);
    }

    public void atualizarComboBoxExemplar() {
        Livro livroSelecionado = (Livro) comboBoxLivro.getSelectedItem();
        comboBoxExemplar.removeAllItems();
        for (Exemplar exemplar : livroSelecionado.getExemplares()) {
            if (exemplar.getEmprestado() == false)
                comboBoxExemplar.addItem(exemplar);

        }
    }

    public void atualizarComboBoxLivro() {
        try {
            for (Object livro : new LivroDao().getAll()) {
                comboBoxLivro.addItem(livro);
            }
        } catch (Exception ed) {
            JOptionPane.showMessageDialog(null, ed.getMessage());
        }
    }
}