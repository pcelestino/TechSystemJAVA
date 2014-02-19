package modelo;

/**
 * Adiciona e retorna atributos de usuário
 * 
 * @author Pedro Celestino Silveira Junior
 */
public class Usuario extends Pessoa {

    private String login;
    private char[] senha;

    /**
     * Constrói o usuário com seus parâmetros obrigatórios
     * 
     * @param nome o nome do usuário
     * @param login o login do usuário, um cpf válido
     * @param senha a senha do usuário
     */
    public Usuario(String nome, String login, char[] senha) {
        super(nome);
        super.setCpf(login);
        this.setLogin(login);
        this.setSenha(senha);
    }

    /**
     * Retorna o login do usuário
     * 
     * @return o login do usuário, um cpf válido
     */
    public String getLogin() {
        return login;
    }

    /**
     * Seta o login do usuário
     * 
     * @param login o login do usuário, um cpf válido
     */
    private void setLogin(String login) {
        this.login = login;
    }

    /**
     * Retorna a senha do usuário
     * 
     * @return a senha do usuário
     */
    public char[] getSenha() {
        return senha;
    }
    
    /**
     * Seta a senha do usuário
     * 
     * @param senha a senha do usuário
     */
    private void setSenha(char[] senha) {
        this.senha = senha;
    }

}
