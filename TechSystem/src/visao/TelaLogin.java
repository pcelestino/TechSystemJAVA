package visao;

import com.alee.extended.image.WebImage;
import com.alee.extended.layout.TableLayout;
import com.alee.extended.panel.CenterPanel;
import com.alee.extended.panel.GroupPanel;
import com.alee.laf.WebLookAndFeel;
import com.alee.laf.button.WebButton;
import com.alee.laf.combobox.WebComboBox;
import com.alee.laf.label.WebLabel;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.rootpane.WebFrame;
import com.alee.laf.text.WebFormattedTextField;
import com.alee.laf.text.WebPasswordField;
import com.alee.laf.text.WebTextField;
import com.alee.managers.hotkey.Hotkey;
import com.alee.managers.hotkey.HotkeyManager;
import com.alee.utils.SwingUtils;
import controle.ControleLoginSistema;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.text.MaskFormatter;

/**
 * Tela para o login no DB e sistema
 *
 * @author Pedro Celestino Silveira Junior
 */
public final class TelaLogin extends WebFrame {

    private static final long serialVersionUID = 1L;

    private WebPanel pnLoginSistema, pnLoginBanco;

    private WebLabel lbLoginSistema, lbSenhaSistema, lbLoginBanco, lbSenhaBanco, lbTipoBanco;
    private WebFormattedTextField ftfLoginSistema;
    private WebTextField tfLoginBanco;
    private WebComboBox cbTipoBanco;
    private WebPasswordField pwSenhaSistema, pwSenhaBanco;
    private WebButton btLogarSistema, btLogarBanco, btLimparSistema, btLimparBanco;
    private CardLayout cardLogin;
    private MaskFormatter formatador;
    private final TableLayout layout;
    private final WebPanel pnLogin;
    private final ControleLoginSistema controleLogin;
    private final WebImage chave1 = new WebImage(new ImageIcon(getClass().getResource("/assets/chave.png"))),
            chave2 = new WebImage(new ImageIcon(getClass().getResource("/assets/chave.png")));

    /**
     * Constrói a tela de login
     */
    public TelaLogin() {

        controleLogin = new ControleLoginSistema(this);
        cardLogin = new CardLayout();
        pnLogin = new WebPanel(cardLogin);

        this.setResizable(false);
        this.setTitle("Banco de Dados");
        this.setIconImages(WebLookAndFeel.getImages());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        layout = new TableLayout(new double[][]{{TableLayout.PREFERRED, TableLayout.FILL},
        {TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED}});

        layout.setHGap(5);
        layout.setVGap(5);

        painelLoginBanco();
        pnLogin.add(pnLoginBanco, "LoginBanco");

        painelLoginSistema();
        pnLogin.add(pnLoginSistema, "LoginSistema");

        cardLogin = (CardLayout) pnLogin.getLayout();
        cardLogin.show(pnLogin, "LoginBanco");

        this.add(pnLogin);

        // Posiciona a janela
        Toolkit thekit = this.getToolkit();
        Dimension dim = thekit.getScreenSize();
        int hor = (dim.width / 2) - 150;
        int ver = (dim.height / 2) - 100;
        this.setBounds(hor, ver, 301, 151);
        this.pack();

        HotkeyManager.registerHotkey(pnLoginBanco, btLogarBanco, Hotkey.ENTER);
        HotkeyManager.registerHotkey(pnLoginBanco, btLimparBanco, Hotkey.CTRL_D);
        HotkeyManager.registerHotkey(pnLoginSistema, btLogarSistema, Hotkey.ENTER);
        HotkeyManager.registerHotkey(pnLoginSistema, btLimparSistema, Hotkey.CTRL_D);
    }

    /**
     * Seta os parâmetros para o login do sistema
     */
    public void painelLoginSistema() {

        lbLoginSistema = new WebLabel("Login", WebLabel.TRAILING);
        try {
            formatador = new MaskFormatter("###.###.###-##");
            formatador.setPlaceholderCharacter('_');
        } catch (ParseException ex) {
            Logger.getLogger(PainelSistema.class.getName()).log(Level.SEVERE, null, ex);
        }
        ftfLoginSistema = new WebFormattedTextField(formatador);

        lbSenhaSistema = new WebLabel("Senha", WebLabel.TRAILING);
        pwSenhaSistema = new WebPasswordField(15);
        pwSenhaSistema.setTrailingComponent(chave1);

        pnLoginSistema = new WebPanel(layout);
        pnLoginSistema.setMargin(15, 30, 15, 30);
        pnLoginSistema.setOpaque(false);

        pnLoginSistema.add(lbLoginSistema, "0,0");
        pnLoginSistema.add(ftfLoginSistema, "1,0");

        pnLoginSistema.add(lbSenhaSistema, "0,1");
        pnLoginSistema.add(pwSenhaSistema, "1,1");

        btLogarSistema = new WebButton("Logar");
        btLimparSistema = new WebButton("Limpar");

        btLogarSistema.addActionListener(controleLogin);
        btLimparSistema.addActionListener(controleLogin);

        pnLoginSistema.add(new CenterPanel(new GroupPanel(5, btLogarSistema, btLimparSistema)), "0,2,1,2");
        SwingUtils.equalizeComponentsWidths(btLogarSistema, btLimparSistema);
    }

    /**
     * Seta os parâmetros para o login do DB
     */
    @SuppressWarnings("unchecked")
    public void painelLoginBanco() {

        lbLoginBanco = new WebLabel("Login", WebLabel.TRAILING);
        tfLoginBanco = new WebTextField(15);

        lbSenhaBanco = new WebLabel("Senha", WebLabel.TRAILING);
        pwSenhaBanco = new WebPasswordField(15);
        pwSenhaBanco.setTrailingComponent(chave2);

        lbTipoBanco = new WebLabel("Tipo", WebLabel.TRAILING);
        cbTipoBanco = new WebComboBox();
        cbTipoBanco.addItem("MySQL");
        cbTipoBanco.addItem("PostgreSQL");

        layout.setHGap(5);
        layout.setVGap(5);

        pnLoginBanco = new WebPanel(layout);
        pnLoginBanco.setMargin(15, 30, 15, 30);
        pnLoginBanco.setOpaque(false);

        pnLoginBanco.add(lbTipoBanco, "0,0");
        pnLoginBanco.add(cbTipoBanco, "1,0");

        pnLoginBanco.add(lbLoginBanco, "0,1");
        pnLoginBanco.add(tfLoginBanco, "1,1");

        pnLoginBanco.add(lbSenhaBanco, "0,2");
        pnLoginBanco.add(pwSenhaBanco, "1,2");

        btLogarBanco = new WebButton("Logar");
        btLimparBanco = new WebButton("Limpar");

        btLogarBanco.setActionCommand("LogarBanco");

        btLogarBanco.addActionListener(controleLogin);
        btLimparBanco.addActionListener(controleLogin);

        // Adiciona os componentes para ficarem centralizados com o layout
        pnLoginBanco.add(new CenterPanel(new GroupPanel(5, btLogarBanco, btLimparBanco)), "0,3,1,3");
        SwingUtils.equalizeComponentsWidths(btLogarBanco, btLimparBanco);
    }

    /**
     * Retorna um TextField para o login do sistema
     *
     * @return um TextField para o login do sistema
     */
    public WebFormattedTextField getFtfLoginSistema() {
        return ftfLoginSistema;
    }

    /**
     * Retorna um TextField para o login do banco
     *
     * @return um TextField para o login do banco
     */
    public WebTextField getTfLoginBanco() {
        return tfLoginBanco;
    }

    /**
     * Retorna um ComboBox para o tipo do banco
     *
     * @return um ComboBox para o tipo do banco
     */
    public WebComboBox getCbTipoBanco() {
        return cbTipoBanco;
    }

    /**
     * Retorna um PasswordField para a senha do sistema
     *
     * @return um PasswordField para a senha do sistema
     */
    public WebPasswordField getPwSenhaSistema() {
        return pwSenhaSistema;
    }

    /**
     * Retorna um PasswordField para a senha do banco
     *
     * @return um PasswordField para a senha do banco
     */
    public WebPasswordField getPwSenhaBanco() {
        return pwSenhaBanco;
    }

    /**
     * Retorna um Button para o login do sistema
     *
     * @return um Button para o login do sistema
     */
    public WebButton getBtLogarSistema() {
        return btLogarSistema;
    }

    /**
     * Retorna um Button para o login do banco
     *
     * @return um Button para o login do banco
     */
    public WebButton getBtLogarBanco() {
        return btLogarBanco;
    }

    /**
     * Retorna um Button para limpar os componentes do login de sistema
     *
     * @return um Button para limpar os componentes do login de sistema
     */
    public WebButton getBtLimparSistema() {
        return btLimparSistema;
    }

    /**
     * Retorna um Button para limpar os componentes do login de banco
     *
     * @return um Button para limpar os componentes do login de banco
     */
    public WebButton getBtLimparBanco() {
        return btLimparBanco;
    }

    /**
     * Retorna um Panel para o login do sistema
     *
     * @return um Panel para o login do sistema
     */
    public WebPanel getPnLoginSistema() {
        return pnLoginSistema;
    }

    /**
     * Retorna um Panel para o login do banco
     *
     * @return um Panel para o login do banco
     */
    public WebPanel getPnLoginBanco() {
        return pnLoginBanco;
    }

    /**
     * Retorna um Panel para o login
     *
     * @return um Panel para o login
     */
    public WebPanel getPnLogin() {
        return pnLogin;
    }

    /**
     * Retorna um CardLayout do login
     *
     * @return um CardLayout do login
     */
    public CardLayout getCardLogin() {
        return cardLogin;
    }

}
