package controle.evento;

import static controle.ControleLoginSistema.telaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import visao.PainelMenuBar;

/**
 * Acoes do painel de menu
 *
 * @author Pedro Celestino Silveira Junior
 */
public class MenuEditarFuncionario implements ActionListener {

    private final PainelMenuBar painelMenuBar;

    public MenuEditarFuncionario(PainelMenuBar painelMenuBar) {
        this.painelMenuBar = painelMenuBar;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Remove todos os componentes de todos os paineis
        painelMenuBar.removerComponentes();

        // Carrega os componentes
        telaPrincipal.getPainelCards().getPainelEdicaoFuncionario().carregarComponentes();

        // Torna os componentes desabilitados
        telaPrincipal.getPainelCards().getPainelEdicaoFuncionario().desabilitaComponentes();

        // Limpa todos os componentes
        telaPrincipal.getPainelCards().getPainelEdicaoFuncionario().limpaComponentes();

        // Ativa o painel de edição de funcionários
        telaPrincipal.getPainelCards().setPainel("EdicaoFuncionario");
    }

}
