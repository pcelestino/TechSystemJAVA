package visao;

import com.alee.laf.button.WebButton;
import com.alee.laf.menu.WebMenu;
import com.alee.laf.menu.WebMenuBar;
import com.alee.laf.menu.WebMenuItem;
import static controle.ControleLoginSistema.telaPrincipal;
import controle.evento.BtDeslogar;
import controle.evento.MenuCadastrarCliente;
import controle.evento.MenuCadastrarFuncionario;
import controle.evento.MenuCadastrarUsuario;
import controle.evento.MenuCadastrarVenda;
import controle.evento.MenuEditarCliente;
import controle.evento.MenuEditarFuncionario;
import controle.evento.MenuEditarUsuario;
import controle.evento.MenuEditarVenda;
import controle.evento.MenuVisualizarCadastros;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;

/**
 * Painel do menu
 *
 * @author Pedro Celestino Silveira Junior
 */
public class PainelMenuBar extends PainelSistema {

    private static final long serialVersionUID = 1L;

    private final BtDeslogar acaoBtDeslogar;
    private final MenuCadastrarCliente acaoMenuCadastrarCliente;
    private final MenuCadastrarFuncionario acaoMenuCadastrarFuncionario;
    private final MenuCadastrarUsuario acaoMenuCadastrarUsuario;
    private final MenuCadastrarVenda acaoMenuCadastrarVenda;
    private final MenuEditarCliente acaoMenuEditarCliente;
    private final MenuEditarFuncionario acaoMenuEditarFuncionario;
    private final MenuEditarUsuario acaoMenuEditarUsuario;
    private final MenuEditarVenda acaoMenuEditarVenda;
    private final MenuVisualizarCadastros acaoMenuVisualizarCadastros;

    private final GridBagConstraints restricoes;
    private final WebButton btDeslogar;
    private final WebMenuBar menuBar;
    private final WebMenu menuAcoes, menuCadastrar, menuEditar;
    private final WebMenuItem cadCliente, cadFuncionario, cadUsuario, cadVenda,
            edCliente, edFuncionario, edUsuario, edVenda, viCadastros;

    /**
     * Constrói um menu
     */
    public PainelMenuBar() {

        // Action Listeners
        acaoBtDeslogar = new BtDeslogar();
        acaoMenuCadastrarCliente = new MenuCadastrarCliente(this);
        acaoMenuCadastrarFuncionario = new MenuCadastrarFuncionario(this);
        acaoMenuCadastrarUsuario = new MenuCadastrarUsuario(this);
        acaoMenuCadastrarVenda = new MenuCadastrarVenda(this);
        acaoMenuEditarCliente = new MenuEditarCliente(this);
        acaoMenuEditarFuncionario = new MenuEditarFuncionario(this);
        acaoMenuEditarUsuario = new MenuEditarUsuario(this);
        acaoMenuEditarVenda = new MenuEditarVenda(this);
        acaoMenuVisualizarCadastros = new MenuVisualizarCadastros(this);

        menuBar = new WebMenuBar();
        menuBar.setLayout(new GridBagLayout());
        menuAcoes = new WebMenu("Ações");
        menuBar.setUndecorated(true);
        menuBar.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
        restricoes = new GridBagConstraints();

        btDeslogar = new WebButton("Deslogar");
        btDeslogar.setFocusable(false);

        menuCadastrar = new WebMenu("Cadastrar");

        cadVenda = new WebMenuItem("Venda");
        cadUsuario = new WebMenuItem("Usuario");
        cadCliente = new WebMenuItem("Cliente");
        cadFuncionario = new WebMenuItem("Funcionario");

        menuCadastrar.add(cadVenda);
        menuCadastrar.addSeparator();
        menuCadastrar.add(cadUsuario);
        menuCadastrar.add(cadCliente);
        menuCadastrar.add(cadFuncionario);

        menuEditar = new WebMenu("Editar");

        edVenda = new WebMenuItem("Venda");
        edUsuario = new WebMenuItem("Usuario");
        edCliente = new WebMenuItem("Cliente");
        edFuncionario = new WebMenuItem("Funcionario");

        menuEditar.add(edVenda);
        menuEditar.addSeparator();
        menuEditar.add(edUsuario);
        menuEditar.add(edCliente);
        menuEditar.add(edFuncionario);

        viCadastros = new WebMenuItem("Visualizar Cadastros");

        menuAcoes.add(menuCadastrar);
        menuAcoes.add(menuEditar);
        menuAcoes.addSeparator();
        menuAcoes.add(viCadastros);

        restricoes.weightx = 1;
        restricoes.anchor = GridBagConstraints.LINE_START;
        menuBar.add(menuAcoes, restricoes);

        restricoes.anchor = GridBagConstraints.LINE_END;
        menuBar.add(btDeslogar, restricoes);

        btDeslogar.addActionListener(acaoBtDeslogar);
        cadVenda.addActionListener(acaoMenuCadastrarVenda);
        cadUsuario.addActionListener(acaoMenuCadastrarUsuario);
        cadCliente.addActionListener(acaoMenuCadastrarCliente);
        cadFuncionario.addActionListener(acaoMenuCadastrarFuncionario);
        edVenda.addActionListener(acaoMenuEditarVenda);
        edUsuario.addActionListener(acaoMenuEditarUsuario);
        edCliente.addActionListener(acaoMenuEditarCliente);
        edFuncionario.addActionListener(acaoMenuEditarFuncionario);
        viCadastros.addActionListener(acaoMenuVisualizarCadastros);
    }

    /**
     * Retorna um MenuBar
     *
     * @return um MenuBar
     */
    public WebMenuBar getMenuBar() {
        return menuBar;
    }

    /**
     * Remove todos os componentes
     */
    public void removerComponentes() {

        telaPrincipal.getPainelCards().getPainelCadastroCliente().removeAll();
        telaPrincipal.getPainelCards().getPainelCadastroFuncionario().removeAll();
        telaPrincipal.getPainelCards().getPainelCadastroUsuario().removeAll();
        telaPrincipal.getPainelCards().getPainelCadastroVenda().removeAll();

        telaPrincipal.getPainelCards().getPainelEdicaoCliente().removeAll();
        telaPrincipal.getPainelCards().getPainelEdicaoFuncionario().removeAll();
        telaPrincipal.getPainelCards().getPainelEdicaoUsuario().removeAll();
        telaPrincipal.getPainelCards().getPainelEdicaoVenda().removeAll();

        telaPrincipal.getPainelCards().getPainelViCadastros().removeAll();
    }

}
