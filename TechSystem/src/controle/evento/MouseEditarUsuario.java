package controle.evento;

import static controle.ControleLoginSistema.telaPrincipal;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import visao.edicao.PainelEdicaoUsuario;

/**
 * Acoes do painel para edição de usuário
 * @author Pedro Celestino Silveira Junior
 */
public class MouseEditarUsuario implements MouseListener {

    private PainelEdicaoUsuario painelEdicaoUsuario;
    private ArrayList<Object> obj;
    private int linha;
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
        painelEdicaoUsuario = telaPrincipal.getPainelCards().getPainelEdicaoUsuario();
        
        // Habilita os componentes
        painelEdicaoUsuario.habilitaComponentes();

        // Captura a linha selecionada na tabela do formulário
        linha = painelEdicaoUsuario.getLinhaSelecionada();

        // Obtém todos os valores da linha selecionada na tabela
        obj = painelEdicaoUsuario.getValoresEm(linha);

        // Seta os valores nos Componentes para serem editados
        painelEdicaoUsuario.getTfNome().setText(obj.get(0).toString());
        painelEdicaoUsuario.getFtfCpf().setText(obj.get(1).toString());
        painelEdicaoUsuario.getFtfDataNascimento().setText(obj.get(2).toString());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
