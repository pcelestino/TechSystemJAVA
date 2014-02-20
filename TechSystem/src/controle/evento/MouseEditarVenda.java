package controle.evento;

import static controle.ControleLoginSistema.telaPrincipal;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import visao.edicao.PainelEdicaoVenda;

/**
 * Acoes do painel para edição de venda
 *
 * @author Pedro Celestino Silveira Junior
 */
public class MouseEditarVenda implements MouseListener {

    private PainelEdicaoVenda painelEdicaoVenda;
    private ArrayList<Object> obj;
    private int linha;
    private String cpfCliente, cpfFuncionario;

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {

        painelEdicaoVenda = telaPrincipal.getPainelCards().getPainelEdicaoVenda();

        // Habilita os componentes
        painelEdicaoVenda.habilitaComponentes();

        // Captura a linha selecionada na tabela do formulário
        linha = painelEdicaoVenda.getLinhaSelecionada();

        // Obtém todos os valores da linha selecionada na tabela
        obj = painelEdicaoVenda.getValoresEm(linha);

        // Recupera os valores passados para a variável obj
        cpfCliente = obj.get(1).toString();
        cpfFuncionario = obj.get(2).toString();

        // Seta os valores nos Componentes para serem editados
        painelEdicaoVenda.getCbCpfCliente().setSelectedItem(cpfCliente);
        painelEdicaoVenda.getCbCpfFuncionario().setSelectedItem(cpfFuncionario);
        painelEdicaoVenda.getTfNomeVenda().setText(obj.get(3).toString());
        painelEdicaoVenda.getFtfValorVenda().setText(obj.get(4).toString());
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
