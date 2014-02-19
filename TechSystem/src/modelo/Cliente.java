package modelo;

/**
 * Adiciona e retorna atributos de cliente
 * 
 * @author Pedro Celestino Silveira Junior
 */
public class Cliente extends Pessoa {

    private String tipo;

    /**
     * Constrói o cliente com seus parâmetros obrigatórios
     * 
     * @param nome o nome do cliente
     * @param cpf o cpf do cliente
     */
    public Cliente(String nome, String cpf) {
        super(nome);
        super.setCpf(cpf);
    }

    /**
     * Retorna o tipo do cliente
     * 
     * @return o tipo do cliente (Normal, Avançado, Especial)
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Seta o tipo do cliente
     * 
     * @param tipo o tipo do cliente (Normal, Avançado, Especial)
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
