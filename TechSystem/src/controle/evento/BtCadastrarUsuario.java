package controle.evento;

import static controle.ControleLoginSistema.telaPrincipal;
import controle.validacao.CpfInvalidoException;
import controle.validacao.DataInvalidaException;
import controle.validacao.NomeInvalidoException;
import controle.validacao.SenhaInvalidaException;
import controle.validacao.Validador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.SistemaDAO;
import modelo.Usuario;
import visao.cadastro.PainelCadastroUsuario;

/**
 * Acoes do painel para cadastro de usuário
 * @author Pedro Celestino Silveira Junior
 */
public class BtCadastrarUsuario implements ActionListener {

    private Usuario usuario;
    private Validador valida;
    private String nome, cpf, dataNascimento;
    private char[] senha;
    private SistemaDAO sistemaDAO;
    private PainelCadastroUsuario painelCadastroUsuario;
    
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            sistemaDAO = new SistemaDAO();
            painelCadastroUsuario = telaPrincipal.getPainelCards().getPainelCadastroUsuario();
          
            // Instancia o objeto Validador para validar os dados
            valida = new Validador();

            // Recupera os dados digitados no painel de cadastro
            nome = painelCadastroUsuario.getTfNome().getText();
            cpf = painelCadastroUsuario.getFtfCpf().getText();
            dataNascimento = painelCadastroUsuario.getFtfDataNascimento().getText();
            senha = painelCadastroUsuario.getPwSenha().getPassword();

            // Remove caracteres não numéricos
            cpf = cpf.replaceAll("\\D", "");

            // Método para validar os dados
            valida.validarNome(nome);
            valida.validarCPF(cpf, "usuario");
            valida.validarDataNascimento(dataNascimento);
            valida.validarSenha(senha);

            // Adiciona os dados ao objeto usuário
            usuario = new Usuario(nome, cpf, senha);
            usuario.setDataNascimento(dataNascimento);

            // Cadastra o usuário no Banco de Dados
            sistemaDAO.cadastrarUsuario(usuario);

            // Atualiza a tabela de vendas
            painelCadastroUsuario.fireTabelaUsuario();

            // Limpa os componentes
            painelCadastroUsuario.limpaComponentes();

        } catch (NomeInvalidoException | CpfInvalidoException | DataInvalidaException ex) {

            JOptionPane.showMessageDialog(null, ex.getMessage(), "Atenção", JOptionPane.WARNING_MESSAGE);

        } catch (SenhaInvalidaException ex) {

            JOptionPane.showMessageDialog(null, ex.getMessage() + "\n[Deve possuir no mínimo 4 caracteres]", "Atenção", JOptionPane.WARNING_MESSAGE);

        } catch (NullPointerException | NumberFormatException ex) {

            JOptionPane.showMessageDialog(null, "Apenas a data de nascimento é opcional", "Atenção", JOptionPane.WARNING_MESSAGE);
        }
    }

}
