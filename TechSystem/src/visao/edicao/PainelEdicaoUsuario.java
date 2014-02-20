package visao.edicao;

import com.alee.laf.button.WebButton;
import com.alee.laf.checkbox.WebCheckBox;
import com.alee.laf.scroll.WebScrollPane;
import com.alee.laf.table.WebTable;
import com.alee.laf.text.WebFormattedTextField;
import com.alee.laf.text.WebPasswordField;
import com.alee.laf.text.WebTextField;
import controle.evento.BtAtualizarUsuario;
import controle.evento.BtExcluirUsuario;
import controle.evento.MouseEditarUsuario;
import controle.evento.SenhaUsuario;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;
import javax.swing.border.TitledBorder;
import visao.PainelSistema;

/**
 * Painel para edição de usuário
 *
 * @author Pedro Celestino Silveira Junior
 */
public class PainelEdicaoUsuario extends PainelSistema {

    private static final long serialVersionUID = 1L;

    private BtAtualizarUsuario acaoBtAtualizarUsuario;
    private BtExcluirUsuario acaoBtExcluirUsuario;
    private MouseEditarUsuario acaoMouseEditarUsuario;
    private SenhaUsuario acaoSenhaUsuario;

    private GridBagConstraints restricoes;
    private TitledBorder bordaTitulo;
    private WebCheckBox checkBoxSenha;
    private WebTextField tfNome;
    private WebFormattedTextField ftfCpf, ftfDataNascimento;
    private WebPasswordField pwSenha;
    private WebButton btAtualizar, btExcluir;
    private WebScrollPane spUsuario;
    private WebTable tabelaUsuario;
    private ArrayList<Object> obj;
    private Object ob;

    /**
     * Carrega os componentes do painel
     */
    public void carregarComponentes() {

        acaoBtAtualizarUsuario = new BtAtualizarUsuario();
        acaoBtExcluirUsuario = new BtExcluirUsuario();
        acaoMouseEditarUsuario = new MouseEditarUsuario();
        acaoSenhaUsuario = new SenhaUsuario();

        restricoes = new GridBagConstraints();
        tabelaUsuario = super.gerarTabelaUsuario();
        tfNome = super.getTfNome();
        ftfCpf = super.getFtfCpf();
        ftfDataNascimento = super.getFtfDataNascimento();
        pwSenha = super.getPwSenha();
        btAtualizar = super.getBtAtualizar();
        btExcluir = super.getBtExcluir();
        spUsuario = super.getSpPessoas();
        checkBoxSenha = super.getCheckBoxSenha();

        // Edição do título do painel
        bordaTitulo = new TitledBorder("Edição de Usuários");
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
        this.add(checkBoxSenha, restricoes);

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
        this.add(pwSenha, restricoes);

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
        this.add(spUsuario, restricoes);

        tabelaUsuario.addMouseListener(acaoMouseEditarUsuario);
        super.setBtAtualizar("btAtualizarUsuario", acaoBtAtualizarUsuario);
        super.setBtExcluir("btExcluirUsuario", acaoBtExcluirUsuario);

        checkBoxSenha.setActionCommand("SenhaUsuario");
        checkBoxSenha.addActionListener(acaoSenhaUsuario);

        this.revalidate();
    }

    /**
     * Atualiza a tabela de usuário
     */
    public void fireTabelaUsuario() {

        tabelaUsuario = super.atualizarTabelaUsuario();
        this.repaint();
    }

    /**
     * Retorna uma tabela de usuário
     *
     * @return uma tabela de usuário
     */
    public WebTable getTabelaUsuario() {
        return tabelaUsuario;
    }

    /**
     * Retorna um TextField para o nome do usuário
     *
     * @return um TextField para o nome do usuário
     */
    @Override
    public WebTextField getTfNome() {
        return tfNome;
    }

    /**
     * Retorna um TextField para o cpf do usuário
     *
     * @return um TextField para o cpf do usuário
     */
    @Override
    public WebFormattedTextField getFtfCpf() {
        return ftfCpf;
    }

    /**
     * Retorna um TextField para a data de nascimento do usuário
     *
     * @return um TextField para a data de nascimento do usuário
     */
    @Override
    public WebFormattedTextField getFtfDataNascimento() {
        return ftfDataNascimento;
    }

    /**
     * Retorna um PasswordField para o salário do usuário
     *
     * @return um PasswordField para o salário do usuário
     */
    @Override
    public WebPasswordField getPwSenha() {
        return pwSenha;
    }

    /**
     * Retorna um Button para atualização do usuário
     *
     * @return um Button para atualização do usuário
     */
    @Override
    public WebButton getBtAtualizar() {

        return btAtualizar;
    }

    /**
     * Retorna um Button para exclusão do usuário
     *
     * @return um Button para exclusão do usuário
     */
    @Override
    public WebButton getBtExcluir() {

        return btExcluir;
    }

    /**
     * Retorna um CheckBox para a senha do usuário
     *
     * @return um CheckBox para a senha do usuário
     */
    @Override
    public WebCheckBox getCheckBoxSenha() {

        return checkBoxSenha;
    }

    /**
     * Retorna um ScrollPane do usuário
     *
     * @return um ScrollPane do usuário
     */
    public WebScrollPane getSpUsuario() {

        return spUsuario;
    }

    /**
     * Retorna a linha selecionada na tabela de usuário
     *
     * @return a linha selecionada na tabela de usuário
     */
    public int getLinhaSelecionada() {
        return this.getTabelaUsuario().getSelectedRow();
    }

    /**
     * Retorna uma String de uma tabela de acordo com os parametros linha e
     * coluna
     *
     * @param linha a linha da tabela de usuário
     * @param coluna a coluna da tabela de usuário
     * @return uma String de uma tabela de acordo com os parametros linha e
     * coluna
     */
    public String getTextoEm(int linha, int coluna) {

        ob = this.getTabelaUsuario().getValueAt(linha, coluna);

        return ob.toString();
    }

    /**
     * Retorna um Object contendo os valores de uma linha da tabela de usuário
     *
     * @param linhaSelecionada a linha selecionada na tabela de usuário
     * @return um Object contendo os valores de uma linha da tabela de usuário
     */
    public ArrayList<Object> getValoresEm(int linhaSelecionada) {

        int qtColunas = this.getTabelaUsuario().getColumnCount();
        obj = new ArrayList<>();

        for (int i = 0; i < qtColunas; i++) {
            obj.add(this.getTabelaUsuario().getValueAt(linhaSelecionada, i));
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
        this.getPwSenha().setEnabled(false);
        this.getBtAtualizar().setEnabled(false);
        this.getBtExcluir().setEnabled(false);
        this.getCheckBoxSenha().setSelected(false);
    }

    /**
     * Limpa os dados dos componentes
     */
    public void limpaComponentes() {

        this.getTfNome().setText(null);
        this.getFtfCpf().setText(null);
        this.getFtfDataNascimento().setText(null);
        this.getPwSenha().setText(null);
    }

}
