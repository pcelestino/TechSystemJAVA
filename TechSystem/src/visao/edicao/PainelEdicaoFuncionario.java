package visao.edicao;

import com.alee.laf.button.WebButton;
import com.alee.laf.scroll.WebScrollPane;
import com.alee.laf.table.WebTable;
import com.alee.laf.text.WebFormattedTextField;
import com.alee.laf.text.WebTextField;
import controle.evento.BtAtualizarFuncionario;
import controle.evento.BtExcluirFuncionario;
import controle.evento.MouseEditarFuncionario;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;
import javax.swing.border.TitledBorder;
import visao.PainelSistema;

/**
 * Painel para edição de funcionário
 * @author Pedro Celestino Silveira Junior
 */
public class PainelEdicaoFuncionario extends PainelSistema {

    private static final long serialVersionUID = 1L;
    
    private BtAtualizarFuncionario acaoBtAtualizarFuncionario;
    private BtExcluirFuncionario acaoBtExcluirFuncionario;
    private MouseEditarFuncionario acaoMouseEditarFuncionario;
    
    private GridBagConstraints restricoes;
    private TitledBorder bordaTitulo;
    private WebTextField tfNome;
    private WebFormattedTextField ftfCpf, ftfSalario, ftfDataNascimento;
    private WebButton btAtualizar, btExcluir;
    private WebScrollPane spFuncionario;
    private WebTable tabelaFuncionario;
    private Object ob;
    private ArrayList<Object> obj;

    /**
     * Carrega os componentes do painel
     */
    public void carregarComponentes() {

        acaoBtAtualizarFuncionario = new BtAtualizarFuncionario();
        acaoBtExcluirFuncionario = new BtExcluirFuncionario();
        acaoMouseEditarFuncionario = new MouseEditarFuncionario();
        
        restricoes = new GridBagConstraints();
        tabelaFuncionario = super.gerarTabelaFuncionario();
        tfNome = super.getTfNome();
        ftfCpf = super.getFtfCpf();
        ftfDataNascimento = super.getFtfDataNascimento();
        ftfSalario = super.getFtfSalario();
        btAtualizar = super.getBtAtualizar();
        btExcluir = super.getBtExcluir();
        spFuncionario = super.getSpPessoas();

        // Edição do título do painel
        bordaTitulo = new TitledBorder("Edição de Funcionários");
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
        this.add(btAtualizar, restricoes);

        restricoes.gridy = 4;
        restricoes.gridx = 0;
        this.add(btExcluir, restricoes);

        restricoes.gridy = 0;
        restricoes.gridx = 3;
        restricoes.weightx = 1;
        restricoes.gridheight = GridBagConstraints.REMAINDER;
        restricoes.fill = GridBagConstraints.BOTH;
        this.add(spFuncionario, restricoes);

        tabelaFuncionario.addMouseListener(acaoMouseEditarFuncionario);
        super.setBtAtualizar("btAtualizarFuncionario", acaoBtAtualizarFuncionario);
        super.setBtExcluir("btExcluirFuncionario", acaoBtExcluirFuncionario);
        
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
     * @return uma tabela de funcionário
     */
    public WebTable getTabelaFuncionario() {
        return tabelaFuncionario;
    }

    /**
     * Retorna um TextField para o nome do funcionário
     * @return um TextField para o nome do funcionário
     */
    @Override
    public WebTextField getTfNome() {
        return tfNome;
    }

    /**
     * Retorna um TextField para o cpf do funcionário
     * @return um TextField para o cpf do funcionário
     */
    @Override
    public WebFormattedTextField getFtfCpf() {
        return ftfCpf;
    }

    /**
     * Retorna um TextField para a data de nascimento do funcionário
     * @return um TextField para a data de nascimento do funcionário
     */
    @Override
    public WebFormattedTextField getFtfDataNascimento() {
        return ftfDataNascimento;
    }

    /**
     * Retorna um TextField para o salário do funcionário
     * @return um TextField para o salário do funcionário
     */
    @Override
    public WebFormattedTextField getFtfSalario() {
        return ftfSalario;
    }

    /**
     * Retorna um Button para atualização do funcionário
     * @return um Button para atualização do funcionário
     */
    @Override
    public WebButton getBtAtualizar() {

        return btAtualizar;
    }

    /**
     * Retorna um Button para exclusão do funcionário
     * @return um Button para exclusão do funcionário
     */
    @Override
    public WebButton getBtExcluir() {

        return btExcluir;
    }

    /**
     * Retorna a linha selecionada na tabela de funcionário
     * @return a linha selecionada na tabela de funcionário
     */
    public int getLinhaSelecionada() {
        return this.getTabelaFuncionario().getSelectedRow();
    }

    /**
     * Retorna uma String de uma tabela de acordo com os parametros linha e coluna
     * @param linha a linha da tabela de funcionário
     * @param coluna a coluna da tabela de funcionário
     * @return uma String de uma tabela de acordo com os parametros linha e coluna
     */
    public String getTextoEm(int linha, int coluna) {

        ob = this.getTabelaFuncionario().getValueAt(linha, coluna);

        return ob.toString();
    }

    /**
     * Retorna um Object contendo os valores de uma linha da tabela de funcionário
     * @param linhaSelecionada a linha selecionada na tabela de funcionário
     * @return um Object contendo os valores de uma linha da tabela de funcionário
     */
    public ArrayList<Object> getValoresEm(int linhaSelecionada) {

        int qtColunas = this.getTabelaFuncionario().getColumnCount();
        obj = new ArrayList<>();

        for (int i = 0; i < qtColunas; i++) {
            obj.add(this.getTabelaFuncionario().getValueAt(linhaSelecionada, i));
        }

        return obj;
    }

    /**
     * Habilita os componentes do painel
     */
    public void habilitaComponentes() {

        this.getTfNome().setEnabled(true);
        this.getFtfCpf().setEnabled(true);
        this.getFtfDataNascimento().setEnabled(true);
        this.getFtfSalario().setEnabled(true);
        this.getBtAtualizar().setEnabled(true);
        this.getBtExcluir().setEnabled(true);
    }

    /**
     * Desabilita os componentes do painel
     */
    public void desabilitaComponentes() {

        this.getTfNome().setEnabled(false);
        this.getFtfCpf().setEnabled(false);
        this.getFtfDataNascimento().setEnabled(false);
        this.getFtfSalario().setEnabled(false);
        this.getBtAtualizar().setEnabled(false);
        this.getBtExcluir().setEnabled(false);
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
