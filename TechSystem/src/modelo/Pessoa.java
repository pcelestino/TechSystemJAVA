package modelo;

/**
 * Adiciona e retorna atributos de pessoa
 *
 * @author Pedro Celestino Silveira Junior
 */
public class Pessoa {

    private String nome, cpf, dataNascimento;

    /**
     * Constrói a pessoa com seus parâmetros obrigatórios
     *
     * @param nome
     */
    public Pessoa(String nome) {
        this.setNome(nome);
    }

    /**
     * Retorna o nome da pessoa
     *
     * @return o nome da pessoa
     */
    public String getNome() {
        return nome;
    }

    /**
     * Seta o nome da pessoa
     *
     * @param nome o nome da pessoa
     */
    private void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna o cpf da pessoa
     *
     * @return o cpf da pessoa
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * Seta o cpf da pessoa
     *
     * @param cpf o cpf da pessoa
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * Retorna a data de nascimento da pessoa
     *
     * @return a data de nascimento da pessoa
     */
    public String getDataNascimento() {
        return dataNascimento;
    }

    /**
     *
     * @param dataNascimento
     */
    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

}
