package controle.excecao;

/**
 * Exceção para a senha inválida
 *
 * @author Pedro Celestino Silveira Junior
 */
public class SenhaInvalidaException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Constrói a exceção
     */
    public SenhaInvalidaException() {

        super("Senha inválida");
    }

}
