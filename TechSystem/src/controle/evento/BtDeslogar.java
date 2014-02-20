package controle.evento;

import static controle.ControleLoginSistema.telaPrincipal;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import visao.TelaLogin;

/**
 * Acoes do painel de menu
 *
 * @author Pedro Celestino Silveira Junior
 */
public class BtDeslogar implements ActionListener {

    private TelaLogin telaLogin;

    @Override
    public void actionPerformed(ActionEvent e) {
        int opcao = JOptionPane.showConfirmDialog(null,
                "Deseja Realmente Sair?", "Mensagem",
                JOptionPane.YES_NO_OPTION);

        if (opcao == JOptionPane.YES_OPTION) {
            telaPrincipal.dispose();
            telaLogin = new TelaLogin();
            telaLogin.getPnLogin().setPreferredSize(new Dimension(275, 110));
            telaLogin.setTitle("Sistema");
            telaLogin.getCardLogin().show(telaLogin.getPnLogin(), "LoginSistema");
            telaLogin.pack();
            telaLogin.setVisible(true);
        }
    }

}
