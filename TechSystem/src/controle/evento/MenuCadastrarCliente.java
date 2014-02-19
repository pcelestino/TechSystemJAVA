package controle.evento;

import static controle.ControleLoginSistema.telaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import visao.PainelMenuBar;

/**
 * Acoes do painel de menu
 * @author Pedro Celestino Silveira Junior
 */
public class MenuCadastrarCliente implements ActionListener {
    
    private final PainelMenuBar painelMenuBar;

    public MenuCadastrarCliente(PainelMenuBar painelMenuBar) {
        this.painelMenuBar = painelMenuBar;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        
            // Remove todos os componentes de todos os paineis
            painelMenuBar.removerComponentes();

            // Carrega os componentes
            telaPrincipal.getPainelCards().getPainelCadastroCliente().carregarComponentes();

            // Limpa todos os componentes
            telaPrincipal.getPainelCards().getPainelCadastroCliente().limpaComponentes();

            // Ativa o painel de cadastros de clientes
            telaPrincipal.getPainelCards().setPainel("CadastroCliente");
    }
    
}
