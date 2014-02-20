package controle.evento;

import static controle.ControleLoginSistema.telaPrincipal;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import visao.edicao.PainelEdicaoCliente;

/**
 * Acoes do painel para edição de cliente
 *
 * @author Pedro Celestino Silveira Junior
 */
public class MouseEditarCliente implements MouseListener {

    private PainelEdicaoCliente painelEdicaoCliente;
    private ArrayList<Object> obj;
    private int linha;

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {

        painelEdicaoCliente = telaPrincipal.getPainelCards().getPainelEdicaoCliente();

        // Habilita os componentes
        painelEdicaoCliente.habilitaComponentes();

        // Captura a linha selecionada na tabela do formulário
        linha = painelEdicaoCliente.getLinhaSelecionada();

        // Obtém todos os valores da linha selecionada na tabela
        obj = painelEdicaoCliente.getValoresEm(linha);

        // Seta os valores nos Componentes para serem editados
        painelEdicaoCliente.getTfNome().setText(obj.get(0).toString());
        painelEdicaoCliente.getFtfCpf().setText(obj.get(1).toString());
        painelEdicaoCliente.getFtfDataNascimento().setText(obj.get(2).toString());
        painelEdicaoCliente.getCbTipo().setSelectedItem(obj.get(3).toString());
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
