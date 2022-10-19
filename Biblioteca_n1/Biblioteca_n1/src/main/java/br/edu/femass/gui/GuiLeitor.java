package br.edu.femass.gui;

import br.edu.femass.dao.AlunoDao;
import br.edu.femass.dao.LivroDao;
import br.edu.femass.dao.ProfessorDao;
import br.edu.femass.model.Aluno;
import br.edu.femass.model.Livro;
import br.edu.femass.model.Professor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiLeitor {
    private JTextField txtNome;
    private JCheckBox checkBoxAluno;
    private JCheckBox checkBoxProfessor;
    private JButton botaoSalvarLeitor;
    private JTextField textExtra;
    private JLabel campoExtra;
    private JPanel GuiLeitor;
    private JList listaProfessores;
    private JList listaAlunos;
    private JButton botaoRemoverAluno;
    private JButton botaoRemoverProfessor;
    private JTextField txtEndereco;
    private JTextField txtTelefone;
    private JLabel Telefone;
    private JLabel Endereco;
    private JLabel Nome;
    private JPanel ListaProfessor;

    public GuiLeitor() {
        checkBoxAluno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkBoxProfessor.setSelected(false);
                campoExtra.setText("Matricula: ");
            }
        });


        checkBoxProfessor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkBoxAluno.setSelected(false);
                campoExtra.setText("Disciplina: ");
            }
        });

        botaoSalvarLeitor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkBoxProfessor.isSelected()) {
                    try {
                        Professor professor = new Professor(txtEndereco.getText(),
                                txtTelefone.getText(),
                                txtNome.getText(),
                                textExtra.getText()
                        );
                        new ProfessorDao().salvar(professor);
                        atualizarLista();

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                }
                else if (checkBoxAluno.isSelected())
                {

                    try {
                        Aluno aluno = new Aluno(txtEndereco.getText(),
                                txtTelefone.getText(),
                                txtNome.getText(),
                                textExtra.getText()
                        );
                        JOptionPane.showMessageDialog(null, aluno);
                        new AlunoDao().salvar(aluno);
                        atualizarLista();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                }
            }
        });
        botaoRemoverAluno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Aluno alunoSelecionado = (Aluno) listaAlunos.getSelectedValue();
                    new AlunoDao().remover(alunoSelecionado);
                    atualizarLista();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
        botaoRemoverProfessor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Professor professorSelecionado = (Professor) listaProfessores.getSelectedValue();
                    new ProfessorDao().remover(professorSelecionado);
                    atualizarLista();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
    }
    public void abrirTelaModal() {
        JDialog frame = new JDialog(new Frame(), true);
        GuiLeitor gui = new GuiLeitor();
        gui.atualizarLista();
        frame.setContentPane(gui.GuiLeitor);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setTitle("Cadastro de Leitores");
        frame.pack();
        frame.setVisible(true);
    }

    private void atualizarLista() {
        try {
            listaProfessores.setListData(
                     new ProfessorDao().getAll().toArray()
             );
            listaAlunos.setListData(
                    new AlunoDao().getAll().toArray()
            );
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

}
