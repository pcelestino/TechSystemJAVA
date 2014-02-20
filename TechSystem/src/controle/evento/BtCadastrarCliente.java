package controle.evento;

import static controle.ControleLoginSistema.telaPrincipal;
import controle.excecao.CpfInvalidoException;
import controle.excecao.DataInvalidaException;
import controle.excecao.NomeInvalidoException;
import controle.validacao.Validador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.Cliente;
import modelo.SistemaDAO;
import visao.cadastro.PainelCadastroCliente;

/**
 * Acoes do painel para cadastro de cliente
 * @author Pedro Celestino Silveira Junior
 */
public class BtCadastrarCliente implements ActionListener {

    private Cliente cliente;
    private Validador valida;
    private String nome, cpf, dataNascimento, tipo;
    private SistemaDAO sistemaDAO;
    private PainelCadastroCliente painelCadastroCliente;

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            sistemaDAO = new SistemaDAO();
            painelCadastroCliente = telaPrincipal.getPainelCards().getPainelCadastroCliente();
            
            // Instancia o objeto Validador para validar os dados
            valida = new Validador();

            // Recupera os dados digitados no painel de cadastro
            nome = painelCadastroCliente.getTfNome().getText();
            cpf = painelCadastroCliente.getFtfCpf().getText();
            dataNascimento = painelCadastroCliente.getFtfDataNascimento().getText();
            tipo = painelCadastroCliente.getCbTipo().getSelectedItem().toString();

            // Remove caracteres não numéricos
            cpf = cpf.replaceAll("\\D", "");

            // Método para validar os dados
            valida.validarNome(nome);
            valida.validarCPF(cpf, "cliente");
            valida.validarDataNascimento(dataNascimento);

            // Adiciona os dados ao objeto cliente
            cliente = new Cliente(nome, cpf);
            cliente.setDataNascimento(dataNascimento);
            cliente.setTipo(tipo);

            // Cadastra o cliente no Banco de Dados
            sistemaDAO.cadastrarCliente(cliente);

            // Atualiza a tabela de clientes
            painelCadastroCliente.fireTabelaCliente();

            // Limpa os componentes
            painelCadastroCliente.limpaComponentes();

        } catch (NomeInvalidoException | CpfInvalidoException | DataInvalidaException ex) {

            JOptionPane.showMessageDialog(null, ex.getMessage(), "Atenção", JOptionPane.WARNING_MESSAGE);

        } catch (NullPointerException | NumberFormatException ex) {

            JOptionPane.showMessageDialog(null, "Apenas a data de nascimento é opcional", "Atenção", JOptionPane.WARNING_MESSAGE);
        }

    }

}
