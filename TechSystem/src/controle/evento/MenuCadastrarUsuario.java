package controle.evento;

import static controle.ControleLoginSistema.telaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import visao.PainelMenuBar;

/**
 * Acoes do painel de menu
 * @author Pedro Celestino Silveira Junior
 */
public class MenuCadastrarUsuario implements ActionListener {

    private final PainelMenuBar painelMenuBar;

    public MenuCadastrarUsuario(PainelMenuBar painelMenuBar) {
        this.painelMenuBar = painelMenuBar;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
            
            // Remove todos os componentes de todos os paineis
            painelMenuBar.removerComponentes();

            // Carrega os componentes
            telaPrincipal.getPainelCards().getPainelCadastroUsuario().carregarComponentes();

            // Limpa todos os componentes
            telaPrincipal.getPainelCards().getPainelCadastroUsuario().limpaComponentes();

            // Ativa o painel de cadastros de usuários
            telaPrincipal.getPainelCards().setPainel("CadastroUsuario");
    }
    
}
