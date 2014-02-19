package controle.evento;

import static controle.ControleLoginSistema.telaPrincipal;
import controle.validacao.CpfInvalidoException;
import controle.validacao.DataInvalidaException;
import controle.validacao.NomeInvalidoException;
import controle.validacao.Validador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.Cliente;
import modelo.SistemaDAO;
import visao.edicao.PainelEdicaoCliente;

/**
 * Acoes do painel para edição de cliente
 * @author Pedro Celestino Silveira Junior
 */
public class BtAtualizarCliente implements ActionListener {
    
    private Cliente cliente;
    private Validador valida;
    private String nome, cpf, cpfSelecionado, dataNascimento, tipo;
    private SistemaDAO sistemaDAO;
    private PainelEdicaoCliente painelEdicaoCliente;
    private int linha;
    
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            sistemaDAO = new SistemaDAO();
            painelEdicaoCliente = telaPrincipal.getPainelCards().getPainelEdicaoCliente();
            
            // Instância o objeto Validador para validar os dados
            valida = new Validador();

            // Captura a linha selecionada na tabela do formulário
            linha = painelEdicaoCliente.getLinhaSelecionada();

            // Seleciona o cpf da tabela do formulário
            cpfSelecionado = painelEdicaoCliente.getTextoEm(linha, 1);

            // Recupera os dados digitados no painel de edição
            nome = painelEdicaoCliente.getTfNome().getText();
            cpf = painelEdicaoCliente.getFtfCpf().getText();
            dataNascimento = painelEdicaoCliente.getFtfDataNascimento().getText();
            tipo = painelEdicaoCliente.getCbTipo().getSelectedItem().toString();

            // Remove caracteres não numéricos
            cpfSelecionado = cpfSelecionado.replaceAll("\\D", "");
            cpf = cpf.replaceAll("\\D", "");

            // Método para validar os dados
            valida.validarNome(nome);
            valida.validarCPF(cpf, cpfSelecionado, "cliente");
            valida.validarDataNascimento(dataNascimento);

            // Adiciona os dados ao objeto cliente
            cliente = new Cliente(nome, cpf);
            cliente.setDataNascimento(dataNascimento);
            cliente.setTipo(tipo);

            // Cadastra o cliente no Banco de Dados
            sistemaDAO.atualizarCliente(cliente, cpfSelecionado);

            // Atualiza a tabela de clientes
            painelEdicaoCliente.fireTabelaCliente();

            // Limpa os componentes
            painelEdicaoCliente.limpaComponentes();

        } catch (NomeInvalidoException | CpfInvalidoException | DataInvalidaException ex) {

            JOptionPane.showMessageDialog(null, ex.getMessage(), "Atenção", JOptionPane.WARNING_MESSAGE);

        } catch (NullPointerException | NumberFormatException ex) {

            JOptionPane.showMessageDialog(null, "Apenas a data de nascimento é opcional", "Atenção", JOptionPane.WARNING_MESSAGE);
        }
    }

}
