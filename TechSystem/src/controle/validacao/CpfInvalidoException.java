package controle.validacao;

/**
 * Exceção para o cpf inválido
 * @author Pedro Celestino Silveira Junior
 */
public class CpfInvalidoException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Constrói a exceção
     * @param texto um texto para complementar a descrição da exceção
     */
    public CpfInvalidoException(String texto) {

        super("Cpf inválido " + texto);
    }

}
