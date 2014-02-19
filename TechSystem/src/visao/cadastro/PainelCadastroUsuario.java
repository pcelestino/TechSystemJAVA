package visao.cadastro;

import com.alee.laf.scroll.WebScrollPane;
import com.alee.laf.table.WebTable;
import com.alee.laf.text.WebFormattedTextField;
import com.alee.laf.text.WebPasswordField;
import com.alee.laf.text.WebTextField;
import controle.evento.BtCadastrarUsuario;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.TitledBorder;
import visao.PainelSistema;

/**
 * Painel para cadastro de usuário
 * @author Pedro Celestino Silveira Junior
 */
public class PainelCadastroUsuario extends PainelSistema {

    private static final long serialVersionUID = 1L;
    private BtCadastrarUsuario acaoBtCadastrarUsuario;
    private GridBagConstraints restricoes;
    private TitledBorder bordaTitulo;
    private WebTextField tfNome;
    private WebFormattedTextField ftfCpf, ftfDataNascimento;
    private WebPasswordField pwSenha;
    private WebScrollPane spUsuario;
    private WebTable tabelaUsuario;

    /**
     * Carrega os componentes do painel
     */
    public void carregarComponentes() {

        acaoBtCadastrarUsuario = new BtCadastrarUsuario();
        restricoes = new GridBagConstraints();
        tabelaUsuario = super.gerarTabelaUsuario();
        tfNome = super.getTfNome();
        ftfCpf = super.getFtfCpf();
        ftfDataNascimento = super.getFtfDataNascimento();
        pwSenha = super.getPwSenha();
        spUsuario = super.getSpPessoas();

        // Edição do título do painel
        bordaTitulo = new TitledBorder("Cadastro de Usuários");
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
        this.add(super.getLbSistema("Senha: "), restricoes);

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
        this.add(super.getBtCadastrar(), restricoes);

        restricoes.gridy = 0;
        restricoes.gridx = 3;
        restricoes.weightx = 1;
        restricoes.gridheight = GridBagConstraints.REMAINDER;
        restricoes.fill = GridBagConstraints.BOTH;
        this.add(spUsuario, restricoes);

        super.setBtCadastrar("btCadastrarUsuario", acaoBtCadastrarUsuario);
        
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
     * @return uma tabela de usuário
     */
    public WebTable getTabelaUsuario() {
        return tabelaUsuario;
    }

    /**
     * Retorna um TextField para o nome do usuário
     * @return um TextField para o nome do usuário
     */
    @Override
    public WebTextField getTfNome() {
        return tfNome;
    }

    /**
     * Retorna um TextField para o cpf do usuário
     * @return um TextField para o cpf do usuário
     */
    @Override
    public WebFormattedTextField getFtfCpf() {
        return ftfCpf;
    }

    /**
     * Retorna um TextField para a data de nascimento do usuário
     * @return um TextField para a data de nascimento do usuário
     */
    @Override
    public WebFormattedTextField getFtfDataNascimento() {
        return ftfDataNascimento;
    }

    /**
     * Retorna um PasswordField para a senha do usuário
     * @return um PasswordField para a senha do usuário
     */
    @Override
    public WebPasswordField getPwSenha() {
        return pwSenha;
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
