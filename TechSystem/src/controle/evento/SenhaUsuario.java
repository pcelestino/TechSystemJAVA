package controle.evento;

import static controle.ControleLoginSistema.telaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import visao.edicao.PainelEdicaoUsuario;

/**
 * Acoes do painel para edição de usuário
 * @author Pedro Celestino Silveira Junior
 */
public class SenhaUsuario implements ActionListener {

    private PainelEdicaoUsuario painelEdicaoUsuario;
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        painelEdicaoUsuario = telaPrincipal.getPainelCards().getPainelEdicaoUsuario();

        if (painelEdicaoUsuario.getCheckBoxSenha().isSelected()) {

            painelEdicaoUsuario.getPwSenha().setEnabled(true);
        } else {
            painelEdicaoUsuario.getPwSenha().setEnabled(false);
        }
    }

}
