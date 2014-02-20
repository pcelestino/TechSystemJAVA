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
public class MenuEditarUsuario implements ActionListener {

    private final PainelMenuBar painelMenuBar;

    public MenuEditarUsuario(PainelMenuBar painelMenuBar) {
        this.painelMenuBar = painelMenuBar;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Remove todos os componentes de todos os paineis
        painelMenuBar.removerComponentes();

        // Carrega os componentes
        telaPrincipal.getPainelCards().getPainelEdicaoUsuario().carregarComponentes();

        // Torna os componentes desabilitados
        telaPrincipal.getPainelCards().getPainelEdicaoUsuario().desabilitaComponentes();

        // Limpa todos os componentes
        telaPrincipal.getPainelCards().getPainelEdicaoUsuario().limpaComponentes();

        // Ativa o painel de edição de usuários
        telaPrincipal.getPainelCards().setPainel("EdicaoUsuario");
    }

}
