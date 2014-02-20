package controle.excecao;

/**
 * Exceção para a data inválida
 *
 * @author Pedro Celestino Silveira Junior
 */
public class DataInvalidaException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Constrói a exceção
     *
     * @param data a data inválida
     * @param texto um texto para complementar a descrição da exceção
     */
    public DataInvalidaException(String data, String texto) {
        super("Data inválida: " + data + " " + texto);
    }

}
