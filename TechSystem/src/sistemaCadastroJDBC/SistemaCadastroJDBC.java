package sistemaCadastroJDBC;

import com.alee.laf.WebLookAndFeel;
import javax.swing.SwingUtilities;
import visao.TelaLogin;

/**
 *
 * @author Pedro Celestino Silveira Junior
 */
public class SistemaCadastroJDBC {

    /**
     *
     */
    public static TelaLogin telaLogin;

    /**
     *
     * @param args
     */
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {

                WebLookAndFeel.install();
                WebLookAndFeel.setDecorateAllWindows(true);

                telaLogin = new TelaLogin();
                telaLogin.setVisible(true);
            }
        });
    }
}
