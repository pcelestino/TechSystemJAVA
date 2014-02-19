package controle;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import visao.TelaLogin;
import visao.TelaPrincipal;

/**
 *
 * @author Pedro Celestino Silveira Junior
 */
public class ControleLoginSistema implements ActionListener {

    private final TelaLogin telaLogin;

    /**
     *
     */
    public static TelaPrincipal telaPrincipal;
    private ConectaBanco bancoDados;
    int tipoBanco;
    char[] senha;
    String loginBanco, senhaBanco, loginSistema, senhaSistema;

    /**
     *
     * @param telaLogin
     */
    public ControleLoginSistema(TelaLogin telaLogin) {
        this.telaLogin = telaLogin;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == telaLogin.getBtLogarBanco()) {
            bancoDados = new ConectaBanco();

            tipoBanco = telaLogin.getCbTipoBanco().getSelectedIndex();

            switch (tipoBanco) {
                case 0:

                    ConectaBanco.setDbTipo(telaLogin.getCbTipoBanco().getSelectedItem().toString());
                    ConectaBanco.setDriver("com.mysql.jdbc.Driver");
                    ConectaBanco.setCaminho("jdbc:mysql://localhost:3306/mysql");

                    break;

                case 1:

                    ConectaBanco.setDbTipo(telaLogin.getCbTipoBanco().getSelectedItem().toString());
                    ConectaBanco.setDriver("org.postgresql.Driver");
                    ConectaBanco.setCaminho("jdbc:postgresql://localhost:5432/postgres");
            }

            loginBanco = telaLogin.getTfLoginBanco().getText();
            senha = telaLogin.getPwSenhaBanco().getPassword();

            senhaBanco = "";
            for (int i = 0; i < senha.length; i++) {
                senhaBanco += senha[i];
            }

            ConectaBanco.setUsuario(loginBanco);
            ConectaBanco.setSenha(senhaBanco);

            if (bancoDados.testaConexao()) {

                bancoDados.criarDatabase();
                bancoDados.criarTabelas();

                telaLogin.getPnLogin().setPreferredSize(new Dimension(275, 110));
                telaLogin.setTitle("Sistema");
                telaLogin.getCardLogin().show(telaLogin.getPnLogin(), "LoginSistema");
                telaLogin.pack();
            }
        }
        if (e.getSource() == telaLogin.getBtLimparBanco()) {
            telaLogin.getTfLoginBanco().setText(null);
            telaLogin.getPwSenhaBanco().setText(null);
            telaLogin.getCbTipoBanco().setSelectedIndex(0);
        }
        if (e.getSource() == telaLogin.getBtLogarSistema()) {

            try {
                bancoDados = new ConectaBanco();

                bancoDados.conectar();

                loginSistema = telaLogin.getFtfLoginSistema().getText();
                senha = telaLogin.getPwSenhaSistema().getPassword();

                loginSistema = loginSistema.replaceAll("\\D", "");

                senhaSistema = "";
                for (int i = 0; i < senha.length; i++) {
                    senhaSistema += senha[i];
                }

                bancoDados.executaPesquisaSQL(
                        "SELECT * FROM login WHERE usuario='" + loginSistema + "' and senha=MD5('" + senhaSistema + "')");

                if (bancoDados.getResultado().next()) {

                    telaLogin.dispose();
                    telaPrincipal = new TelaPrincipal();
                    telaPrincipal.getPainelCards().getPainelCadastroVenda().carregarComponentes();
                    telaPrincipal.setVisible(true);

                } else {

                    JOptionPane.showMessageDialog(null, "Login ou senha incorretos!");
                }
                bancoDados.desconectar();

            } catch (SQLException ex) {

                JOptionPane.showMessageDialog(null, "Erro de conexão!\nERRO: " + ex);
            }
        }

        if (e.getSource() == telaLogin.getBtLimparSistema()) {
            telaLogin.getFtfLoginSistema().setText(null);
            telaLogin.getPwSenhaSistema().setText(null);
        }
    }

}
