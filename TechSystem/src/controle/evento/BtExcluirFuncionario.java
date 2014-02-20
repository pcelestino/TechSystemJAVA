package controle.evento;

import static controle.ControleLoginSistema.telaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.SistemaDAO;
import visao.edicao.PainelEdicaoFuncionario;

/**
 * Acoes do painel para edição de funcionário
 *
 * @author Pedro Celestino Silveira Junior
 */
public class BtExcluirFuncionario implements ActionListener {

    private int linha;
    private String cpfSelecionado;
    private SistemaDAO sistemaDAO;
    private PainelEdicaoFuncionario painelEdicaoFuncionario;

    @Override
    public void actionPerformed(ActionEvent e) {

        sistemaDAO = new SistemaDAO();
        painelEdicaoFuncionario = telaPrincipal.getPainelCards().getPainelEdicaoFuncionario();

        int opcao = JOptionPane.showConfirmDialog(null, "Deseja Realmente Excluir?", "Atenção", JOptionPane.YES_NO_OPTION);

        if (opcao == JOptionPane.YES_OPTION) {

            // Captura a linha selecionada na tabela do formulário
            linha = painelEdicaoFuncionario.getLinhaSelecionada();

            // Seleciona o cpf da tabela do formulário
            cpfSelecionado = painelEdicaoFuncionario.getTextoEm(linha, 1);

            // Remove caracteres não numéricos
            cpfSelecionado = cpfSelecionado.replaceAll("\\D", "");

            // Exclui o funcionário do Banco de Dados
            sistemaDAO.excluirLinha("funcionarios", "cpf_funcionario", cpfSelecionado);

            // Atualiza a tabela de funcionários
            painelEdicaoFuncionario.fireTabelaFuncionario();

            // Desabilita os componentes
            painelEdicaoFuncionario.desabilitaComponentes();

            // Limpa os componentes
            painelEdicaoFuncionario.limpaComponentes();
        }
    }

}
