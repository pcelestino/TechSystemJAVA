package controle.evento;

import com.alee.laf.text.WebPasswordField;
import static controle.ControleLoginSistema.telaPrincipal;
import controle.excecao.CpfInvalidoException;
import controle.excecao.DataInvalidaException;
import controle.excecao.NomeInvalidoException;
import controle.excecao.SenhaInvalidaException;
import controle.validacao.Validador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.SistemaDAO;
import modelo.Usuario;
import visao.edicao.PainelEdicaoUsuario;

/**
 * Acoes do painel para edição de usuário
 *
 * @author Pedro Celestino Silveira Junior
 */
public class BtAtualizarUsuario implements ActionListener {

    private Usuario usuario;
    private Validador valida;
    private String nome, cpf, cpfSelecionado, dataNascimento, loginUsuario, senhaUsuario;
    private SistemaDAO sistemaDAO;
    private PainelEdicaoUsuario painelEdicaoUsuario;
    private WebPasswordField senhaChecagem;
    private Object[] ob;
    private ArrayList<Object> obj;
    private char[] senha;
    private int linha;

    @Override
    public void actionPerformed(ActionEvent e) {

        ob = new Object[2];
        sistemaDAO = new SistemaDAO();
        painelEdicaoUsuario = telaPrincipal.getPainelCards().getPainelEdicaoUsuario();

        // Declaração dos valores para a tela de checagem
        senhaChecagem = new WebPasswordField();
        ob[0] = "Digite a senha antiga:\n\n";
        ob[1] = senhaChecagem;
        Object stringArray[] = {"OK", "Cancel"};

        // Cria uma tela para verificar a senha antiga do usuário
        if (JOptionPane.showOptionDialog(null, ob, "Mensagem",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, stringArray, ob) == JOptionPane.YES_OPTION) {

            // Captura a linha selecionada na tabela do formulário
            linha = painelEdicaoUsuario.getLinhaSelecionada();

            // Obtém todos os valores da linha selecionada na tabela
            obj = painelEdicaoUsuario.getValoresEm(linha);

            // Recupera os valores passados para a variável obj
            loginUsuario = obj.get(1).toString();
            loginUsuario = loginUsuario.replaceAll("\\D", "");

            // Transforma char[] em String
            senha = senhaChecagem.getPassword();
            senhaUsuario = "";
            for (int i = 0; i < senha.length; i++) {
                senhaUsuario += senha[i];
            }

            try {
                // Verifica se a senha é igual a senha cadastrada
                sistemaDAO.checarSenha(loginUsuario, senhaUsuario);

                // Instância o objeto Validador para validar os dados
                valida = new Validador();

                // Captura a linha selecionada na tabela do formulário
                linha = painelEdicaoUsuario.getLinhaSelecionada();

                // Seleciona o cpf da tabela do formulário
                cpfSelecionado = painelEdicaoUsuario.getTextoEm(linha, 1);

                // Recupera os dados digitados no painel de edição
                nome = painelEdicaoUsuario.getTfNome().getText();
                cpf = painelEdicaoUsuario.getFtfCpf().getText();
                dataNascimento = painelEdicaoUsuario.getFtfDataNascimento().getText();

                if (painelEdicaoUsuario.getCheckBoxSenha().isSelected()) {
                    senha = painelEdicaoUsuario.getPwSenha().getPassword();
                }

                // Remove caracteres não numéricos
                cpf = cpf.replaceAll("\\D", "");

                // Método para validar os dados
                valida.validarNome(nome);
                valida.validarCPF(cpf, cpfSelecionado, "usuario");
                valida.validarDataNascimento(dataNascimento);

                // Adiciona os dados ao objeto usuário
                usuario = new Usuario(nome, cpf, senha);
                usuario.setDataNascimento(dataNascimento);

                // Cadastra o usuário no Banco de Dados
                sistemaDAO.atualizarUsuario(usuario, loginUsuario);

                // Atualiza a tabela de Usuários
                painelEdicaoUsuario.fireTabelaUsuario();

                // Desabilita os componentes
                painelEdicaoUsuario.desabilitaComponentes();

                // Limpa os componentes
                painelEdicaoUsuario.limpaComponentes();

            } catch (NomeInvalidoException | CpfInvalidoException | DataInvalidaException | SenhaInvalidaException ex) {

                JOptionPane.showMessageDialog(null, ex.getMessage(), "Atenção", JOptionPane.WARNING_MESSAGE);

            } catch (NullPointerException | NumberFormatException ex) {

                JOptionPane.showMessageDialog(null, "Apenas a data de nascimento é opcional", "Atenção", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

}
