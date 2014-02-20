package visao.edicao;

import com.alee.laf.button.WebButton;
import com.alee.laf.combobox.WebComboBox;
import com.alee.laf.scroll.WebScrollPane;
import com.alee.laf.tabbedpane.WebTabbedPane;
import com.alee.laf.table.WebTable;
import com.alee.laf.text.WebFormattedTextField;
import com.alee.laf.text.WebTextField;
import controle.evento.BtAtualizarVenda;
import controle.evento.BtExcluirVenda;
import controle.evento.MouseEditarVenda;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;
import javax.swing.border.TitledBorder;
import visao.PainelSistema;

/**
 * Painel para edição de venda
 *
 * @author Pedro Celestino Silveira Junior
 */
public class PainelEdicaoVenda extends PainelSistema {

    private static final long serialVersionUID = 1L;
    private BtAtualizarVenda acaoBtAtualizarVenda;
    private BtExcluirVenda acaoBtExcluirVenda;
    private MouseEditarVenda acaoMouseEditarVenda;
    private GridBagConstraints restricoes;
    private TitledBorder bordaTitulo;

    private WebTabbedPane painelAbas;
    private WebTextField tfNomeVenda;
    private WebFormattedTextField ftfValorVenda;
    private WebComboBox cbCpfCliente, cbCpfFuncionario;
    private WebButton btAtualizar, btExcluir;
    private WebScrollPane spVenda;
    private WebTable tabelaVenda, tabelaCliente, tabelaFuncionario;
    private ArrayList<Object> obj;
    private Object ob;

    /**
     * Carrega os componentes do painel
     */
    public void carregarComponentes() {

        acaoBtAtualizarVenda = new BtAtualizarVenda();
        acaoBtExcluirVenda = new BtExcluirVenda();
        acaoMouseEditarVenda = new MouseEditarVenda();

        restricoes = new GridBagConstraints();
        tabelaVenda = super.gerarTabelaVenda();
        cbCpfCliente = super.getCbCpfCliente();
        cbCpfFuncionario = super.getCbCpfFuncionario();
        tfNomeVenda = super.getTfNomeVenda();
        ftfValorVenda = super.getFtfValorVenda();
        btAtualizar = super.getBtAtualizar();
        btExcluir = super.getBtExcluir();
        spVenda = super.getSpPessoas();

        // Edição do título do painel
        bordaTitulo = new TitledBorder("Edição de Vendas");
        bordaTitulo.setTitleJustification(TitledBorder.CENTER);
        super.setBorder(bordaTitulo);

        restricoes.insets = new Insets(20, 20, 20, 20);

        restricoes.anchor = GridBagConstraints.WEST;
        restricoes.gridy = 0;
        restricoes.gridx = 0;
        this.add(super.getLbSistema("Cliente: "), restricoes);

        restricoes.gridy = 1;
        restricoes.gridx = 0;
        this.add(super.getLbSistema("Funcionário: "), restricoes);

        restricoes.gridy = 2;
        restricoes.gridx = 0;
        this.add(super.getLbSistema("Venda: "), restricoes);

        restricoes.gridy = 3;
        restricoes.gridx = 0;
        this.add(super.getLbSistema("Valor: "), restricoes);

        restricoes.weighty = 1;
        restricoes.gridy = 0;
        restricoes.gridx = 1;
        this.add(cbCpfCliente, restricoes);

        restricoes.gridy = 1;
        restricoes.gridx = 1;
        this.add(cbCpfFuncionario, restricoes);

        restricoes.gridy = 2;
        restricoes.gridx = 1;
        this.add(tfNomeVenda, restricoes);

        restricoes.gridy = 3;
        restricoes.gridx = 1;
        this.add(ftfValorVenda, restricoes);

        restricoes.gridy = 4;
        restricoes.gridx = 1;
        this.add(btAtualizar, restricoes);

        restricoes.gridy = 4;
        restricoes.gridx = 0;
        this.add(btExcluir, restricoes);

        restricoes.gridy = 0;
        restricoes.gridx = 3;
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

        tabelaCliente = super.gerarTabelaCliente();
        spVenda = super.getSpPessoas();
        painelAbas.addTab("Clientes", spVenda);

        tabelaFuncionario = super.gerarTabelaFuncionario();
        spVenda = super.getSpPessoas();
        painelAbas.addTab("Funcionarios", spVenda);

        this.add(painelAbas, restricoes);

        tabelaVenda.addMouseListener(acaoMouseEditarVenda);
        super.setBtAtualizar("btAtualizarVenda", acaoBtAtualizarVenda);
        super.setBtExcluir("btExcluirVenda", acaoBtExcluirVenda);

        this.revalidate();
    }

    /**
     * Atualiza uma tabela de venda
     */
    public void fireTabelaVenda() {

        painelAbas.removeAll();

        tabelaVenda = super.gerarTabelaVenda();
        spVenda = super.getSpPessoas();
        painelAbas.addTab("Vendas", spVenda);

        tabelaCliente = super.gerarTabelaCliente();
        spVenda = super.getSpPessoas();
        painelAbas.addTab("Clientes", spVenda);

        tabelaFuncionario = super.gerarTabelaFuncionario();
        spVenda = super.getSpPessoas();
        painelAbas.addTab("Funcionarios", spVenda);

        this.add(painelAbas, restricoes);
        tabelaVenda.addMouseListener(acaoMouseEditarVenda);
        this.revalidate();
    }

    /**
     * Atualiza ComboBox de venda
     */
    public void fireCbVenda() {

        cbCpfCliente = super.atualizaCbCpfCliente();
        cbCpfFuncionario = super.atualizaCbCpfFuncionario();
        this.repaint();
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
     * Retorna um ComboBox para o cpf do cliente
     *
     * @return um ComboBox para o cpf do cliente
     */
    @Override
    public WebComboBox getCbCpfCliente() {
        return cbCpfCliente;
    }

    /**
     * Retorna um ComboBox para o cpf do funcionário
     *
     * @return um ComboBox para o cpf do funcionário
     */
    @Override
    public WebComboBox getCbCpfFuncionario() {
        return cbCpfFuncionario;
    }

    /**
     * Retorna um TextField para o nome da venda
     *
     * @return um TextField para o nome da venda
     */
    @Override
    public WebTextField getTfNomeVenda() {
        return tfNomeVenda;
    }

    /**
     * Retorna um TextField para o valor da venda
     *
     * @return um TextField para o valor da venda
     */
    @Override
    public WebFormattedTextField getFtfValorVenda() {
        return ftfValorVenda;
    }

    /**
     * Retorna um Button para atualização da venda
     *
     * @return um Button para atualização da venda
     */
    @Override
    public WebButton getBtAtualizar() {

        return btAtualizar;
    }

    /**
     * Retorna um Button para exclusão da venda
     *
     * @return um Button para exclusão da venda
     */
    @Override
    public WebButton getBtExcluir() {

        return btExcluir;
    }

    /**
     * Retorna a linha selecionada na tabela de venda
     *
     * @return a linha selecionada na tabela de venda
     */
    public int getLinhaSelecionada() {
        return this.getTabelaVenda().getSelectedRow();
    }

    /**
     * Retorna uma String de uma tabela de acordo com os parametros linha e
     * coluna
     *
     * @param linha a linha da tabela de venda
     * @param coluna a coluna da tabela de venda
     * @return uma String de uma tabela de acordo com os parametros linha e
     * coluna
     */
    public String getTextoEm(int linha, int coluna) {

        ob = this.getTabelaVenda().getValueAt(linha, coluna);

        return ob.toString();
    }

    /**
     * Retorna um Object contendo os valores de uma linha da tabela de venda
     *
     * @param linhaSelecionada a linha selecionada na tabela de venda
     * @return um Object contendo os valores de uma linha da tabela de venda
     */
    public ArrayList<Object> getValoresEm(int linhaSelecionada) {

        int qtColunas = this.getTabelaVenda().getColumnCount();
        obj = new ArrayList<>();

        for (int i = 0; i < qtColunas; i++) {
            obj.add(this.getTabelaVenda().getValueAt(linhaSelecionada, i));
        }

        return obj;
    }

    /**
     * Habilita os componentes do painel
     */
    public void habilitaComponentes() {

        this.getCbCpfCliente().setEnabled(true);
        this.getCbCpfFuncionario().setEnabled(true);
        this.getTfNomeVenda().setEnabled(true);
        this.getFtfValorVenda().setEnabled(true);
        this.getBtAtualizar().setEnabled(true);
        this.getBtExcluir().setEnabled(true);
    }

    /**
     * Desabilita os componentes do painel
     */
    public void desabilitaComponentes() {

        this.getCbCpfCliente().setEnabled(false);
        this.getCbCpfFuncionario().setEnabled(false);
        this.getTfNomeVenda().setEnabled(false);
        this.getFtfValorVenda().setEnabled(false);
        this.getBtAtualizar().setEnabled(false);
        this.getBtExcluir().setEnabled(false);
    }

    /**
     * Limpa os dados dos componentes
     */
    public void limpaComponentes() {

        this.getCbCpfCliente().setSelectedItem(null);
        this.getCbCpfFuncionario().setSelectedItem(null);
        this.getTfNomeVenda().setText(null);
        this.getFtfValorVenda().setText(null);
    }

}
