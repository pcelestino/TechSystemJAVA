package controle.evento;

import static controle.ControleLoginSistema.telaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import visao.PainelMenuBar;

/**
 * Acoes do painel de menu
 * @author Pedro Celestino Silveira Junior
 */
public class MenuCadastrarFuncionario implements ActionListener {
    
    private final PainelMenuBar painelMenuBar;

    public MenuCadastrarFuncionario(PainelMenuBar painelMenuBar) {
        this.painelMenuBar = painelMenuBar;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
            // Remove todos os componentes de todos os paineis
            painelMenuBar.removerComponentes();

            // Carrega os componentes
            telaPrincipal.getPainelCards().getPainelCadastroFuncionario().carregarComponentes();

            // Limpa todos os componentes
            telaPrincipal.getPainelCards().getPainelCadastroFuncionario().limpaComponentes();

            // Ativa o painel de cadastros de funcionários
            telaPrincipal.getPainelCards().setPainel("CadastroFuncionario");
    }
    
}
