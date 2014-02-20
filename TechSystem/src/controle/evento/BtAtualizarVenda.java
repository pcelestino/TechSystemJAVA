package controle.evento;

import static controle.ControleLoginSistema.telaPrincipal;
import controle.excecao.NomeVendaInvalidoException;
import controle.validacao.Validador;
import controle.excecao.ValorInvalidoException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.SistemaDAO;
import modelo.Venda;
import visao.edicao.PainelEdicaoVenda;

/**
 * Acoes do painel para edição de venda
 *
 * @author Pedro Celestino Silveira Junior
 */
public class BtAtualizarVenda implements ActionListener {

    private Venda venda;
    private PainelEdicaoVenda painelEdicaoVenda;
    private String nomeVenda, valorVenda, cpfCliente, cpfFuncionario, idVenda;
    private Validador valida;
    private SistemaDAO sistemaDAO;
    int linha;

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            sistemaDAO = new SistemaDAO();
            painelEdicaoVenda = telaPrincipal.getPainelCards().getPainelEdicaoVenda();

            // Instância o objeto Validador para validar os dados
            valida = new Validador();

            // Captura a linha selecionada na tabela do formulário
            linha = painelEdicaoVenda.getLinhaSelecionada();

            // Seleciona o ID da tabela do formulário
            idVenda = painelEdicaoVenda.getTextoEm(linha, 0);

            // Recupera os dados digitados no painel de edição
            nomeVenda = painelEdicaoVenda.getTfNomeVenda().getText();
            valorVenda = painelEdicaoVenda.getFtfValorVenda().getText();
            cpfCliente = painelEdicaoVenda.getCbCpfCliente().getSelectedItem().toString();
            cpfFuncionario = painelEdicaoVenda.getCbCpfFuncionario().getSelectedItem().toString();

            // Método necessário para ser compatível com double
            valorVenda = valorVenda.replaceAll(",", ".");

            // Remove caracteres não numéricos
            cpfFuncionario = cpfFuncionario.replaceAll("\\D", "");
            cpfCliente = cpfCliente.replaceAll("\\D", "");

            // Método para validar os dados
            valida.validarNomeVenda(nomeVenda);
            valida.validarValorVenda(Double.parseDouble(valorVenda));

            // Adiciona os dados ao objeto venda
            venda = new Venda(cpfCliente, cpfFuncionario, nomeVenda, Double.parseDouble(valorVenda));

            // Cadastra a venda no Banco de Dados
            sistemaDAO.atualizarVenda(venda, idVenda);

            // Atualiza a tabela de vendas
            painelEdicaoVenda.fireTabelaVenda();

            // Limpa os componentes
            painelEdicaoVenda.limpaComponentes();

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
