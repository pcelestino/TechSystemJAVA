package visao.cadastro;

import com.alee.laf.combobox.WebComboBox;
import com.alee.laf.scroll.WebScrollPane;
import com.alee.laf.table.WebTable;
import com.alee.laf.text.WebFormattedTextField;
import com.alee.laf.text.WebTextField;
import controle.evento.BtCadastrarCliente;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.TitledBorder;
import visao.PainelSistema;

/**
 * Painel para cadastro de cliente
 *
 * @author Pedro Celestino Silveira Junior
 */
public class PainelCadastroCliente extends PainelSistema {

    private static final long serialVersionUID = 1L;
    private BtCadastrarCliente acaoBtCadastrarCliente;
    private GridBagConstraints restricoes;
    private TitledBorder bordaTitulo;
    private WebTextField tfNome;
    private WebFormattedTextField ftfCpf, ftfDataNascimento;
    private WebComboBox cbTipo;
    private WebScrollPane spCliente;
    private WebTable tabelaCliente;

    /**
     * Carrega os componentes do painel
     */
    public void carregarComponentes() {

        acaoBtCadastrarCliente = new BtCadastrarCliente();
        restricoes = new GridBagConstraints();
        tabelaCliente = super.gerarTabelaCliente();
        tfNome = super.getTfNome();
        ftfCpf = super.getFtfCpf();
        ftfDataNascimento = super.getFtfDataNascimento();
        cbTipo = super.getCbTipo();
        spCliente = super.getSpPessoas();

        // Edição do título do painel
        bordaTitulo = new TitledBorder("Cadastro de Clientes");
        bordaTitulo.setTitleJustification(TitledBorder.CENTER);
        super.setBorder(bordaTitulo);

        restricoes.insets = new Insets(20, 20, 20, 20);

        restricoes.anchor = GridBagConstraints.WEST;
        restricoes.gridy = 0;
        restricoes.gridx = 0;
        this.add(super.getLbSistema("Nome: "), restricoes);

        restricoes.gridy = 1;
        restricoes.gridx = 0;
        this.add(super.getLbSistema("CPF: "), restricoes);

        restricoes.gridy = 2;
        restricoes.gridx = 0;
        this.add(super.getLbSistema("Nascimento: "), restricoes);

        restricoes.gridy = 3;
        restricoes.gridx = 0;
        this.add(super.getLbSistema("Tipo: "), restricoes);

        restricoes.weighty = 1;
        restricoes.gridy = 0;
        restricoes.gridx = 1;
        this.add(tfNome, restricoes);

        restricoes.gridy = 1;
        restricoes.gridx = 1;
        this.add(ftfCpf, restricoes);

        restricoes.gridy = 2;
        restricoes.gridx = 1;
        this.add(ftfDataNascimento, restricoes);

        restricoes.gridy = 3;
        restricoes.gridx = 1;
        this.add(cbTipo, restricoes);

        restricoes.gridy = 4;
        restricoes.gridx = 1;
        this.add(super.getBtCadastrar(), restricoes);

        restricoes.gridy = 0;
        restricoes.gridx = 3;
        restricoes.weightx = 1;
        restricoes.gridheight = GridBagConstraints.REMAINDER;
        restricoes.fill = GridBagConstraints.BOTH;
        this.add(spCliente, restricoes);

        super.setBtCadastrar("btCadastrarCliente", acaoBtCadastrarCliente);

        this.revalidate();
    }

    /**
     * Atualiza a tabela de cliente
     */
    public void fireTabelaCliente() {

        tabelaCliente = super.atualizarTabelaCliente();
        this.repaint();
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
     * Retorna um TextField para o nome do cliente
     *
     * @return um TextField para o nome do cliente
     */
    @Override
    public WebTextField getTfNome() {
        return tfNome;
    }

    /**
     * Retorna um TextField para o cpf do cliente
     *
     * @return um TextField para o cpf do cliente
     */
    @Override
    public WebFormattedTextField getFtfCpf() {
        return ftfCpf;
    }

    /**
     * Retorna um TextField para a data de nascimento do cliente
     *
     * @return um TextField para a data de nascimento do cliente
     */
    @Override
    public WebFormattedTextField getFtfDataNascimento() {
        return ftfDataNascimento;
    }

    /**
     * Retorna um ComboBox para o tipo do cliente
     *
     * @return um ComboBox para o tipo do cliente
     */
    @Override
    public WebComboBox getCbTipo() {
        return cbTipo;
    }

    /**
     * Limpa os dados dos componentes
     */
    public void limpaComponentes() {

        this.getTfNome().setText(null);
        this.getFtfCpf().setText(null);
        this.getFtfDataNascimento().setText(null);
        this.getCbTipo().setSelectedItem(null);
    }

}
