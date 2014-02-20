package sistemaCadastroJDBC;

import com.alee.laf.WebLookAndFeel;
import visao.TelaLogin;

/**
 * Classe principal do sistema
 * @author Pedro Celestino Silveira Junior
 */
public class SistemaCadastroJDBC {

    public static TelaLogin telaLogin;

    /**
     * Inicializa o Sistema
     * @param args
     */
    public static void main(String[] args) {

        WebLookAndFeel.install();
        WebLookAndFeel.setDecorateAllWindows(true);

        telaLogin = new TelaLogin();
        telaLogin.setVisible(true);

    }
}
