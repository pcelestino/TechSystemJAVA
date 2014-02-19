package modelo;

/**
 * Adiciona e retorna atributos de funcionário
 * 
 * @author Pedro Celestino Silveira Junior
 */
public class Funcionario extends Pessoa {

    private double salario;

    /**
     * Constrói o funcionário com seus parâmetros obrigatórios
     * 
     * @param nome o nome do funcionário
     * @param cpf o cpf do cunfionário
     * @param salario o salário do funcionário
     */
    public Funcionario(String nome, String cpf, double salario) {
        super(nome);
        super.setCpf(cpf);
        this.setSalario(salario);
    }

    /**
     * Retorna o salário do funcionário
     * 
     * @return o salário do funcionário
     */
    public double getSalario() {
        return salario;
    }
    
    /**
     * Seta o salário do funcionário
     * 
     * @param salario salario do funcionário
     */
    private void setSalario(double salario) {
        this.salario = salario;
    }
}
