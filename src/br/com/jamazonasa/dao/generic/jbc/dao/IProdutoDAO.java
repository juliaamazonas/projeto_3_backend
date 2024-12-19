package br.com.jamazonasa.dao.generic.jbc.dao;

import br.com.jamazonasa.domain.Produto;

import java.sql.SQLException;
import java.util.List;

public interface IProdutoDAO {

    public Integer cadastrar(Produto produto) throws SQLException;


    Produto consultar(String codigo) throws SQLException;

    Integer excluir(Produto produto) throws SQLException;

    List<Produto> buscarTodos() throws SQLException;

    Integer atualizar(Produto produto) throws SQLException;
}
