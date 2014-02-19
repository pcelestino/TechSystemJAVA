package controle.evento;

import static controle.ControleLoginSistema.telaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.SistemaDAO;
import visao.edicao.PainelEdicaoCliente;

/**
 * Acoes do painel para edição de cliente
 * @author Pedro Celestino Silveira Junior
 */
public class BtExcluirCliente implements ActionListener {

    private int linha;
    private String cpfSelecionado;
    private SistemaDAO sistemaDAO;
    private PainelEdicaoCliente painelEdicaoCliente;
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        sistemaDAO = new SistemaDAO();
        painelEdicaoCliente = telaPrincipal.getPainelCards().getPainelEdicaoCliente();
        
        int opcao = JOptionPane.showConfirmDialog(null, "Deseja Realmente Excluir?", "Atenção", JOptionPane.YES_NO_OPTION);

        if (opcao == JOptionPane.YES_OPTION) {

            // Captura a linha selecionada na tabela do formulário
            linha = painelEdicaoCliente.getLinhaSelecionada();

            // Seleciona o cpf da tabela do formulário
            cpfSelecionado = painelEdicaoCliente.getTextoEm(linha, 1);

            // Remove caracteres não numéricos
            cpfSelecionado = cpfSelecionado.replaceAll("\\D", "");

            // Exclui o cliente do Banco de Dados
            sistemaDAO.excluirLinha("clientes", "cpf_cliente", cpfSelecionado);

            // Atualiza a tabela de clientes
            painelEdicaoCliente.fireTabelaCliente();

            // Desabilita os componentes
            painelEdicaoCliente.desabilitaComponentes();

            // Limpa os componentes
            painelEdicaoCliente.limpaComponentes();
        }
    }

}
