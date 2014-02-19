package controle.evento;

import static controle.ControleLoginSistema.telaPrincipal;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import visao.edicao.PainelEdicaoFuncionario;

/**
 * Acoes do painel para edição de funcionário
 * @author Pedro Celestino Silveira Junior
 */
public class MouseEditarFuncionario implements MouseListener {
    
    private PainelEdicaoFuncionario painelEdicaoFuncionario;
    private ArrayList<Object> obj;
    private int linha;
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
        painelEdicaoFuncionario = telaPrincipal.getPainelCards().getPainelEdicaoFuncionario();
        
        // Habilita os componentes
        painelEdicaoFuncionario.habilitaComponentes();

        // Captura a linha selecionada na tabela do formulário
        linha = painelEdicaoFuncionario.getLinhaSelecionada();

        // Obtém todos os valores da linha selecionada na tabela
        obj = painelEdicaoFuncionario.getValoresEm(linha);

        // Seta os valores nos Componentes para serem editados
        painelEdicaoFuncionario.getTfNome().setText(obj.get(0).toString());
        painelEdicaoFuncionario.getFtfCpf().setText(obj.get(1).toString());
        painelEdicaoFuncionario.getFtfDataNascimento().setText(obj.get(2).toString());
        painelEdicaoFuncionario.getFtfSalario().setText(obj.get(3).toString());
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
