package controle.excecao;

/**
 * Exceção para o nome da venda inválido
 *
 * @author Pedro Celestino Silveira Junior
 */
public class NomeVendaInvalidoException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Constrói a exceção
     *
     * @param nome o nome da venda inválido
     */
    public NomeVendaInvalidoException(String nome) {

        super("Nome da Venda inválido: " + nome
                + "\n[Tamanho deve ser entre 2 a 45 caracteres]"
                + "\n[Possui caracteres especiais ou números]");
    }

}
