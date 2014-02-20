package modelo;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

/**
 * Modelo de uma tabela
 *
 * @author Pedro Celestino Silveira Junior
 */
public class Tabela extends AbstractTableModel {

    private static final long serialVersionUID = 1L;
    private ArrayList<Object[]> linhas = null;
    private String[] colunas = null;

    /**
     * Contrói uma tabela com seus parâmetros obrigatórios
     *
     * @param dados um vetor de dados
     * @param col a quantidade de colunas da tabela
     */
    public Tabela(ArrayList<Object[]> dados, String[] col) {
        this.setLinhas(dados);
        this.setColunas(col);
    }

    /**
     * Retorna a quantidade de linhas da tabela
     *
     * @return a quantidade de linhas da tabela
     */
    public ArrayList<Object[]> getLinhas() {
        return this.linhas;
    }

    /**
     * Seta o dados nas linhas de uma tabela
     *
     * @param dados os dados que serão inseridos nas linhas da tabela
     */
    private void setLinhas(ArrayList<Object[]> dados) {
        this.linhas = dados;
    }

    /**
     * Retorna o número de colunas da tabela
     *
     * @return o número de colunas da tabela
     */
    public String[] getColunas() {
        return this.colunas;
    }

    /**
     * Seta o nome das colunas da tabela
     *
     * @param nomes os nomes das colunas da tabela
     */
    private void setColunas(String[] nomes) {
        this.colunas = nomes;
    }

    @Override
    public int getColumnCount() {
        return this.colunas.length;
    }

    @Override
    public int getRowCount() {
        return this.linhas.size();
    }

    @Override
    public String getColumnName(int numCol) {
        return this.colunas[numCol];
    }

    @Override
    public Object getValueAt(int numLin, int numCol) {
        Object[] linha = (Object[]) getLinhas().get(numLin);
        return linha[numCol];
    }
}
