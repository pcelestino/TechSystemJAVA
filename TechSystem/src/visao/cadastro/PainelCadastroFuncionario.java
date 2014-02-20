package visao.cadastro;

import com.alee.laf.scroll.WebScrollPane;
import com.alee.laf.table.WebTable;
import com.alee.laf.text.WebFormattedTextField;
import com.alee.laf.text.WebTextField;
import controle.evento.BtCadastrarFuncionario;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.TitledBorder;
import visao.PainelSistema;

/**
 * Painel para cadastro de funcionário
 *
 * @author Pedro Celestino Silveira Junior
 */
public class PainelCadastroFuncionario extends PainelSistema {

    private static final long serialVersionUID = 1L;
    private BtCadastrarFuncionario acaoBtCadastrarFuncionario;
    private GridBagConstraints restricoes;
    private TitledBorder bordaTitulo;
    private WebTextField tfNome;
    private WebFormattedTextField ftfCpf, ftfSalario, ftfDataNascimento;
    private WebScrollPane spFuncionario;
    private WebTable tabelaFuncionario;

    /**
     * Carrega os componentes do painel
     */
    public void carregarComponentes() {

        acaoBtCadastrarFuncionario = new BtCadastrarFuncionario();
        restricoes = new GridBagConstraints();
        tabelaFuncionario = super.gerarTabelaFuncionario();
        tfNome = super.getTfNome();
        ftfCpf = super.getFtfCpf();
        ftfDataNascimento = super.getFtfDataNascimento();
        ftfSalario = super.getFtfSalario();
        spFuncionario = super.getSpPessoas();

        // Edição do título do painel
        bordaTitulo = new TitledBorder("Cadastro de Funcionários");
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
        this.add(super.getLbSistema("Salário: "), restricoes);

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
        this.add(ftfSalario, restricoes);

        restricoes.gridy = 4;
        restricoes.gridx = 1;
        this.add(super.getBtCadastrar(), restricoes);

        restricoes.gridy = 0;
        restricoes.gridx = 3;
        restricoes.weightx = 1;
        restricoes.gridheight = GridBagConstraints.REMAINDER;
        restricoes.fill = GridBagConstraints.BOTH;
        this.add(spFuncionario, restricoes);

        super.setBtCadastrar("btCadastrarFuncionario", acaoBtCadastrarFuncionario);

        this.revalidate();
    }

    /**
     * Atualiza a tabela de funcionário
     */
    public void fireTabelaFuncionario() {

        tabelaFuncionario = super.atualizarTabelaFuncionario();
        this.repaint();
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
     * Retorna um TextField para o nome do funcionário
     *
     * @return um TextField para o nome do funcionário
     */
    @Override
    public WebTextField getTfNome() {
        return tfNome;
    }

    /**
     * Retorna um TextField para o cpf do funcionário
     *
     * @return um TextField para o cpf do funcionário
     */
    @Override
    public WebFormattedTextField getFtfCpf() {
        return ftfCpf;
    }

    /**
     * Retorna um TextField para a data de nascimento do funcionário
     *
     * @return um TextField para a data de nascimento do funcionário
     */
    @Override
    public WebFormattedTextField getFtfDataNascimento() {
        return ftfDataNascimento;
    }

    /**
     * Retorna um TextField para salario do funcionário
     *
     * @return um TextField para salario do funcionário
     */
    @Override
    public WebFormattedTextField getFtfSalario() {
        return ftfSalario;
    }

    /**
     * Limpa os dados dos componentes
     */
    public void limpaComponentes() {

        this.getTfNome().setText(null);
        this.getFtfCpf().setText(null);
        this.getFtfDataNascimento().setText(null);
        this.getFtfSalario().setText(null);
    }

}
