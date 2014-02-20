package controle.validacao;

import controle.excecao.NomeInvalidoException;
import controle.excecao.SenhaInvalidaException;
import controle.excecao.ValorInvalidoException;
import controle.excecao.NomeVendaInvalidoException;
import controle.excecao.DataInvalidaException;
import controle.excecao.CpfInvalidoException;
import controle.ConectaBanco;

import java.sql.SQLException;
import java.util.Calendar;

import javax.swing.JOptionPane;

/**
 * Valida diversos dados do programa
 *
 * @author Pedro Celestino Silveira Junior
 */
public class Validador {

    ConectaBanco bancoDados = new ConectaBanco();
    private ValidaCpf vCpf;

    /**
     * Valida o nome
     *
     * @param nome o nome para ser analisado
     * @throws NomeInvalidoException Exceção gerada quando o nome fornecido
     * contém caracteres especiais ou é maior que 45 ou é menor que 3 caracteres
     */
    public void validarNome(String nome) throws NomeInvalidoException {

        // Verifica o tamanho da string, caracteres especiais "\\p{Punct}" e numeros "\\d"
        if (nome.length() < 3 || nome.length() > 45 || nome.matches(".*?[\\p{Punct}\\d].*")) {

            throw new NomeInvalidoException(nome);
        }
    }

    /**
     * Valida o nome da venda
     *
     * @param nomeVenda o nome da venda que será analisado
     * @throws NomeVendaInvalidoException Exceção gerada quando o nome fornecido
     * contém caracteres especiais ou é maior que 45 ou é menor que 3 caracteres
     */
    public void validarNomeVenda(String nomeVenda) throws NomeVendaInvalidoException {

        // Verifica o tamanho da string, caracteres especiais "\\p{Punct}" e permite "_" com "[^_]"
        if (nomeVenda.length() < 2 || nomeVenda.length() > 45 || nomeVenda.matches(".*?[\\p{Punct}&&[^_]].*")) {

            throw new NomeVendaInvalidoException(nomeVenda);
        }
    }

    /**
     * Valida a data de nascimento
     *
     * @param dataNascimento a data de nascimento que será analisada
     * @throws DataInvalidaException Exceção gerada quando a data for inválida
     * ou o usuário for menor de 18 anos
     */
    public void validarDataNascimento(String dataNascimento) throws DataInvalidaException {

        if (!dataNascimento.equals("__ / __ / ____")) {

            Calendar dataAtual = Calendar.getInstance();
            String[] separaData = dataNascimento.split(" / ");
            int dia, mes, ano, diferencaMes, diferencaDia, idade;

            dia = Integer.parseInt(separaData[0]);
            mes = Integer.parseInt(separaData[1]);
            ano = Integer.parseInt(separaData[2]);

            diferencaDia = dataAtual.get(Calendar.DAY_OF_MONTH) - dia;
            diferencaMes = (dataAtual.get(Calendar.MONTH) + 1) - mes;
            idade = dataAtual.get(Calendar.YEAR) - ano;

            if (diferencaMes < 0 || (diferencaMes == 0 && diferencaDia < 0)) {
                idade--;
            }

            if (idade < 16 || idade > 120) {
                throw new DataInvalidaException(dataNascimento, "\n[Idade mínima 18 e máxima 120 anos]");
            } else if (dia > 31 || mes > 12) {

                throw new DataInvalidaException(dataNascimento, "");
            }
        }
    }

    /**
     * Valida o salário
     *
     * @param salario o salário que será analisado
     * @throws ValorInvalidoException Exceção gerada quando o valor do salário
     * for menor que R$ 500.00
     */
    public void validarSalario(double salario) throws ValorInvalidoException {

        if (salario < 500.00) {
            throw new ValorInvalidoException(salario);
        }
    }

    /**
     * Valida o valor da venda
     *
     * @param valorVenda o valor da venda que será analisado
     * @throws ValorInvalidoException Exceção gerada quando o valor da venda for
     * menor que R$ 0.01
     */
    public void validarValorVenda(double valorVenda) throws ValorInvalidoException {

        if (valorVenda < 0.01) {

            throw new ValorInvalidoException(valorVenda);
        }
    }

    /**
     * Valida o cpf
     *
     * @param CPF o cpf que será analisado
     * @param pessoa o tipo da pessoa que será analizada no DB
     * @throws CpfInvalidoException Exceção gerada quando o cpf for inválido ou
     * já existir no DB
     */
    public void validarCPF(String CPF, String pessoa) throws CpfInvalidoException {

        vCpf = new ValidaCpf();

        CPF = CPF.replaceAll("\\D", "");

        if (!vCpf.CPF(CPF)) {
            throw new CpfInvalidoException("");
        }

        try {
            bancoDados.conectar();
            bancoDados.executaPesquisaSQL("SELECT * FROM " + pessoa + "s");

            while (bancoDados.getResultado().next()) {

                if (CPF.equals(bancoDados.getResultado().getString("cpf_" + pessoa))) {

                    throw new CpfInvalidoException("\n[CPF atual já cadastrado]");
                }
            }
            bancoDados.desconectar();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro na verificação do CPF\n\nErro: " + e.getMessage());
        }
    }

    /**
     * Valida o cpf
     *
     * @param CPF o cpf que será analisado
     * @param cpfSelecionado o cpf selecionado na tabela
     * @param pessoa o tipo da pessoa que será analizada no DB
     * @throws CpfInvalidoException Exceção gerada quando o cpf for inválido ou
     * já existir no DB
     */
    public void validarCPF(String CPF, String cpfSelecionado, String pessoa) throws CpfInvalidoException {

        vCpf = new ValidaCpf();

        CPF = CPF.replaceAll("\\D", "");
        cpfSelecionado = cpfSelecionado.replaceAll("\\D", "");

        if (!vCpf.CPF(CPF)) {
            throw new CpfInvalidoException("");
        }

        if (!cpfSelecionado.equals(CPF)) {
            try {
                bancoDados.conectar();
                bancoDados.executaPesquisaSQL("SELECT * FROM " + pessoa + "s");

                while (bancoDados.getResultado().next()) {

                    if (CPF.equals(bancoDados.getResultado().getString("cpf_" + pessoa))) {

                        throw new CpfInvalidoException("\n[CPF atual já cadastrado]");
                    }
                }
                bancoDados.desconectar();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro na verificação do CPF\n\nErro: " + e.getMessage());
            }
        }
    }

    /**
     * Valida a senha
     *
     * @param senha a senha que será analisada
     * @throws SenhaInvalidaException Exceção gerada quando a senha for maior
     * que 32 e menor que 4 caracteres
     */
    public void validarSenha(char[] senha) throws SenhaInvalidaException {

        if (senha.length > 32 || senha.length < 4) {

            throw new SenhaInvalidaException();
        }
    }

}
