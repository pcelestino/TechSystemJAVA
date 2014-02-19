package controle.evento;

import static controle.ControleLoginSistema.telaPrincipal;
import controle.validacao.CpfInvalidoException;
import controle.validacao.DataInvalidaException;
import controle.validacao.NomeInvalidoException;
import controle.validacao.Validador;
import controle.validacao.ValorInvalidoException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.Funcionario;
import modelo.SistemaDAO;
import visao.cadastro.PainelCadastroFuncionario;

/**
 * Acoes do painel para cadastro de funcionário
 * @author Pedro Celestino Silveira Junior
 */
public class BtCadastrarFuncionario implements ActionListener {

    private Funcionario funcionario;
    private Validador valida;
    private String nome, cpf, dataNascimento, salario;
    private SistemaDAO sistemaDAO;
    private PainelCadastroFuncionario painelCadastroFuncionario;

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            sistemaDAO = new SistemaDAO();
            painelCadastroFuncionario = telaPrincipal.getPainelCards().getPainelCadastroFuncionario();
        
            // Instancia o objeto Validador para validar os dados
            valida = new Validador();

            // Recupera os dados digitados no painel de cadastro
            nome = painelCadastroFuncionario.getTfNome().getText();
            cpf = painelCadastroFuncionario.getFtfCpf().getText();
            dataNascimento = painelCadastroFuncionario.getFtfDataNascimento().getText();
            salario = painelCadastroFuncionario.getFtfSalario().getText();

            // Método necessário para ser compatível com double
            salario = salario.replaceAll(",", ".");

            // Remove caracteres não numéricos
            cpf = cpf.replaceAll("\\D", "");

            // Método para validar os dados
            valida.validarSalario(Double.parseDouble(salario));
            valida.validarNome(nome);
            valida.validarCPF(cpf, "funcionario");
            valida.validarDataNascimento(dataNascimento);

            // Adiciona os dados ao objeto funcionário
            funcionario = new Funcionario(nome, cpf, Double.parseDouble(salario));
            funcionario.setDataNascimento(dataNascimento);

            // Cadastra o funcionário no Banco de Dados
            sistemaDAO.cadastrarFuncionario(funcionario);

            // Atualiza a tabela de funcionários
            painelCadastroFuncionario.fireTabelaFuncionario();

            // Limpa os componentes
            painelCadastroFuncionario.limpaComponentes();

        } catch (ValorInvalidoException ex) {

            JOptionPane.showMessageDialog(null, ex.getMessage()
                    + "\n[Salário abaixo de R$ 500.00]", "Atenção", JOptionPane.WARNING_MESSAGE);

        } catch (NomeInvalidoException | DataInvalidaException | CpfInvalidoException ex) {

            JOptionPane.showMessageDialog(null, ex.getMessage(), "Atenção", JOptionPane.WARNING_MESSAGE);

        } catch (NullPointerException | NumberFormatException ex) {

            JOptionPane.showMessageDialog(null, "Apenas a data de nascimento é opcional", "Atenção", JOptionPane.WARNING_MESSAGE);

        }
    }

}
