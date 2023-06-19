import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



class PrincipalAgenda {
    public static void main(String[] args) {
           Agenda agenda = new Agenda();
    }
     
}

class Agenda extends JFrame {
    public Agenda (){
        Painel painel = new Painel();
        getContentPane().add(painel);
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
    }
}

class Painel extends JPanel {
    public Painel (){
        JLabel image;
        JPanel cadastrodivisoria = new JPanel();
        JPanel centrodivisoria = new JPanel();
        JPanel atributos = new JPanel();
        JPanel buttom = new JPanel();
        JPanel Anotacao = new JPanel();

        JTextField codi;
        JTextField NomeAgenda;
        JTextField Endereco;
        JTextField telefone;
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

        atributos.setBackground(Color.DARK_GRAY);
        Anotacao.setBackground(Color.ORANGE);
        centrodivisoria.setBackground(Color.BLUE);

        add("Center", centrodivisoria);
        add("West", cadastrodivisoria);

        cadastrodivisoria.add("Center", atributos);
        cadastrodivisoria.add( "South",buttom);
        cadastrodivisoria.add("East", Anotacao);


        // elementos de atributos 

        atributos.setLayout(new GridLayout(4, 1));

        JPanel Codigo = new JPanel();
        Codigo.setLayout(new FlowLayout());
        Codigo.add(new JLabel("Codigo"));
        codi = new JTextField(10);


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
        telefone = new JTextField(10);

        Codigo.add(codi);
        nome.add(NomeAgenda);
        end.add(Endereco);
        celular.add(telefone);


        atributos.add(Codigo);
        atributos.add(nome);
        atributos.add(end);
        atributos.add(telefone);

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

}
}