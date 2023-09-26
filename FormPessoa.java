//João Antonio Dias
// Isabelly Barbosa Gonçalves
package Pessoa;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormPessoa extends JFrame {
    private JLabel numeroLabel;
    private JTextField nomeField;
    private JTextField sexoField;
    private JTextField idadeField;
    private JButton okButton;
    private JButton mostrarButton;
    private JButton limparButton;
    private JButton sairButton;

    private Pessoa umaPessoa;

    private static int numero = 1;

    public FormPessoa() {
        setTitle("Formulário de Pessoa");
        setSize(450, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        numeroLabel = new JLabel("Número:");
        JLabel nomeLabel = new JLabel("Nome:");
        nomeField = new JTextField();
        JLabel sexoLabel = new JLabel("Sexo:");
        sexoField = new JTextField();
        JLabel idadeLabel = new JLabel("Idade:");
        idadeField = new JTextField();

        panel.add(numeroLabel);
        panel.add(new JLabel(String.valueOf(numero)));
        panel.add(nomeLabel);
        panel.add(nomeField);
        panel.add(sexoLabel);
        panel.add(sexoField);
        panel.add(idadeLabel);
        panel.add(idadeField);

        add(panel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 4));

        okButton = new JButton("OK");
        mostrarButton = new JButton("Mostrar");
        limparButton = new JButton("Limpar");
        sairButton = new JButton("Sair");

        buttonPanel.add(okButton);
        buttonPanel.add(limparButton);
        buttonPanel.add(mostrarButton);
        buttonPanel.add(sairButton);

        add(buttonPanel, BorderLayout.SOUTH);

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                criarInstanciaPessoa();
            }
        });

        mostrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarDadosPessoa();
            }
        });

        limparButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparCampos();
            }
        });

        sairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        setVisible(true);
    }

    private void criarInstanciaPessoa() {
        String nome = nomeField.getText();
        String sexoStr = sexoField.getText();
        char sexo = validarSexo(sexoStr);
        if (sexo == '\0') {
            JOptionPane.showMessageDialog(this, "Sexo deve ser 'M' ou 'F'.");
            return;
        }
        int idade = Integer.parseInt(idadeField.getText());

        umaPessoa = new Pessoa(nome, sexo, idade);
        numero++; // Incrementa o número após criar uma pessoa
        numeroLabel.setText("Número: " + numero);
    }

    private char validarSexo(String sexoStr) {
        if (sexoStr.equalsIgnoreCase("M") || sexoStr.equalsIgnoreCase("F")) {
            return sexoStr.charAt(0);
        }
        return '\0';
    }

    private void mostrarDadosPessoa() {
        if (umaPessoa != null) {
            JOptionPane.showMessageDialog(this, "Número: " + numero + "\nNome: " + umaPessoa.getNome() + "\nSexo: " + umaPessoa.getSexo() + "\nIdade: " + umaPessoa.getIdade() + "\nKP: " + umaPessoa.getKp());
        } else {
            JOptionPane.showMessageDialog(this, "Nenhuma pessoa criada ainda.");
        }
    }

    private void limparCampos() {
        nomeField.setText("");
        sexoField.setText("");
        idadeField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FormPessoa();
            }
        });
    }
}
