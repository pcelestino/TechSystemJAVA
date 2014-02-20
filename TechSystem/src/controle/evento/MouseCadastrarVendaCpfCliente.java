package controle.evento;

import static controle.ControleLoginSistema.telaPrincipal;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import visao.cadastro.PainelCadastroVenda;

/**
 * Acoes do painel para edição de cliente
 *
 * @author Pedro Celestino Silveira Junior
 */
public class MouseCadastrarVendaCpfCliente implements MouseListener {

    private PainelCadastroVenda painelCadastroVenda;
    private ArrayList<Object> obj;
    private int linha;

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
        painelCadastroVenda = telaPrincipal.getPainelCards().getPainelCadastroVenda();

        // Captura a linha selecionada na tabela do formulário
        linha = painelCadastroVenda.getLinhaSelecionadaTbCliente();

        // Obtém todos os valores da linha selecionada na tabela
        obj = painelCadastroVenda.getValoresEmTbCliente(linha);

        // Seta os valores nos Componentes para serem editados
        painelCadastroVenda.getCbCpfCliente().setSelectedItem(obj.get(1).toString());
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
