package controle.evento;

import static controle.ControleLoginSistema.telaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import visao.PainelMenuBar;

/**
 * Acoes do painel de menu
 * @author Pedro Celestino Silveira Junior
 */
public class MenuCadastrarVenda implements ActionListener {

    private final PainelMenuBar painelMenuBar;

    public MenuCadastrarVenda(PainelMenuBar painelMenuBar) {
        this.painelMenuBar = painelMenuBar;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Remove todos os componentes de todos os paineis
        painelMenuBar.removerComponentes();

        // Carrega os componentes
        telaPrincipal.getPainelCards().getPainelCadastroVenda().carregarComponentes();

        // Limpa todos os componentes
        telaPrincipal.getPainelCards().getPainelCadastroVenda().limpaComponentes();

        // Atualiza os comboboxs de vendas
        telaPrincipal.getPainelCards().getPainelCadastroVenda().fireCbVenda();

        // Ativa o painel de cadastros de vendas
        telaPrincipal.getPainelCards().setPainel("CadastroVenda");
    }

}
