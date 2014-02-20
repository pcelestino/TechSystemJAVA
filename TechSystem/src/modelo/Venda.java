package modelo;

/**
 * Adiciona e retorna atributos de venda
 *
 * @author Pedro
 */
public class Venda {

    private String cpfCliente, cpfFuncionario, nomeVenda;
    private double valorVenda;

    /**
     * Constrói a venda com seus parâmetros obrigatórios
     *
     * @param cpfCliente o cpf do cliente
     * @param cpfFuncionario o cpf do funcionário
     * @param nomeVenda o nome da venda
     * @param valorVenda o valor da venda
     */
    public Venda(String cpfCliente, String cpfFuncionario, String nomeVenda, double valorVenda) {
        this.setCpfCliente(cpfCliente);
        this.setCpfFuncionario(cpfFuncionario);
        this.setNomeVenda(nomeVenda);
        this.setValorVenda(valorVenda);
    }

    /**
     * Retorna o cpf do cliente
     *
     * @return o cpf do cliente
     */
    public String getCpfCliente() {
        return cpfCliente;
    }

    /**
     * Seta o cpf do usuário
     *
     * @param cpfUsuario o cpf do usuário
     */
    private void setCpfCliente(String cpfUsuario) {
        this.cpfCliente = cpfUsuario;
    }

    /**
     * Retorna o cpf do funcionário
     *
     * @return o cpf do funcionário
     */
    public String getCpfFuncionario() {
        return cpfFuncionario;
    }

    /**
     * Seta o cpf do funcionário
     *
     * @param cpfFuncionario o cpf do funcionário
     */
    private void setCpfFuncionario(String cpfFuncionario) {
        this.cpfFuncionario = cpfFuncionario;
    }

    /**
     * Retorna o nome da venda
     *
     * @return o nome da venda
     */
    public String getNomeVenda() {
        return nomeVenda;
    }

    /**
     * Seta o nome da venda
     *
     * @param nomeVenda o nome da venda
     */
    private void setNomeVenda(String nomeVenda) {
        this.nomeVenda = nomeVenda;
    }

    /**
     * Retorna o valor da venda
     *
     * @return o valor da venda
     */
    public double getValorVenda() {
        return valorVenda;
    }

    /**
     * Seta o valor da venda
     *
     * @param valorVenda o valor da venda
     */
    private void setValorVenda(double valorVenda) {
        this.valorVenda = valorVenda;
    }

}
