package controle.evento;

import static controle.ControleLoginSistema.telaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.SistemaDAO;
import visao.edicao.PainelEdicaoVenda;

/**
 * Acoes do painel para edição de venda
 * @author Pedro Celestino Silveira Junior
 */
public class BtExcluirVenda implements ActionListener {

    private int linha;
    private String idVenda;
    private SistemaDAO sistemaDAO;
    private PainelEdicaoVenda painelEdicaoVenda;
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        sistemaDAO = new SistemaDAO();
        painelEdicaoVenda = telaPrincipal.getPainelCards().getPainelEdicaoVenda();
        
        int opcao = JOptionPane.showConfirmDialog(null, "Deseja Realmente Excluir?", "Atenção", JOptionPane.YES_NO_OPTION);

        if (opcao == JOptionPane.YES_OPTION) {

            // Captura a linha selecionada na tabela do formulário
            linha = painelEdicaoVenda.getLinhaSelecionada();

            // Seleciona o ID da tabela do formulário
            idVenda = painelEdicaoVenda.getTextoEm(linha, 0);

            // Exclui a venda do Banco de Dados
            sistemaDAO.excluirLinha("vendas", "id_venda", idVenda);

            // Atualiza a tabela de clientes
            painelEdicaoVenda.fireTabelaVenda();

            // Desabilita os componentes
            painelEdicaoVenda.desabilitaComponentes();

            // Limpa os componentes
            painelEdicaoVenda.limpaComponentes();
        }
    }

}
