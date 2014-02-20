package controle.evento;

import static controle.ControleLoginSistema.telaPrincipal;
import controle.excecao.CpfInvalidoException;
import controle.excecao.DataInvalidaException;
import controle.excecao.NomeInvalidoException;
import controle.validacao.Validador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.Funcionario;
import modelo.SistemaDAO;
import visao.edicao.PainelEdicaoFuncionario;

/**
 * Acoes do painel para edição de funcionário
 *
 * @author Pedro Celestino Silveira Junior
 */
public class BtAtualizarFuncionario implements ActionListener {

    private Funcionario funcionario;
    private Validador valida;
    private String nome, cpf, cpfSelecionado, dataNascimento, salario;
    private SistemaDAO sistemaDAO;
    private PainelEdicaoFuncionario painelEdicaoFuncionario;
    private int linha;

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            sistemaDAO = new SistemaDAO();
            painelEdicaoFuncionario = telaPrincipal.getPainelCards().getPainelEdicaoFuncionario();

            // Instância o objeto Validador para validar os dados
            valida = new Validador();

            // Captura a linha selecionada na tabela do formulário
            linha = painelEdicaoFuncionario.getLinhaSelecionada();

            // Seleciona o cpf da tabela do formulário
            cpfSelecionado = painelEdicaoFuncionario.getTextoEm(linha, 1);

            // Recupera os dados digitados no painel de edição
            nome = painelEdicaoFuncionario.getTfNome().getText();
            cpf = painelEdicaoFuncionario.getFtfCpf().getText();
            dataNascimento = painelEdicaoFuncionario.getFtfDataNascimento().getText();
            salario = painelEdicaoFuncionario.getFtfSalario().getText();

            // Método necessário para ser compatível com double
            salario = salario.replaceAll(",", ".");

            // Remove caracteres não numéricos
            cpfSelecionado = cpfSelecionado.replaceAll("\\D", "");
            cpf = cpf.replaceAll("\\D", "");

            // Método para validar os dados
            valida.validarNome(nome);
            valida.validarCPF(cpf, cpfSelecionado, "funcionario");
            valida.validarDataNascimento(dataNascimento);

            // Adiciona os dados ao objeto funcionário
            funcionario = new Funcionario(nome, cpf, Double.parseDouble(salario));
            funcionario.setDataNascimento(dataNascimento);

            // Cadastra o cliente no Banco de Dados
            sistemaDAO.atualizarFuncionario(funcionario, cpfSelecionado);

            // Atualiza a tabela de funcionários
            painelEdicaoFuncionario.fireTabelaFuncionario();

            // Desabilita os componentes
            painelEdicaoFuncionario.desabilitaComponentes();

            // Limpa os componentes
            painelEdicaoFuncionario.limpaComponentes();

        } catch (NomeInvalidoException | CpfInvalidoException | DataInvalidaException ex) {

            JOptionPane.showMessageDialog(null, ex.getMessage(), "Atenção", JOptionPane.WARNING_MESSAGE);

        } catch (NullPointerException | NumberFormatException ex) {

            JOptionPane.showMessageDialog(null, "Apenas a data de nascimento é opcional", "Atenção", JOptionPane.WARNING_MESSAGE);
        }
    }

}
