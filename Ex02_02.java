import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Pessoa {
    private static int kp = 0; 
    private String nome;
    private char sexo;
    private int idade;

    
    public Pessoa() {
        kp++; 
    }

    
    public Pessoa(String nome, char sexo, int idade) {
        this.nome = nome;
        this.sexo = sexo;
        this.idade = idade;
        kp++; 
    }

    
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    
    public int getKp() {
        return kp;
    }

    public String getNome() {
        return nome;
    }

    public char getSexo() {
        return sexo;
    }

    public int getIdade() {
        return idade;
    }
}

public class Ex02_02 extends JFrame {
    private JLabel lblNome, lblSexo, lblIdade, lblNumero;
    private JTextField txtNome, txtIdade, txtNumero;
    private JButton btnOK, btnLimpar, btnMostrar, btnSair;
    private JComboBox<String> cmbSexo;

    private Pessoa UmaPessoa = null;

    public Ex02_02() {
        setTitle("Formulário de Pessoa");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel mainPanel = new JPanel(new GridLayout(6, 2));
        
        lblNumero = new JLabel("Número:");
        txtNumero = new JTextField();
        if (UmaPessoa == null) {
            txtNumero.setText("1");
        } else {
            txtNumero.setText(String.valueOf(this.UmaPessoa.getKp() + 1));
        }
        txtNumero.setEditable(false);
        lblNome = new JLabel("Nome:");
        txtNome = new JTextField();
        lblSexo = new JLabel("Sexo (M/F):");
        cmbSexo = new JComboBox<>(new String[]{"M", "F"});
        
        

        lblIdade = new JLabel("Idade:");
        txtIdade = new JTextField();
        
        mainPanel.add(lblNumero);
        mainPanel.add(txtNumero);
        mainPanel.add(lblNome);
        mainPanel.add(txtNome);
        mainPanel.add(lblSexo);
        mainPanel.add(cmbSexo);
        mainPanel.add(lblIdade);
        mainPanel.add(txtIdade);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        btnOK = new JButton("OK");
        btnLimpar = new JButton("Limpar");
        btnMostrar = new JButton("Mostrar");
        btnSair = new JButton("Sair");
        
        buttonPanel.add(btnOK);
        buttonPanel.add(btnLimpar);
        buttonPanel.add(btnMostrar);
        buttonPanel.add(btnSair);
        
        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        
        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateInput()) {
                    String nome = txtNome.getText();
                    char sexo = cmbSexo.getSelectedItem().toString().charAt(0);
                    if (!txtIdade.getText().matches("[0-9]+")) {
                        JOptionPane.showMessageDialog(null, "Idade deve ser um número inteiro.");
                        return;
                    }
                    int idade = Integer.parseInt(txtIdade.getText());
                    UmaPessoa = new Pessoa(nome, sexo, idade);
                    txtNumero.setText(String.valueOf(UmaPessoa.getKp() + 1));
                    txtNome.setText("");
                    cmbSexo.setSelectedIndex(0);
                    txtIdade.setText("");
                    JOptionPane.showMessageDialog(null, "Dados transferidos com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos corretamente.");
                }
            }
        });

        btnLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtNome.setText("");
                cmbSexo.setSelectedIndex(0);
                txtIdade.setText("");
            }
        });

        btnMostrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (UmaPessoa != null) {
                    StringBuilder mensagem = new StringBuilder();
                    mensagem.append("Nome: ").append(UmaPessoa.getNome()).append("\n");
                    mensagem.append("Sexo: ").append(UmaPessoa.getSexo()).append("\n");
                    mensagem.append("Idade: ").append(UmaPessoa.getIdade()).append("\n");
                    mensagem.append("Número de Pessoas: ").append(UmaPessoa.getKp()).append("\n");
                    JOptionPane.showMessageDialog(null, mensagem.toString());
                } else {
                    JOptionPane.showMessageDialog(null, "Nenhum dado disponível.");
                }
            }
        });

        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        setVisible(true);
    }

    private boolean validateInput() {
        return !txtNome.getText().isEmpty() && !txtIdade.getText().isEmpty();
    }

    public static void main(String[] args) {
        new Ex02_02();
    }
}