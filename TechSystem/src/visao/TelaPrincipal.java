package visao;

import com.alee.extended.window.ComponentMoveAdapter;
import com.alee.laf.WebLookAndFeel;
import com.alee.laf.rootpane.WebFrame;
import java.awt.HeadlessException;

/**
 * A tela principal do sistema
 *
 * @author Pedro Celestino Silveira Junior
 */
public class TelaPrincipal extends WebFrame {

    private static final long serialVersionUID = 1L;
    private final PainelMenuBar painelMenuBar;
    private final PainelCards painelCards;

    /**
     * Constrói a tela principal do sistema
     *
     * @throws HeadlessException
     */
    public TelaPrincipal() throws HeadlessException {
        ComponentMoveAdapter.install(rootPane);
        painelCards = new PainelCards();
        painelMenuBar = new PainelMenuBar();
        this.setIconImages(WebLookAndFeel.getImages());
        setDefaultCloseOperation(WebFrame.EXIT_ON_CLOSE);
        this.setSize(1024, 576);
        this.setShowResizeCorner(false);
        setJMenuBar(painelMenuBar.getMenuBar());

        this.add(painelCards.getPnPrincipal());
        this.setLocationRelativeTo(null);
    }

    /**
     * Retorna um painel de menu
     *
     * @return um painel de menu
     */
    public PainelMenuBar getPainelMenuBar() {
        return painelMenuBar;
    }

    /**
     * Retorna um painel de Cards
     *
     * @return um painel de Cards
     */
    public PainelCards getPainelCards() {
        return painelCards;
    }

}
