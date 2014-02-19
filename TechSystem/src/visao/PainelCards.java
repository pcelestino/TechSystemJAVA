package visao;

import java.awt.CardLayout;

import javax.swing.JPanel;

import visao.cadastro.PainelCadastroCliente;
import visao.cadastro.PainelCadastroFuncionario;
import visao.cadastro.PainelCadastroUsuario;
import visao.cadastro.PainelCadastroVenda;
import visao.edicao.PainelEdicaoCliente;
import visao.edicao.PainelEdicaoFuncionario;
import visao.edicao.PainelEdicaoUsuario;
import visao.edicao.PainelEdicaoVenda;
import visao.visualizacao.PainelViCadastros;

/**
 * Painel que agrupa todos os painéis
 * @author Pedro Celestino Silveira Junior
 */
public class PainelCards {

    private CardLayout card;
    private final JPanel pnPrincipal;
    private final PainelCadastroCliente painelCadastroCliente;
    private final PainelCadastroFuncionario painelCadastroFuncionario;
    private final PainelCadastroUsuario painelCadastroUsuario;
    private final PainelCadastroVenda painelCadastroVenda;
    private final PainelEdicaoCliente painelEdicaoCliente;
    private final PainelEdicaoFuncionario painelEdicaoFuncionario;
    private final PainelEdicaoUsuario painelEdicaoUsuario;
    private final PainelEdicaoVenda painelEdicaoVenda;
    private final PainelViCadastros painelViCadastros;

    /**
     * Inicializa e agrupa os paineis em um CardLayout
     */
    public PainelCards() {
        card = new CardLayout();
        pnPrincipal = new JPanel(card);

        painelCadastroCliente = new PainelCadastroCliente();
        painelCadastroFuncionario = new PainelCadastroFuncionario();
        painelCadastroUsuario = new PainelCadastroUsuario();
        painelCadastroVenda = new PainelCadastroVenda();
        painelEdicaoCliente = new PainelEdicaoCliente();
        painelEdicaoFuncionario = new PainelEdicaoFuncionario();
        painelEdicaoUsuario = new PainelEdicaoUsuario();
        painelEdicaoVenda = new PainelEdicaoVenda();
        painelViCadastros = new PainelViCadastros();

        pnPrincipal.add(painelCadastroCliente, "CadastroCliente");
        pnPrincipal.add(painelCadastroFuncionario, "CadastroFuncionario");
        pnPrincipal.add(painelCadastroUsuario, "CadastroUsuario");
        pnPrincipal.add(painelCadastroVenda, "CadastroVenda");

        pnPrincipal.add(painelEdicaoCliente, "EdicaoCliente");
        pnPrincipal.add(painelEdicaoFuncionario, "EdicaoFuncionario");
        pnPrincipal.add(painelEdicaoUsuario, "EdicaoUsuario");
        pnPrincipal.add(painelEdicaoVenda, "EdicaoVenda");

        pnPrincipal.add(painelViCadastros, "VisualizacaoCadastros");

        card = (CardLayout) pnPrincipal.getLayout();
        card.show(pnPrincipal, "CadastroVenda");
    }

    /**
     * Seleciona o painel
     * @param painel o nome do painel que será exposto
     */
    public void setPainel(String painel) {
        card.show(pnPrincipal, painel);
    }

    /**
     * Retorna o painel principal
     * @return o painel principal
     */
    public JPanel getPnPrincipal() {
        return pnPrincipal;
    }

    /**
     * Retorna um painel para cadastro de cliente
     * @return um painel para cadastro de cliente
     */
    public PainelCadastroCliente getPainelCadastroCliente() {
        return painelCadastroCliente;
    }

    /**
     * Retorna um painel para cadastro de funcionário
     * @return um painel para cadastro de funcionário
     */
    public PainelCadastroFuncionario getPainelCadastroFuncionario() {
        return painelCadastroFuncionario;
    }

    /**
     * Retorna um painel para cadastro de usuário
     * @return um painel para cadastro de usuário
     */
    public PainelCadastroUsuario getPainelCadastroUsuario() {
        return painelCadastroUsuario;
    }

    /**
     * Retorna um painel para cadastro de venda
     * @return um painel para cadastro de venda
     */
    public PainelCadastroVenda getPainelCadastroVenda() {
        return painelCadastroVenda;
    }

    /**
     * Retorna um painel para edição de cliente
     * @return um painel para edição de cliente
     */
    public PainelEdicaoCliente getPainelEdicaoCliente() {
        return painelEdicaoCliente;
    }

    /**
     * Retorna um painel para edição de funcionário
     * @return um painel para edição de funcionário
     */
    public PainelEdicaoFuncionario getPainelEdicaoFuncionario() {
        return painelEdicaoFuncionario;
    }

    /**
     * Retorna um painel para edição de usuário
     * @return um painel para edição de usuário
     */
    public PainelEdicaoUsuario getPainelEdicaoUsuario() {
        return painelEdicaoUsuario;
    }

    /**
     * Retorna um painel para edição de venda
     * @return um painel para edição de venda
     */
    public PainelEdicaoVenda getPainelEdicaoVenda() {
        return painelEdicaoVenda;
    }

    /**
     * Retorna um painel para visualização de cadastros
     * @return um painel para visualização de cadastros
     */
    public PainelViCadastros getPainelViCadastros() {
        return painelViCadastros;
    }

}
