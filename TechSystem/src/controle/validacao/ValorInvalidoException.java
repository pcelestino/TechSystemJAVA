package controle.validacao;

/**
 * Exceção para o valor inválido
 * @author Pedro Celestino Silveira Junior
 */
public class ValorInvalidoException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Constrói a exceção
     * @param valor o valor inválido
     */
    public ValorInvalidoException(double valor) {

        super("Valor inválido: " + valor);
    }

}
