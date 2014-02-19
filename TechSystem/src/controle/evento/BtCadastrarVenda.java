package controle.evento;

import static controle.ControleLoginSistema.telaPrincipal;
import controle.validacao.NomeVendaInvalidoException;
import controle.validacao.Validador;
import controle.validacao.ValorInvalidoException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.SistemaDAO;
import modelo.Venda;
import visao.cadastro.PainelCadastroVenda;

/**
 * Acoes do painel para cadastro de venda
 * @author Pedro Celestino Silveira Junior
 */
public class BtCadastrarVenda implements ActionListener {
    
    private Venda venda;
    private PainelCadastroVenda painelCadastroVenda;
    private String nomeVenda, valorVenda, cpfCliente, cpfFuncionario;
    private Validador valida;
    private SistemaDAO sistemaDAO;

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            sistemaDAO = new SistemaDAO();
            painelCadastroVenda = telaPrincipal.getPainelCards().getPainelCadastroVenda();
            
            // Instância o objeto Validador para validar os dados
            valida = new Validador();

            // Recupera os dados digitados no painel de cadastro
            nomeVenda = painelCadastroVenda.getTfNomeVenda().getText();
            valorVenda = painelCadastroVenda.getFtfValorVenda().getText();
            cpfCliente = painelCadastroVenda.getCbCpfCliente().getSelectedItem().toString();
            cpfFuncionario = painelCadastroVenda.getCbCpfFuncionario().getSelectedItem().toString();

            // Método necessário para ser compatível com double
            valorVenda = valorVenda.replaceAll(",", ".");

            // Remove caracteres não numéricos
            cpfCliente = cpfCliente.replaceAll("\\D", "");
            cpfFuncionario = cpfFuncionario.replaceAll("\\D", "");

            // Método para validar os dados
            valida.validarNomeVenda(nomeVenda);
            valida.validarValorVenda(Double.parseDouble(valorVenda));

            // Adiciona os dados ao objeto venda
            venda = new Venda(cpfCliente, cpfFuncionario, nomeVenda, Double.parseDouble(valorVenda));

            // Cadastra a venda no Banco de Dados
            sistemaDAO.cadastrarVenda(venda);

            // Atualiza a tabela de vendas
            painelCadastroVenda.fireTabelaVenda();

            // Limpa os componentes
            painelCadastroVenda.limpaComponentes();

        } catch (NomeVendaInvalidoException ex) {

            JOptionPane.showMessageDialog(null, ex.getMessage(), "Atenção", JOptionPane.WARNING_MESSAGE);

        } catch (ValorInvalidoException ex) {

            JOptionPane.showMessageDialog(null, ex.getMessage()
                    + "\n[Valor da venda abaixo de R$ 0.01]", "Atenção", JOptionPane.WARNING_MESSAGE);

        } catch (NullPointerException | NumberFormatException ex) {

            JOptionPane.showMessageDialog(null, "Preencha todos os campos do formulário", "Atenção", JOptionPane.WARNING_MESSAGE);
        }
    }

}
