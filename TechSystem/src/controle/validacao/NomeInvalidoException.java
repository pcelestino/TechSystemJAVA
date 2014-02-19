package controle.validacao;

/**
 * Exceção para o nome inválido
 * @author Pedro Celestino Silveira Junior
 */
public class NomeInvalidoException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Constrói a exceção
     * @param nome o nome inválido
     */
    public NomeInvalidoException(String nome) {

        super("Nome inválido: " + nome
                + "\n[Tamanho deve ser entre 2 a 45 caracteres]"
                + "\n[Possui caracteres especiais ou números]");
    }

}
