package modelo;

import controle.ConectaBanco;
import controle.excecao.SenhaInvalidaException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * Administra o retorno e a transferência de dados pelo DB
 * @author Pedro Celestino Silveira Junior
 */
public class SistemaDAO {
    
    private String nome, cpf, cpfCliente, cpfFuncionario, data, salario,
            valorVenda, tipo, nomeVenda, dia, mes, ano, senha, idVenda;
    private ArrayList<Object[]> dados;
    private ArrayList<String> consulta;
    private String[] colunas;
    private Tabela modeloTabela;
    private ConectaBanco bancoDados;
    private PreparedStatement pst;
    private Date dtValue;
    /** 
    * Locale Brasileiro 
    */  
    private final Locale BRAZIL = new Locale("pt","BR");  
    /** 
    * Símbolos especificos do Real Brasileiro 
    */  
    private final DecimalFormatSymbols REAL = new DecimalFormatSymbols(BRAZIL);  
    /** 
    * Mascara de dinheiro para Real Brasileiro 
    */  
    private final DecimalFormat DINHEIRO_REAL = new DecimalFormat("¤ ###,###,##0.00",REAL);

    /**
     * Cadastra um cliente
     * @param cliente a classe cliente
     */
    public void cadastrarCliente(Cliente cliente) {

        try {

            bancoDados = new ConectaBanco();

            // Abrindo conexão
            bancoDados.conectar();

            dtValue = null;

            if (!cliente.getDataNascimento().equals("__ / __ / ____")) {

                // Transforma a data no formato brasileiro para o formato SQL
                dia = cliente.getDataNascimento().substring(0, 2);
                mes = cliente.getDataNascimento().substring(5, 7);
                ano = cliente.getDataNascimento().substring(10, 14);
                data = ano + '-' + mes + '-' + dia;

                dtValue = Date.valueOf(data);
            }

            pst = bancoDados.getConexao().prepareStatement("insert into clientes"
                    + " (nome_cliente, cpf_cliente, nascimento_cliente, tipo_cliente)"
                    + " values(?, ?, ?, ?)");

            pst.setString(1, cliente.getNome());
            pst.setString(2, cliente.getCpf());
            pst.setDate(3, dtValue);
            pst.setString(4, cliente.getTipo());
            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "Cliente cadastrado");

        } catch (SQLException e1) {

            JOptionPane.showMessageDialog(null, "Erro na inserção!\nErro: " + e1.getMessage());
        } finally {

            bancoDados.desconectar();
        }
    }

    /**
     * Cadastra um funcionário
     * @param funcionario a classe funcionário
     */
    public void cadastrarFuncionario(Funcionario funcionario) {

        try {

            bancoDados = new ConectaBanco();

            // Abrindo conexão
            bancoDados.conectar();

            dtValue = null;

            if (!funcionario.getDataNascimento().equals("__ / __ / ____")) {

                // Transforma a data no formato brasileiro para o formato SQL
                dia = funcionario.getDataNascimento().substring(0, 2);
                mes = funcionario.getDataNascimento().substring(5, 7);
                ano = funcionario.getDataNascimento().substring(10, 14);
                data = ano + '-' + mes + '-' + dia;

                dtValue = Date.valueOf(data);
            }

            pst = bancoDados.getConexao().prepareStatement("insert into funcionarios"
                    + " (nome_funcionario, cpf_funcionario, nascimento_funcionario, salario_funcionario)"
                    + " values(?, ?, ?, ?)");

            pst.setString(1, funcionario.getNome());
            pst.setString(2, funcionario.getCpf());
            pst.setDate(3, dtValue);
            pst.setDouble(4, funcionario.getSalario());
            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "Funcionário cadastrado");

        } catch (SQLException e1) {

            JOptionPane.showMessageDialog(null, "Erro na inserção!\nErro: " + e1.getMessage());
        } finally {

            bancoDados.desconectar();
        }
    }

    /**
     * Cadastra um usuário
     * @param usuario a classe usuário
     */
    public void cadastrarUsuario(Usuario usuario) {

        senha = "";
        for (int i = 0; i < usuario.getSenha().length; i++) {
            senha += usuario.getSenha()[i];
        }

        try {

            bancoDados = new ConectaBanco();

            // Abrindo conexão
            bancoDados.conectar();

            dtValue = null;

            if (!usuario.getDataNascimento().equals("__ / __ / ____")) {

                // Transforma a data no formato brasileiro para o formato SQL
                dia = usuario.getDataNascimento().substring(0, 2);
                mes = usuario.getDataNascimento().substring(5, 7);
                ano = usuario.getDataNascimento().substring(10, 14);
                data = ano + '-' + mes + '-' + dia;

                dtValue = Date.valueOf(data);

            }

            pst = bancoDados.getConexao().prepareStatement("insert into usuarios"
                    + " (nome_usuario, cpf_usuario, nascimento_usuario, senha_usuario)"
                    + " values(?, ?, ?, MD5(?))");

            pst.setString(1, usuario.getNome());
            pst.setString(2, usuario.getLogin());
            pst.setDate(3, dtValue);
            pst.setString(4, senha);
            pst.executeUpdate();

            pst = bancoDados.getConexao().prepareStatement("insert into login (usuario, senha) values(?, MD5(?))");
            pst.setString(1, usuario.getCpf());
            pst.setString(2, senha);
            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "Usuário cadastrado");

            // Fechando conexão
            bancoDados.desconectar();

        } catch (SQLException e1) {

            JOptionPane.showMessageDialog(null, "Erro na inserção!\nErro: " + e1.getMessage());

        } finally {

            bancoDados.desconectar();
        }
    }

    /**
     * Cadastra uma venda
     * @param venda a classe venda
     */
    public void cadastrarVenda(Venda venda) {

        try {

            bancoDados = new ConectaBanco();

            // Abrindo conexão
            bancoDados.conectar();

            pst = bancoDados.getConexao().prepareStatement("insert into vendas"
                    + " (cpf_cliente, cpf_funcionario, nome_venda, valor_venda)"
                    + " values(?, ?, ?, ?)");

            pst.setString(1, venda.getCpfCliente());
            pst.setString(2, venda.getCpfFuncionario());
            pst.setString(3, venda.getNomeVenda());
            pst.setDouble(4, venda.getValorVenda());
            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "Venda Cadastrada");

        } catch (SQLException e1) {

            JOptionPane.showMessageDialog(null, "Erro na inserção!\nErro: " + e1.getMessage());
        } finally {

            bancoDados.desconectar();
        }
    }

    /**
     * Atualiza os dados do cliente
     * @param cliente a classe cliente
     * @param cpfSelecionado o cpf selecionado na tabela cliente
     */
    public void atualizarCliente(Cliente cliente, String cpfSelecionado) {

        try {

            bancoDados = new ConectaBanco();

            // Abrindo conexão
            bancoDados.conectar();

            dtValue = null;

            if (!cliente.getDataNascimento().equals("__ / __ / ____")) {

                // Transforma a data no formato brasileiro para o formato SQL
                dia = cliente.getDataNascimento().substring(0, 2);
                mes = cliente.getDataNascimento().substring(5, 7);
                ano = cliente.getDataNascimento().substring(10, 14);
                data = ano + '-' + mes + '-' + dia;

                dtValue = Date.valueOf(data);

            }

            pst = bancoDados.getConexao().prepareStatement("UPDATE clientes set nome_cliente = ?, cpf_cliente = ?, nascimento_cliente = ?, tipo_cliente = ? where cpf_cliente = ?");

            pst.setString(1, cliente.getNome());
            pst.setString(2, cliente.getCpf());
            pst.setDate(3, dtValue);
            pst.setString(4, cliente.getTipo());
            pst.setString(5, cpfSelecionado);

            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "Cliente atualizado");

        } catch (SQLException e1) {

            JOptionPane.showMessageDialog(null, "Erro na inserção!\nErro: " + e1.getMessage());
        } finally {
            bancoDados.desconectar();
        }
    }

    /**
     * Atualiza os dados do funcionário
     * @param funcionario a classe funcionário
     * @param cpfSelecionado o cpf selecionado na tabela de funcionários
     */
    public void atualizarFuncionario(Funcionario funcionario, String cpfSelecionado) {

        try {

            bancoDados = new ConectaBanco();

            // Abrindo conexão
            bancoDados.conectar();

            dtValue = null;

            if (!funcionario.getDataNascimento().equals("__ / __ / ____")) {

                // Transforma a data no formato brasileiro para o formato SQL
                dia = funcionario.getDataNascimento().substring(0, 2);
                mes = funcionario.getDataNascimento().substring(5, 7);
                ano = funcionario.getDataNascimento().substring(10, 14);
                data = ano + '-' + mes + '-' + dia;

                dtValue = Date.valueOf(data);
            }

            pst = bancoDados.getConexao().prepareStatement("UPDATE funcionarios set nome_funcionario = ?, cpf_funcionario = ?, nascimento_funcionario = ?, salario_funcionario = ? where cpf_funcionario = ?");

            pst.setString(1, funcionario.getNome());
            pst.setString(2, funcionario.getCpf());
            pst.setDate(3, dtValue);
            pst.setDouble(4, funcionario.getSalario());
            pst.setString(5, cpfSelecionado);

            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "Funcionario atualizado");

        } catch (SQLException e1) {

            JOptionPane.showMessageDialog(null, "Erro na inserção!\nErro: " + e1.getMessage());
        } finally {

            bancoDados.desconectar();
        }
    }

    /**
     * Atualiza os dados do usuário
     * @param usuario a classe usuário
     * @param cpfSelecionado o cpf selecionado na tabela de usuários
     */
    public void atualizarUsuario(Usuario usuario, String cpfSelecionado) {

        senha = "";
        for (int i = 0; i < usuario.getSenha().length; i++) {
            senha += usuario.getSenha()[i];
        }

        try {

            // Abrindo conexão
            bancoDados.conectar();

            dtValue = null;

            if (!usuario.getDataNascimento().equals("__ / __ / ____")) {

                // Transforma a data no formato brasileiro para o formato SQL
                dia = usuario.getDataNascimento().substring(0, 2);
                mes = usuario.getDataNascimento().substring(5, 7);
                ano = usuario.getDataNascimento().substring(10, 14);
                data = ano + '-' + mes + '-' + dia;

                dtValue = Date.valueOf(data);
            }

            pst = bancoDados.getConexao().prepareStatement("UPDATE usuarios set nome_usuario = ?, cpf_usuario = ?, nascimento_usuario = ?, senha_usuario = MD5(?) where cpf_usuario = ?");

            pst.setString(1, usuario.getNome());
            pst.setString(2, usuario.getCpf());
            pst.setDate(3, dtValue);
            pst.setString(4, senha);
            pst.setString(5, cpfSelecionado);

            pst.executeUpdate();

            pst = bancoDados.getConexao().prepareStatement("UPDATE login set usuario = ?, senha = MD5(?) where usuario = ?");

            pst.setString(1, usuario.getCpf());
            pst.setString(2, senha);
            pst.setString(3, cpfSelecionado);

            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "Usuario atualizado");

        } catch (SQLException e1) {

            JOptionPane.showMessageDialog(null, "Erro na inserção!\nErro: " + e1.getMessage());
        } finally {

            bancoDados.desconectar();
        }
    }

    /**
     * Atualiza os dados da venda
     * @param venda a classe venda
     * @param idVenda o id da tabela de vendas
     */
    public void atualizarVenda(Venda venda, String idVenda) {

        try {

            bancoDados = new ConectaBanco();

            // Abrindo conexão
            bancoDados.conectar();

            pst = bancoDados.getConexao().prepareStatement("UPDATE vendas set cpf_cliente = ?, cpf_funcionario = ?, nome_venda = ?, valor_venda = ? where id_venda = ?");
            
            pst.setString(1, venda.getCpfCliente());
            pst.setString(2, venda.getCpfFuncionario());
            pst.setString(3, venda.getNomeVenda());
            pst.setDouble(4, venda.getValorVenda());
            pst.setInt(5, Integer.parseInt(idVenda));

            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "Venda atualizada");

        } catch (SQLException e1) {
            
            JOptionPane.showMessageDialog(null, "Erro na inserção!\nErro: " + e1.getMessage());
        } finally {

            bancoDados.desconectar();
        }
    }

    /**
     * Cria a tabela de clientes
     * @return uma tabela de clientes
     */
    public Tabela gerarTabelaCliente() {

        // Banco de Dados
        bancoDados = new ConectaBanco();
        bancoDados.conectar();
        dados = new ArrayList<>();

        try {

            bancoDados.executaPesquisaSQL("SELECT nome_cliente, cpf_cliente, "
                    + "nascimento_cliente, tipo_cliente FROM clientes");

            while (bancoDados.getResultado().next()) {

                // Ler o registro do banco de dados
                nome = bancoDados.getResultado().getString("nome_cliente");
                cpf = bancoDados.getResultado().getString("cpf_cliente");
                data = bancoDados.getResultado().getString("nascimento_cliente");
                tipo = bancoDados.getResultado().getString("tipo_cliente");

                if (data != null) {
                    // Transforma a data no formato SQL para o formato br
                    dia = data.substring(8, 10);
                    mes = data.substring(5, 7);
                    ano = data.substring(0, 4);
                    data = dia + " / " + mes + " / " + ano;

                } else {

                    data = "";
                }

                // Adiciona "." e "-" na string cpf
                cpf = cpf.substring(0, 3) + "."
                        + cpf.substring(3, 6) + "."
                        + cpf.substring(6, 9) + "-"
                        + cpf.substring(9, 11);

                dados.add(new Object[]{nome, cpf, data, tipo});
            }
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Erro de SQL - " + e.getMessage());

        } finally {
            bancoDados.desconectar();
        }
        colunas = new String[]{"Nome", "CPF", "Nascimento", "Tipo"};
        modeloTabela = new Tabela(dados, colunas);
        return modeloTabela;
    }

    /**
     * Cria a tabela de funcionários
     * @return uma tabela de funcionários
     */
    public Tabela gerarTabelaFuncionario() {

        // Banco de Dados
        bancoDados = new ConectaBanco();
        bancoDados.conectar();
        dados = new ArrayList<>();

        try {

            bancoDados.executaPesquisaSQL("SELECT nome_funcionario, "
                    + "cpf_funcionario, nascimento_funcionario, "
                    + "salario_funcionario FROM funcionarios");

            while (bancoDados.getResultado().next()) {
                
                // Ler o registro do banco de dados
                nome = bancoDados.getResultado().getString("nome_funcionario");
                cpf = bancoDados.getResultado().getString("cpf_funcionario");
                data = bancoDados.getResultado().getString("nascimento_funcionario");
                salario = DINHEIRO_REAL.format(bancoDados.getResultado().getDouble("salario_funcionario"));

                if (data != null) {
                    // Transforma a data no formato SQL para o formato br
                    dia = data.substring(8, 10);
                    mes = data.substring(5, 7);
                    ano = data.substring(0, 4);
                    data = dia + " / " + mes + " / " + ano;

                } else {

                    data = "";
                }

                // Adiciona "." e "-" na string cpf
                cpf = cpf.substring(0, 3) + "."
                        + cpf.substring(3, 6) + "."
                        + cpf.substring(6, 9) + "-"
                        + cpf.substring(9, 11);

                dados.add(new Object[]{nome, cpf, data, salario});
            }
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Erro de SQL - " + e.getMessage());

        } finally {
            bancoDados.desconectar();
        }
        colunas = new String[]{"Nome", "CPF", "Nascimento", "Salario"};
        modeloTabela = new Tabela(dados, colunas);
        return modeloTabela;
    }

    /**
     * Cria a tabela de usuários
     * @return uma tabela de usuários
     */
    public Tabela gerarTabelaUsuario() {

        // Banco de Dados
        bancoDados = new ConectaBanco();
        bancoDados.conectar();
        dados = new ArrayList<>();

        try {

            bancoDados.executaPesquisaSQL("SELECT nome_usuario, cpf_usuario, "
                    + "nascimento_usuario, senha_usuario FROM usuarios");

            while (bancoDados.getResultado().next()) {

                // Ler o registro do banco de dados
                nome = bancoDados.getResultado().getString("nome_usuario");
                cpf = bancoDados.getResultado().getString("cpf_usuario");
                data = bancoDados.getResultado().getString("nascimento_usuario");

                if (data != null) {
                    // Transforma a data no formato SQL para o formato br
                    dia = data.substring(8, 10);
                    mes = data.substring(5, 7);
                    ano = data.substring(0, 4);
                    data = dia + " / " + mes + " / " + ano;

                } else {

                    data = "";
                }

                // Adiciona "." e "-" na string cpf
                cpf = cpf.substring(0, 3) + "."
                        + cpf.substring(3, 6) + "."
                        + cpf.substring(6, 9) + "-"
                        + cpf.substring(9, 11);

                dados.add(new Object[]{nome, cpf, data, tipo});
            }
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Erro de SQL - " + e.getMessage());

        } finally {
            bancoDados.desconectar();
        }
        colunas = new String[]{"Nome", "CPF", "Nascimento"};
        modeloTabela = new Tabela(dados, colunas);
        return modeloTabela;
    }

    /**
     * Cria a tabela de vendas
     * @return uma tabela de vendas
     */
    public Tabela gerarTabelaVenda() {

        // Banco de Dados
        bancoDados = new ConectaBanco();
        bancoDados.conectar();
        dados = new ArrayList<>();

        try {

            bancoDados.executaPesquisaSQL("SELECT id_venda, cpf_cliente, cpf_funcionario, "
                    + "nome_venda, valor_venda FROM vendas");

            while (bancoDados.getResultado().next()) {

                // Ler o registro do banco de dados
                idVenda = bancoDados.getResultado().getString("id_venda");
                cpfCliente = bancoDados.getResultado().getString("cpf_cliente");
                cpfFuncionario = bancoDados.getResultado().getString("cpf_funcionario");
                nomeVenda = bancoDados.getResultado().getString("nome_venda");
                valorVenda = bancoDados.getResultado().getString("valor_venda");

                // Adiciona "." e "-" na string cpf
                cpfCliente = cpfCliente.substring(0, 3) + "."
                        + cpfCliente.substring(3, 6) + "."
                        + cpfCliente.substring(6, 9) + "-"
                        + cpfCliente.substring(9, 11);

                // Adiciona "." e "-" na string cpf
                cpfFuncionario = cpfFuncionario.substring(0, 3) + "."
                        + cpfFuncionario.substring(3, 6) + "."
                        + cpfFuncionario.substring(6, 9) + "-"
                        + cpfFuncionario.substring(9, 11);

                dados.add(new Object[]{idVenda, cpfCliente, cpfFuncionario,
                    nomeVenda, valorVenda});
            }
            bancoDados.desconectar();
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Erro de SQL - " + e.getMessage());

        } finally {
            bancoDados.desconectar();
        }
        colunas = new String[]{"ID", "CPF Cliente", "CPF Funcionario", "Venda",
            "Valor"};
        modeloTabela = new Tabela(dados, colunas);

        return modeloTabela;
    }

    /**
     * Seleciona uma coluna de uma tabela no DB
     * @param sql o parametro para a pesquisa no DB
     * @param coluna a coluna que será retornada do DB
     * @return um ArrayList de Strings com a coluna pesquisada
     */
    public ArrayList<String> getColunaSQL(String sql, String coluna) {

        try {
            bancoDados = new ConectaBanco();
            bancoDados.conectar();
            consulta = new ArrayList<>();

            bancoDados.executaPesquisaSQL(sql);

            while (bancoDados.getResultado().next()) {

                consulta.add(bancoDados.getResultado().getString(coluna));
            }
            bancoDados.desconectar();
        } catch (SQLException e1) {

            JOptionPane.showMessageDialog(null, "Erro ao preencher o ArrayList da pesquisa simples de SQL!\nERRO: " + e1);
        } finally {
            bancoDados.desconectar();
        }
        return consulta;
    }

    /**
     * Verifica se a senha passada existe no DB
     * @param loginUsuario o cpf do usuário
     * @param senhaUsuario a senha para verificação
     * @throws SenhaInvalidaException se a senha não existir no DB
     */
    public void checarSenha(String loginUsuario, String senhaUsuario) throws SenhaInvalidaException {

        bancoDados = new ConectaBanco();
        bancoDados.conectar();
        bancoDados.executaPesquisaSQL("SELECT cpf_usuario, senha_usuario FROM usuarios "
                + "WHERE cpf_usuario = '" + loginUsuario
                + "' AND senha_usuario = MD5('" + senhaUsuario + "')");

        try {
            if (!bancoDados.getResultado().next()) {

                throw new SenhaInvalidaException();
            }
        } catch (SQLException ex) {

            Logger.getLogger(SistemaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Exclui a linha da tabela selecionada pelos parâmetros
     * @param tabela a tabela de onde será excluida uma linha
     * @param coluna uma coluna que servirá como primeiro parâmetro para seleção
     * @param valor um valor para combinar com a coluna e selecionar a linha
     */
    public void excluirLinha(String tabela, String coluna, String valor) {

        bancoDados = new ConectaBanco();

        // Conecta no Banco de Dados
        bancoDados.conectar();

        // Remove a linha da tabela selecionada pelo cpf do funcionario
        bancoDados.executaAtualizacaoSQL("DELETE FROM " + tabela + " WHERE " + coluna + " = '" + valor + "'");

        // Desconecta do Banco de Dados
        bancoDados.desconectar();
    }
}
