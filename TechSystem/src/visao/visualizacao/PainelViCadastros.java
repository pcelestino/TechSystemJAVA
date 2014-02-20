package visao.visualizacao;

import com.alee.laf.scroll.WebScrollPane;
import com.alee.laf.tabbedpane.WebTabbedPane;
import com.alee.laf.table.WebTable;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.TitledBorder;
import visao.PainelSistema;

/**
 * Painel para visualização dos cadastros
 *
 * @author Pedro Celestino Silveira Junior
 */
public class PainelViCadastros extends PainelSistema {

    private static final long serialVersionUID = 1L;
    private GridBagConstraints restricoes;
    private TitledBorder bordaTitulo;
    private WebTabbedPane painelAbas;
    private WebTable tabelaVenda, tabelaCliente, tabelaFuncionario, tabelaUsuario;
    private WebScrollPane spVenda;

    /**
     * Carrega os componentes do painel
     */
    public void carregarComponentes() {

        restricoes = new GridBagConstraints();

        // Edição do título do painel
        bordaTitulo = new TitledBorder("Visualização de Cadastros");
        bordaTitulo.setTitleJustification(TitledBorder.CENTER);
        super.setBorder(bordaTitulo);

        restricoes.insets = new Insets(20, 20, 20, 20);

        restricoes.anchor = GridBagConstraints.WEST;
        restricoes.gridy = 0;
        restricoes.gridx = 0;
        restricoes.weighty = 1;
        restricoes.weightx = 1;
        restricoes.gridheight = GridBagConstraints.REMAINDER;
        restricoes.fill = GridBagConstraints.BOTH;

        painelAbas = new WebTabbedPane();
        painelAbas.setRound(3);
        painelAbas.setFontSize(14);
        painelAbas.setPreferredSize(new Dimension(200, 100));
        painelAbas.setTabPlacement(WebTabbedPane.TOP);
        painelAbas.setFocusable(false);

        tabelaVenda = super.gerarTabelaVenda();
        spVenda = super.getSpPessoas();
        painelAbas.addTab("Vendas", spVenda);

        tabelaUsuario = super.gerarTabelaUsuario();
        spVenda = super.getSpPessoas();
        painelAbas.addTab("Usuários", spVenda);

        tabelaCliente = super.gerarTabelaCliente();
        spVenda = super.getSpPessoas();
        painelAbas.addTab("Clientes", spVenda);

        tabelaFuncionario = super.gerarTabelaFuncionario();
        spVenda = super.getSpPessoas();
        painelAbas.addTab("Funcionários", spVenda);

        this.add(painelAbas, restricoes);
        this.revalidate();
    }

    /**
     * Retorna uma tabela de venda
     *
     * @return uma tabela de venda
     */
    public WebTable getTabelaVenda() {
        return tabelaVenda;
    }

    /**
     * Retorna uma tabela de cliente
     *
     * @return uma tabela de cliente
     */
    public WebTable getTabelaCliente() {
        return tabelaCliente;
    }

    /**
     * Retorna uma tabela de funcionário
     *
     * @return uma tabela de funcionário
     */
    public WebTable getTabelaFuncionario() {
        return tabelaFuncionario;
    }

    /**
     * Retorna uma tabela de usuário
     *
     * @return uma tabela de usuário
     */
    public WebTable getTabelaUsuario() {
        return tabelaUsuario;
    }
}
