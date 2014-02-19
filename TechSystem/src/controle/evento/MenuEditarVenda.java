package controle.evento;

import static controle.ControleLoginSistema.telaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import visao.PainelMenuBar;

/**
 * Acoes do painel de menu
 * @author Pedro Celestino Silveira Junior
 */
public class MenuEditarVenda implements ActionListener {
    
    private final PainelMenuBar painelMenuBar;

    public MenuEditarVenda(PainelMenuBar painelMenuBar) {
        this.painelMenuBar = painelMenuBar;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

        // Remove todos os componentes de todos os paineis
        painelMenuBar.removerComponentes();

        // Carrega os componentes
        telaPrincipal.getPainelCards().getPainelEdicaoVenda().carregarComponentes();

        // Torna os componentes desabilitados
        telaPrincipal.getPainelCards().getPainelEdicaoVenda().desabilitaComponentes();

        // Atualiza os comboboxs de vendas
        telaPrincipal.getPainelCards().getPainelEdicaoVenda().fireCbVenda();

        // Limpa todos os componentes
        telaPrincipal.getPainelCards().getPainelEdicaoVenda().limpaComponentes();

        // Ativa o painel de edição de vendas
        telaPrincipal.getPainelCards().setPainel("EdicaoVenda");
    }

}
