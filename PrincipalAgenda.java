import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

 public class PrincipalAgenda {
    public static void main(String[] args) {
        Agenda agenda = new Agenda();
    }
}

class Agenda extends JFrame {
    public Agenda() {
        Painel painel = new Painel();
        getContentPane().add(painel);
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
    }
}

class Painel extends JPanel {
    private DefaultTableModel tableModel;
    private JTable table;
    private List<Registro> registros;
    private int codigoCounter;

    public Painel() {
        JLabel image;
        JPanel cadastrodivisoria = new JPanel();
        JPanel centrodivisoria = new JPanel();
        JPanel atributos = new JPanel();
        JPanel buttom = new JPanel();
        JPanel Anotacao = new JPanel();

        JTextField codi;
        JTextField NomeAgenda;
        JTextField Endereco;
        JTextField telefoneField;
        JTextField texto;

        JButton inserir;
        JButton consulta;
        JButton alterar;
        JButton delete;
        JButton limpar;

        setLayout(new BorderLayout());
        image = new JLabel(new ImageIcon("image.jpg"));
        add("North", image);
        cadastrodivisoria.setLayout(new BorderLayout());
        Anotacao.setLayout(new BorderLayout());

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Código");
        tableModel.addColumn("Nome");
        tableModel.addColumn("Endereço");
        tableModel.addColumn("Telefone");
        table = new JTable(tableModel);

        centrodivisoria.setLayout(new BorderLayout());
        centrodivisoria.add(new JScrollPane(table), BorderLayout.CENTER);

        atributos.setBackground(Color.DARK_GRAY);
        Anotacao.setBackground(Color.ORANGE);

        add("West", cadastrodivisoria);
        add("Center", centrodivisoria);

        cadastrodivisoria.add("Center", atributos);
        cadastrodivisoria.add("South", buttom);
        cadastrodivisoria.add("East", Anotacao);

        // elementos de atributos
        atributos.setLayout(new GridLayout(4, 1));

        JPanel Codigo = new JPanel();
        Codigo.setLayout(new FlowLayout());
        Codigo.add(new JLabel("Codigo"));
        codi = new JTextField(10);
        codi.setEnabled(false); // Desabilita a edição manual do campo Código

        JPanel end = new JPanel();
        end.setLayout(new FlowLayout());
        end.add(new JLabel("Endereço"));
        Endereco = new JTextField(10);

        JPanel nome = new JPanel();
        nome.setLayout(new FlowLayout());
        nome.add(new JLabel("Nome"));
        NomeAgenda = new JTextField(10);

        JPanel celular = new JPanel();
        celular.setLayout(new FlowLayout());
        celular.add(new JLabel("Telefone"));
        telefoneField = new JTextField(10);

        Codigo.add(codi);
        nome.add(NomeAgenda);
        end.add(Endereco);
        celular.add(telefoneField);

        atributos.add(Codigo);
        atributos.add(nome);
        atributos.add(end);
        atributos.add(celular);

        JPanel titulo = new JPanel();
        titulo.add(new JLabel("Anotações"));
        Anotacao.add("North", titulo);
        texto = new JTextField(10);
        Anotacao.add("Center", texto);

        JPanel botao = new JPanel();

        inserir = new JButton("Inserir");
        consulta = new JButton("Consultar");
        alterar = new JButton("Alterar");
        delete = new JButton("Excluir");
        limpar = new JButton("Limpar");

        botao.add(inserir);
        botao.add(consulta);
        botao.add(alterar);
        botao.add(delete);
        botao.add(limpar);

        buttom.add(botao);

        inserir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String codigo = "R" + (++codigoCounter);
                String nome = NomeAgenda.getText();
                String endereco = Endereco.getText();
                String telefone = telefoneField.getText();

                Registro registro = new Registro(codigo, nome, endereco, telefone);
                adicionarRegistro(registro);

                adicionarLinhaTabela(registro);

                JOptionPane.showMessageDialog(null, "Inserido com sucesso!");

                NomeAgenda.setText("");
                Endereco.setText("");
                telefoneField.setText("");
            }
        });

        consulta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String consultaInput = JOptionPane.showInputDialog(null, "Digite o código ou nome para consultar:");
                if (consultaInput != null && !consultaInput.isEmpty()) {
                    if (consultaInput.startsWith("R")) {
                        consultarPorCodigo(consultaInput);
                    } else {
                        consultarPorNome(consultaInput);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Digite um código ou nome válido para consultar!");
                }
            }
        });

        alterar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    Registro registro = registros.get(selectedRow);
                    String nome = NomeAgenda.getText();
                    String endereco = Endereco.getText();
                    String telefone = telefoneField.getText();
                    registro.setNome(nome);
                    registro.setEndereco(endereco);
                    registro.setTelefone(telefone);
                    atualizarLinhaTabela(selectedRow, registro);
                    JOptionPane.showMessageDialog(null, "Registro alterado com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um registro para alterar!");
                }
            }
        });

        delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    Registro registro = registros.get(selectedRow);
                    removerRegistro(registro);
                    removerLinhaTabela(selectedRow);
                    JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um registro para excluir!");
                }
            }
        });

        limpar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                NomeAgenda.setText("");
                Endereco.setText("");
                telefoneField.setText("");
            }
        });

        registros = RegistroManager.carregarRegistros();
        codigoCounter = registros.size();
        for (Registro registro : registros) {
            adicionarLinhaTabela(registro);
        }
    }

    private void adicionarLinhaTabela(Registro registro) {
        Object[] rowData = { registro.getCodigo(), registro.getNome(), registro.getEndereco(), registro.getTelefone() };
        tableModel.addRow(rowData);
    }

    private void removerLinhaTabela(int rowIndex) {
        tableModel.removeRow(rowIndex);
    }

    private void atualizarLinhaTabela(int rowIndex, Registro registro) {
        Object[] rowData = { registro.getCodigo(), registro.getNome(), registro.getEndereco(), registro.getTelefone() };
        for (int i = 0; i < rowData.length; i++) {
            tableModel.setValueAt(rowData[i], rowIndex, i);
        }
    }

    private void adicionarRegistro(Registro novoRegistro) {
        registros.add(novoRegistro);
        RegistroManager.salvarRegistros(registros);
    }

    private void removerRegistro(Registro registro) {
        registros.remove(registro);
        RegistroManager.salvarRegistros(registros);
    }

    private void consultarPorCodigo(String codigo) {
        for (Registro registro : registros) {
            if (registro.getCodigo().equalsIgnoreCase(codigo)) {
                StringBuilder result = new StringBuilder();
                result.append("Código: ").append(registro.getCodigo()).append("\n")
                        .append("Nome: ").append(registro.getNome()).append("\n")
                        .append("Endereço: ").append(registro.getEndereco()).append("\n")
                        .append("Telefone: ").append(registro.getTelefone());
                JOptionPane.showMessageDialog(null, result.toString());
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Registro não encontrado!");
    }

    private void consultarPorNome(String nome) {
        List<Registro> registrosEncontrados = new ArrayList<>();
        for (Registro registro : registros) {
            if (registro.getNome().equalsIgnoreCase(nome)) {
                registrosEncontrados.add(registro);
            }
        }
        if (!registrosEncontrados.isEmpty()) {
            StringBuilder result = new StringBuilder();
            for (Registro registro : registrosEncontrados) {
                result.append("Código: ").append(registro.getCodigo()).append("\n")
                        .append("Nome: ").append(registro.getNome()).append("\n")
                        .append("Endereço: ").append(registro.getEndereco()).append("\n")
                        .append("Telefone: ").append(registro.getTelefone()).append("\n\n");
            }
            JOptionPane.showMessageDialog(null, result.toString());
        } else {
            JOptionPane.showMessageDialog(null, "Registro não encontrado!");
        }
    }
}

class Registro {
    private String codigo;
    private String nome;
    private String endereco;
    private String telefone;

    public Registro(String codigo, String nome, String endereco, String telefone) {
        this.codigo = codigo;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getTelefone() {
        return telefone;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}

class RegistroManager {
    private static final String ARQUIVO_REGISTRO = "registro.txt";

    public static void salvarRegistros(List<Registro> registros) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ARQUIVO_REGISTRO))) {
            for (Registro registro : registros) {
                writer.println(registro.getCodigo());
                writer.println(registro.getNome());
                writer.println(registro.getEndereco());
                writer.println(registro.getTelefone());
                writer.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Registro> carregarRegistros() {
        List<Registro> registros = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_REGISTRO))) {
            String codigo;
            while ((codigo = reader.readLine()) != null) {
                String nome = reader.readLine();
                String endereco = reader.readLine();
                String telefone = reader.readLine();
                reader.readLine(); // Descarta a linha em branco
                Registro registro = new Registro(codigo, nome, endereco, telefone);
                registros.add(registro);
            }
        } catch (IOException e) {
            // O arquivo ainda não existe, retorna uma lista vazia
        }
        return registros;
    }
}