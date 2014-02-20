package controle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 * Inicializa a conexão e atualização ao DB
 *
 * @author Pedro Celestino Silveira Junior
 */
public class ConectaBanco {

    // Responsável por realizar a conexão com o banco de dados
    private Connection conexao;

    // Responsável por preparar e realizar pesquisas no banco de dados
    private Statement stm;

    // Responsável por armazenar o resultado de uma pesquisa passada para o statement
    private ResultSet resultado;

    // Responsável por identificar o serviço de banco de dados
    private static String driver;

    // Responsável por setar o local do banco de dados
    private static String caminho;

    private static String usuario;
    private static String senha;
    private static String dbTipo;
    private static String dbNome = "dbempresa";

    private String sql;
    private boolean isConectado;

    /**
     * Testa a conexão com o DB
     *
     * @return true para sucesso e false para conexão inválida
     */
    public boolean testaConexao() {

        isConectado = false;

        try {

            conectar();

            stm = conexao.createStatement();

            if (driver.equals("com.mysql.jdbc.Driver")) {

                caminho = "jdbc:mysql://localhost:3306/";

                isConectado = stm.execute("show tables;");
            }

            if (driver.equals("org.postgresql.Driver")) {

                caminho = "jdbc:postgresql://localhost:5432/";

                isConectado = stm.execute("SELECT * FROM pg_catalog.pg_tables");

                caminho = "jdbc:postgresql://localhost:5432/" + dbNome;
            }

            conexao.close();
            stm.close();

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Erro de conexão!\nErro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (NullPointerException e) {
        }

        return isConectado;
    }

    /**
     * Responsável por realizar a conexão com o DB
     */
    public void conectar() {

        try {

            // Registra o driver de conexão
            Class.forName(driver);

            // Seta a propriedade com o driver de conexão
            System.setProperty("jdbc.Drivers", driver);

            // Realiza a conexão com o banco de dados
            conexao = DriverManager.getConnection(caminho, usuario, senha);

        } catch (SQLException | ClassNotFoundException e) {

            JOptionPane.showMessageDialog(null, "Erro de conexão!\nErro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);

        } catch (NullPointerException e) {
        }
    }

    /**
     * Executa uma pesquisa ao DB
     *
     * @param sql parâmetros para pesquisa no DB em formato SQL
     */
    public void executaPesquisaSQL(String sql) {
        try {
            stm = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultado = stm.executeQuery(sql);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de conexão para pesquisa ao SQL!\nErro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Executa uma atualização ao DB
     *
     * @param sql parâmetros para atualização no DB em formato SQL
     */
    public void executaAtualizacaoSQL(String sql) {
        try {
            stm = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stm.executeUpdate(sql);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de conexão para atualização ao SQL!\nErro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Reponsável por fechar a conexão com o banco de dados
     */
    public void desconectar() {

        try {
            // fecha a conexão
            conexao.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão!\nErro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Reponsável por garantir que exista um DB para o sistema
     */
    public void criarDatabase() {

        conectar();

        try {
            if (ConectaBanco.getDbTipo().equals("MySQL")) {
                sql = "CREATE DATABASE IF NOT EXISTS " + ConectaBanco.getDbNome();
            }
            if (ConectaBanco.getDbTipo().equals("PostgreSQL")) {
                sql = "CREATE DATABASE " + ConectaBanco.getDbNome();
            }
            stm = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stm.executeUpdate(sql);

            ConectaBanco.setCaminho(caminho + dbNome);
            this.desconectar();

        } catch (SQLException ex) {

            // Erro: Database já existe
            if (!ex.getSQLState().equals("42P04")) {

                JOptionPane.showMessageDialog(null, "Erro ao criar o database: "
                        + ConectaBanco.getDbNome() + "\nErro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Reponsável por garantir que exista as tabela corretas no DB do sistema
     */
    public void criarTabelas() {
        try {
            this.conectar();

            stm = conexao.createStatement();

            if (dbTipo.equals("MySQL")) {

                sql = "CREATE TABLE IF NOT EXISTS clientes"
                        + "  (id_cliente           INTEGER(11) NOT NULL AUTO_INCREMENT,"
                        + "   nome_cliente         VARCHAR(45),"
                        + "   cpf_cliente          CHAR(11),"
                        + "   nascimento_cliente   DATE,"
                        + "   tipo_cliente         VARCHAR(8),"
                        + "   PRIMARY KEY(id_cliente))";

                stm.executeUpdate(sql);

                sql = "CREATE TABLE IF NOT EXISTS funcionarios"
                        + "  (id_funcionario           INTEGER(11) NOT NULL AUTO_INCREMENT,"
                        + "   nome_funcionario         VARCHAR(45),"
                        + "   cpf_funcionario          CHAR(11),"
                        + "   nascimento_funcionario   DATE,"
                        + "   salario_funcionario      DOUBLE,"
                        + "   PRIMARY KEY(id_funcionario))";

                stm.executeUpdate(sql);

                sql = "CREATE TABLE IF NOT EXISTS usuarios"
                        + "  (id_usuario           INTEGER(11) NOT NULL AUTO_INCREMENT,"
                        + "   nome_usuario         VARCHAR(45),"
                        + "   cpf_usuario          CHAR(11),"
                        + "   nascimento_usuario   DATE,"
                        + "   senha_usuario        VARCHAR(32),"
                        + "   PRIMARY KEY(id_usuario))";

                stm.executeUpdate(sql);

                sql = "CREATE TABLE IF NOT EXISTS vendas"
                        + "  (id_venda           INTEGER(11) NOT NULL AUTO_INCREMENT,"
                        + "   cpf_cliente        CHAR(11),"
                        + "   cpf_funcionario    CHAR(11),"
                        + "   nome_venda         VARCHAR(45),"
                        + "   valor_venda        DOUBLE,"
                        + "   PRIMARY KEY(id_venda))";

                stm.executeUpdate(sql);

                sql = "CREATE TABLE IF NOT EXISTS login"
                        + "  (id_login       INTEGER(11) NOT NULL AUTO_INCREMENT,"
                        + "   usuario        CHAR(11),"
                        + "   senha          VARCHAR(32),"
                        + "   PRIMARY KEY(id_login))";

                stm.executeUpdate(sql);
            }
            if (dbTipo.equals("PostgreSQL")) {

                sql = "CREATE TABLE IF NOT EXISTS clientes "
                        + "(id_cliente             SERIAL PRIMARY KEY NOT NULL,"
                        + " nome_cliente           VARCHAR(45), "
                        + " cpf_cliente            CHAR(11), "
                        + " nascimento_cliente     DATE, "
                        + " tipo_cliente           VARCHAR(8))";

                stm.executeUpdate(sql);

                sql = "CREATE TABLE IF NOT EXISTS funcionarios "
                        + "(id_funcionario             SERIAL PRIMARY KEY NOT NULL,"
                        + " nome_funcionario           VARCHAR(45), "
                        + " cpf_funcionario            CHAR(11), "
                        + " nascimento_funcionario     DATE, "
                        + " salario_funcionario        DOUBLE PRECISION)";

                stm.executeUpdate(sql);

                sql = "CREATE TABLE IF NOT EXISTS usuarios "
                        + "(id_usuario             SERIAL PRIMARY KEY NOT NULL,"
                        + " nome_usuario           VARCHAR(45), "
                        + " cpf_usuario            CHAR(11), "
                        + " nascimento_usuario     DATE, "
                        + " senha_usuario          VARCHAR(32))";

                stm.executeUpdate(sql);

                sql = "CREATE TABLE IF NOT EXISTS vendas "
                        + "(id_venda             SERIAL PRIMARY KEY NOT NULL,"
                        + " cpf_cliente          CHAR(11), "
                        + " cpf_funcionario      CHAR(11), "
                        + " nome_venda           VARCHAR(45), "
                        + " valor_venda          DOUBLE PRECISION)";

                stm.executeUpdate(sql);

                sql = "CREATE TABLE IF NOT EXISTS login "
                        + "(id_login             SERIAL PRIMARY KEY NOT NULL,"
                        + " usuario              CHAR(11), "
                        + " senha                VARCHAR(32))";

                stm.executeUpdate(sql);
            }

            this.executaPesquisaSQL("SELECT * FROM usuarios");

            if (!resultado.next()) {
                sql = "INSERT INTO usuarios"
                        + " (nome_usuario, cpf_usuario, nascimento_usuario, senha_usuario)"
                        + " values('Pedro', '01319216307', '1987-08-06', MD5('novo'))";

                stm.executeUpdate(sql);

                sql = "INSERT INTO login"
                        + " (usuario, senha)"
                        + " values('01319216307', MD5('novo'))";

                stm.executeUpdate(sql);
            }

            this.desconectar();

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Erro ao criar as tabelas\nErro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Seta o driver correto para conexão ao DB
     *
     * @param driver a String contendo os párâmetros para o driver
     */
    public static void setDriver(String driver) {
        ConectaBanco.driver = driver;
    }

    /**
     * Seta o caminho para a conexão ao DB
     *
     * @param caminho a String que contém os parãmetros para a conexão
     */
    public static void setCaminho(String caminho) {
        ConectaBanco.caminho = caminho;
    }

    /**
     * Seta o login do DB
     *
     * @param usuario o login do DB
     */
    public static void setUsuario(String usuario) {
        ConectaBanco.usuario = usuario;
    }

    /**
     * Seta a senha do DB
     *
     * @param senha a senha do DB
     */
    public static void setSenha(String senha) {
        ConectaBanco.senha = senha;
    }

    /**
     * Seta o nome do DB
     *
     * @param dbNome o nome do DB
     */
    public static void setDbNome(String dbNome) {
        ConectaBanco.dbNome = dbNome;
    }

    /**
     * Seta o tipo do DB
     *
     * @param dbTipo o tipo do DB
     */
    public static void setDbTipo(String dbTipo) {
        ConectaBanco.dbTipo = dbTipo;
    }

    /**
     * Retorna o nome do DB
     *
     * @return o nome do DB
     */
    public static String getDbNome() {
        return dbNome;
    }

    /**
     * Retorna o tipo do DB
     *
     * @return o tipo do DB
     */
    public static String getDbTipo() {
        return dbTipo;
    }

    /**
     * Retorna o driver do DB
     *
     * @return o driver do DB
     */
    public static String getDriver() {
        return driver;
    }

    /**
     * Retorna o login do DB
     *
     * @return o login do DB
     */
    public static String getUsuario() {
        return usuario;
    }

    /**
     * Retorna a senha do DB
     *
     * @return a senha do DB
     */
    public static String getSenha() {
        return senha;
    }

    /**
     * Retorna o caminho do DB
     *
     * @return o caminho do DB
     */
    public static String getCaminho() {
        return caminho;
    }

    /**
     * Retorna um Connection do DB
     *
     * @return um Connection do DB
     */
    public Connection getConexao() {
        return conexao;
    }

    /**
     * Retorna um ResultSet do DB
     *
     * @return um ResultSet do DB
     */
    public ResultSet getResultado() {
        return resultado;
    }

}
