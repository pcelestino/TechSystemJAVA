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
public class MenuEditarCliente implements ActionListener {

    private final PainelMenuBar painelMenuBar;

    public MenuEditarCliente(PainelMenuBar painelMenuBar) {
        this.painelMenuBar = painelMenuBar;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Remove todos os componentes de todos os paineis
        painelMenuBar.removerComponentes();

        // Carrega os componentes
        telaPrincipal.getPainelCards().getPainelEdicaoCliente().carregarComponentes();

        // Torna os componentes desabilitados
        telaPrincipal.getPainelCards().getPainelEdicaoCliente().desabilitaComponentes();

        // Limpa todos os componentes
        telaPrincipal.getPainelCards().getPainelEdicaoCliente().limpaComponentes();

        // Ativa o painel de edição de clientes
        telaPrincipal.getPainelCards().setPainel("EdicaoCliente");
    }

}
