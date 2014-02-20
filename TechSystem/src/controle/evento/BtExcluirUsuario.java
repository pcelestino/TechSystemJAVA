package controle.evento;

import static controle.ControleLoginSistema.telaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.SistemaDAO;
import visao.edicao.PainelEdicaoUsuario;

/**
 * Acoes do painel para edição de usuário
 *
 * @author Pedro Celestino Silveira Junior
 */
public class BtExcluirUsuario implements ActionListener {

    private int linha;
    private String cpfSelecionado;
    private SistemaDAO sistemaDAO;
    private PainelEdicaoUsuario painelEdicaoUsuario;

    @Override
    public void actionPerformed(ActionEvent e) {

        sistemaDAO = new SistemaDAO();
        painelEdicaoUsuario = telaPrincipal.getPainelCards().getPainelEdicaoUsuario();

        int opcao = JOptionPane.showConfirmDialog(null, "Deseja Realmente Excluir?", "Atenção", JOptionPane.YES_NO_OPTION);

        if (opcao == JOptionPane.YES_OPTION) {

            // Captura a linha selecionada na tabela do formulário
            linha = painelEdicaoUsuario.getLinhaSelecionada();

            // Seleciona o cpf da tabela do formulário
            cpfSelecionado = painelEdicaoUsuario.getTextoEm(linha, 1);

            // Remove caracteres não numéricos
            cpfSelecionado = cpfSelecionado.replaceAll("\\D", "");

            // Exclui o usuário do Banco de Dados
            sistemaDAO.excluirLinha("usuarios", "cpf_usuario", cpfSelecionado);

            // Atualiza a tabela de clientes
            painelEdicaoUsuario.fireTabelaUsuario();

            // Desabilita os componentes
            painelEdicaoUsuario.desabilitaComponentes();

            // Limpa os componentes
            painelEdicaoUsuario.limpaComponentes();
        }
    }

}
