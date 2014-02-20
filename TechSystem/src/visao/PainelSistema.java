package visao;

import com.alee.extended.image.WebImage;
import com.alee.laf.button.WebButton;
import com.alee.laf.checkbox.WebCheckBox;
import com.alee.laf.combobox.WebComboBox;
import com.alee.laf.label.WebLabel;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.scroll.WebScrollPane;
import com.alee.laf.table.WebTable;
import com.alee.laf.text.WebFormattedTextField;
import com.alee.laf.text.WebPasswordField;
import com.alee.laf.text.WebTextField;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.text.MaskFormatter;
import modelo.SistemaDAO;

/**
 * Painel gera que contém os principais componentes
 *
 * @author Pedro Celestino Silveira Junior
 */
public class PainelSistema extends WebPanel {

    private static final long serialVersionUID = 1L;
    private String cpf;
    private WebTextField tfNome, tfNomeVenda;
    private WebFormattedTextField ftfCpf, tfSalario, tfValorVenda, ftfDataNascimesnto;
    private WebPasswordField pwSenha;
    private WebComboBox cbTipo, cbCpfCliente, cbCpfFuncionario;
    private WebCheckBox checkBoxSenha;
    private WebButton btCadastrar, btAtualizar, btExcluir;
    private WebScrollPane spPessoas;
    private WebLabel lbSistema;
    private MaskFormatter formatador;
    private DecimalFormat decimalFormat;
    private WebTable tbPessoas;
    private ArrayList<String> consulta;
    private final SistemaDAO sistemaDao;
    private final WebImage chave = new WebImage(new ImageIcon(getClass().getResource("/assets/chave.png")));

    /**
     * Constrói o painel do sistema
     */
    public PainelSistema() {
        this.setLayout(new GridBagLayout());
        sistemaDao = new SistemaDAO();
    }

    /**
     * Retorna uma tabela de cliente
     *
     * @return uma tabela de cliente
     */
    public WebTable gerarTabelaCliente() {

        tbPessoas = this.getTbPessoas();
        tbPessoas.setModel(sistemaDao.gerarTabelaCliente());
        return tbPessoas;
    }

    /**
     * Retorna uma tabela de funcionário
     *
     * @return uma tabela de funcionário
     */
    public WebTable gerarTabelaFuncionario() {

        tbPessoas = this.getTbPessoas();
        tbPessoas.setModel(sistemaDao.gerarTabelaFuncionario());
        return tbPessoas;
    }

    /**
     * Retorna uma tabela de usuário
     *
     * @return uma tabela de usuário
     */
    public WebTable gerarTabelaUsuario() {

        tbPessoas = this.getTbPessoas();
        tbPessoas.setModel(sistemaDao.gerarTabelaUsuario());
        return tbPessoas;
    }

    /**
     * Retorna uma tabela de venda
     *
     * @return uma tabela de venda
     */
    public WebTable gerarTabelaVenda() {

        tbPessoas = this.getTbPessoas();
        tbPessoas.setModel(sistemaDao.gerarTabelaVenda());

        // Seleciona a coluna para modificar o tamanho
        TableColumn column = tbPessoas.getColumnModel().getColumn(0);
        column.setPreferredWidth(10);
        column = tbPessoas.getColumnModel().getColumn(4);
        column.setPreferredWidth(80);

        return tbPessoas;
    }

    /**
     * Retorna uma tabela atualizada de cliente
     *
     * @return uma tabela atualizada de cliente
     */
    public WebTable atualizarTabelaCliente() {

        tbPessoas.setModel(sistemaDao.gerarTabelaCliente());
        return tbPessoas;
    }

    /**
     * Retorna uma tabela atualizada de fucionário
     *
     * @return uma tabela atualizada de fucionário
     */
    public WebTable atualizarTabelaFuncionario() {

        tbPessoas.setModel(sistemaDao.gerarTabelaFuncionario());
        return tbPessoas;
    }

    /**
     * Retorna uma tabela atualizada de usuário
     *
     * @return uma tabela atualizada de usuário
     */
    public WebTable atualizarTabelaUsuario() {

        tbPessoas.setModel(sistemaDao.gerarTabelaUsuario());
        return tbPessoas;
    }

    /**
     * Retorna uma tabela atualizada de vendas
     *
     * @return uma tabela atualizada de vendas
     */
    public WebTable atualizarTabelaVenda() {

        tbPessoas.setModel(sistemaDao.gerarTabelaVenda());

        return tbPessoas;
    }

    /**
     * Retorna um Label genérico
     *
     * @param nome o nome do Label
     * @return um Label genérico
     */
    public WebLabel getLbSistema(String nome) {

        // Label Nome
        lbSistema = new WebLabel(nome);
        lbSistema.setBoldFont();
        lbSistema.setFontSize(15);

        return lbSistema;
    }

    /**
     * Retorna um TextField para nome
     *
     * @return um TextField para nome
     */
    public WebTextField getTfNome() {

        // TextField Nome
        tfNome = new WebTextField();
        tfNome.setPreferredSize(new Dimension(200, 35));
        tfNome.setRound(5);
        tfNome.setFontSize(16);

        return tfNome;
    }

    /**
     * Retorna um TextField para nome da venda
     *
     * @return um TextField para nome da venda
     */
    public WebTextField getTfNomeVenda() {

        // TextField Venda
        tfNomeVenda = new WebTextField();
        tfNomeVenda.setPreferredSize(new Dimension(200, 35));
        tfNomeVenda.setRound(5);
        tfNomeVenda.setFontSize(16);

        return tfNomeVenda;
    }

    /**
     * Retorna um TextField para valor da venda
     *
     * @return um TextField para valor da venda
     */
    public WebFormattedTextField getFtfValorVenda() {

        // FormatedTextField Valor Venda
        decimalFormat = new DecimalFormat("0.00");
        tfValorVenda = new WebFormattedTextField(decimalFormat);
        tfValorVenda.setPreferredSize(new Dimension(200, 35));
        tfValorVenda.setRound(5);
        tfValorVenda.setFontSize(16);
        tfValorVenda.setText(null);

        return tfValorVenda;
    }

    /**
     * Retorna um TextField para salário
     *
     * @return um TextField para salário
     */
    public WebFormattedTextField getFtfSalario() {

        // FormatedTextField Salario
        decimalFormat = new DecimalFormat("0.00");
        tfSalario = new WebFormattedTextField(decimalFormat);
        tfSalario.setPreferredSize(new Dimension(200, 35));
        tfSalario.setRound(5);
        tfSalario.setFontSize(16);

        return tfSalario;
    }

    /**
     * Retorna um TextField para cpf
     *
     * @return um TextField para cpf
     */
    public WebFormattedTextField getFtfCpf() {

        // FormatedTextField CPF
        try {
            formatador = new MaskFormatter("###.###.###-##");
            formatador.setPlaceholderCharacter('_');
        } catch (ParseException ex) {
            Logger.getLogger(PainelSistema.class.getName()).log(Level.SEVERE, null, ex);
        }
        ftfCpf = new WebFormattedTextField(formatador);
        ftfCpf.setPreferredSize(new Dimension(200, 35));
        ftfCpf.setRound(5);
        ftfCpf.setFontSize(16);

        return ftfCpf;
    }

    /**
     * Retorna um TextField para data de nascimento
     *
     * @return um TextField para data de nascimento
     */
    public WebFormattedTextField getFtfDataNascimento() {

        // DateField Data Nascimento
        try {
            formatador = new MaskFormatter("## / ## / ####");
            formatador.setPlaceholderCharacter('_');
        } catch (ParseException ex) {
            Logger.getLogger(PainelSistema.class.getName()).log(Level.SEVERE, null, ex);
        }
        ftfDataNascimesnto = new WebFormattedTextField(formatador);
        ftfDataNascimesnto.setPreferredSize(new Dimension(200, 35));
        ftfDataNascimesnto.setRound(5);
        ftfDataNascimesnto.setFontSize(16);

        return ftfDataNascimesnto;
    }

    /**
     * Retorna um PasswordFild para senha
     *
     * @return um PasswordFild para senha
     */
    public WebPasswordField getPwSenha() {

        // PassWordField Senha
        pwSenha = new WebPasswordField();
        pwSenha.setPreferredSize(new Dimension(200, 35));
        pwSenha.setRound(5);
        pwSenha.setFontSize(16);
        pwSenha.setTrailingComponent(chave);

        return pwSenha;
    }

    /**
     * Retorna um ComboBox para tipo
     *
     * @return um ComboBox para tipo
     */
    @SuppressWarnings("unchecked")
    public WebComboBox getCbTipo() {

        // ComboBox Tipo
        cbTipo = new WebComboBox();
        cbTipo.setPreferredSize(new Dimension(200, 35));
        cbTipo.setRound(5);
        cbTipo.setFontSize(15);
        cbTipo.addItem("Normal");
        cbTipo.addItem("Avançado");
        cbTipo.addItem("Especial");
        cbTipo.setSelectedItem(null);

        return cbTipo;
    }

    /**
     * Retorna um ComboBox para cpf do cliente
     *
     * @return um ComboBox para cpf do cliente
     */
    @SuppressWarnings("unchecked")
    public WebComboBox getCbCpfCliente() {

        try {
            formatador = new MaskFormatter("## / ## / ####");
            formatador.setPlaceholderCharacter('_');
        } catch (ParseException ex) {
            Logger.getLogger(PainelSistema.class.getName()).log(Level.SEVERE, null, ex);
        }

        // ComboBox Cliente
        cbCpfCliente = new WebComboBox();
        cbCpfCliente.setPreferredSize(new Dimension(200, 35));
        cbCpfCliente.setRound(5);
        cbCpfCliente.setFontSize(15);

        consulta = sistemaDao.getColunaSQL("SELECT cpf_cliente FROM clientes", "cpf_cliente");

        for (int i = 0; i < consulta.size(); i++) {

            cpf = consulta.get(i);

            cpf = cpf.substring(0, 3) + "."
                    + cpf.substring(3, 6) + "."
                    + cpf.substring(6, 9) + "-"
                    + cpf.substring(9, 11);

            cbCpfCliente.addItem(cpf);
        }

        cbCpfCliente.setSelectedItem(null);

        return cbCpfCliente;
    }

    /**
     * Retorna um ComboBox para cpf do funcionário
     *
     * @return um ComboBox para cpf do funcionário
     */
    @SuppressWarnings("unchecked")
    public WebComboBox getCbCpfFuncionario() {

        // ComboBox Funcionario
        cbCpfFuncionario = new WebComboBox();
        cbCpfFuncionario.setPreferredSize(new Dimension(200, 35));
        cbCpfFuncionario.setRound(5);
        cbCpfFuncionario.setFontSize(15);

        consulta = sistemaDao.getColunaSQL("SELECT cpf_funcionario FROM funcionarios", "cpf_funcionario");

        for (int i = 0; i < consulta.size(); i++) {

            cpf = consulta.get(i);

            cpf = cpf.substring(0, 3) + "."
                    + cpf.substring(3, 6) + "."
                    + cpf.substring(6, 9) + "-"
                    + cpf.substring(9, 11);

            cbCpfFuncionario.addItem(cpf);
        }
        cbCpfFuncionario.setSelectedItem(null);

        return cbCpfFuncionario;
    }

    /**
     * Retorna um CheckBox para senha
     *
     * @return um CheckBox para senha
     */
    public WebCheckBox getCheckBoxSenha() {

        checkBoxSenha = new WebCheckBox("Senha: ", false);
        checkBoxSenha.setHorizontalTextPosition(WebCheckBox.LEFT);
        checkBoxSenha.setBoldFont();
        checkBoxSenha.setFontSize(15);

        return checkBoxSenha;
    }

    /**
     * Retorna um Button para cadastro
     *
     * @return um Button para cadastro
     */
    public WebButton getBtCadastrar() {

        // Button Cadastrar
        btCadastrar = new WebButton("Cadastrar");
        btCadastrar.setPreferredSize(new Dimension(200, 35));
        btCadastrar.setBoldFont();
        btCadastrar.setRound(5);
        btCadastrar.setFontSize(15);

        return btCadastrar;
    }

    /**
     * Retorna um Button para atualização
     *
     * @return um Button para atualização
     */
    public WebButton getBtAtualizar() {

        // Button Editar
        btAtualizar = new WebButton("Atualizar");
        btAtualizar.setPreferredSize(new Dimension(200, 35));
        btAtualizar.setBoldFont();
        btAtualizar.setRound(5);
        btAtualizar.setFontSize(15);

        return btAtualizar;
    }

    /**
     * Retorna um Button para exclusão
     *
     * @return um Button para exclusão
     */
    public WebButton getBtExcluir() {

        // Button Editar
        btExcluir = new WebButton("Excluir");
        btExcluir.setPreferredSize(new Dimension(95, 35));
        btExcluir.setBoldFont();
        btExcluir.setRound(5);
        btExcluir.setFontSize(15);

        return btExcluir;
    }

    /**
     * Retorna um Table para pessoas
     *
     * @return um Table para pessoas
     */
    public WebTable getTbPessoas() {

        tbPessoas = new WebTable();
        tbPessoas.setRowSelectionAllowed(false);
        tbPessoas.setRowSelectionAllowed(true);
        tbPessoas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbPessoas.setRowHeight(30);
        tbPessoas.setFontSize(14);

        // Centralizar a Tabela
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(WebLabel.CENTER);
        tbPessoas.setDefaultRenderer(Object.class, centerRenderer);
        tbPessoas.setFocusable(false);

        return tbPessoas;
    }

    /**
     * Retorna um ScrollPane para pessoas
     *
     * @return um ScrollPane para pessoas
     */
    public WebScrollPane getSpPessoas() {

        // ScrollPane Pessoas
        spPessoas = new WebScrollPane(tbPessoas);
        spPessoas.setPreferredSize(new Dimension(300, 300));

        return spPessoas;
    }

    /**
     * Seta uma ação para o botão de cadastro
     *
     * @param Acao a ação
     * @param Listener para onde será enviada a ação
     */
    public void setBtCadastrar(String Acao, ActionListener Listener) {
        this.btCadastrar.setActionCommand(Acao);
        this.btCadastrar.addActionListener(Listener);
    }

    /**
     * Seta uma ação para o botão de atualização
     *
     * @param Acao a ação
     * @param Listener para onde será enviada a ação
     */
    public void setBtAtualizar(String Acao, ActionListener Listener) {
        this.btAtualizar.setActionCommand(Acao);
        this.btAtualizar.addActionListener(Listener);
    }

    /**
     * Seta uma ação para o botão de exclusão
     *
     * @param Acao a ação
     * @param Listener para onde será enviada a ação
     */
    public void setBtExcluir(String Acao, ActionListener Listener) {
        this.btExcluir.setActionCommand(Acao);
        this.btExcluir.addActionListener(Listener);
    }

    /**
     * Retorna um ComboBox atualizado para cpf do cliente
     *
     * @return um ComboBox atualizado para cpf do cliente
     */
    @SuppressWarnings("unchecked")
    public WebComboBox atualizaCbCpfCliente() {

        // ComboBox Cliente
        cbCpfCliente.removeAllItems();

        consulta = sistemaDao.getColunaSQL("SELECT cpf_cliente FROM clientes", "cpf_cliente");

        for (int i = 0; i < consulta.size(); i++) {

            cpf = consulta.get(i);

            cpf = cpf.substring(0, 3) + "."
                    + cpf.substring(3, 6) + "."
                    + cpf.substring(6, 9) + "-"
                    + cpf.substring(9, 11);

            cbCpfCliente.addItem(cpf);
        }

        cbCpfCliente.setSelectedItem(null);

        return cbCpfCliente;
    }

    /**
     * Retorna um ComboBox atualizado para cpf do funcionário
     *
     * @return um ComboBox atualizado para cpf do funcionário
     */
    @SuppressWarnings("unchecked")
    public WebComboBox atualizaCbCpfFuncionario() {

        // ComboBox Funcionario
        cbCpfFuncionario.removeAllItems();

        consulta = sistemaDao.getColunaSQL("SELECT cpf_funcionario FROM funcionarios", "cpf_funcionario");

        for (int i = 0; i < consulta.size(); i++) {

            cpf = consulta.get(i);

            cpf = cpf.substring(0, 3) + "."
                    + cpf.substring(3, 6) + "."
                    + cpf.substring(6, 9) + "-"
                    + cpf.substring(9, 11);

            cbCpfFuncionario.addItem(cpf);
        }
        cbCpfFuncionario.setSelectedItem(null);

        return cbCpfFuncionario;
    }

}
