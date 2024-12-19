package br.com.jamazonasa;

import br.com.jamazonasa.dao.generic.jbc.dao.IProdutoDAO;
import br.com.jamazonasa.dao.generic.jbc.dao.ProdutoDAO;
import br.com.jamazonasa.domain.Produto;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class ProdutoTest {

    @Test
    public void cadastrarTest() throws SQLException {

        IProdutoDAO dao = new ProdutoDAO();

        Produto produto = new Produto();
        produto.setCodigo("1");
        produto.setNome("Pacote de Arroz");

        Integer qtd = dao.cadastrar(produto);
        assertTrue(qtd ==  1);

        Produto produtoBD = dao.consultar(produto.getCodigo());
        assertNotNull(produtoBD);
        assertNotNull(produtoBD.getId());
        assertEquals(produto.getCodigo(), produtoBD.getCodigo());
        assertEquals(produto.getNome(), produtoBD.getNome());

        Integer qtdDel = dao.excluir(produtoBD);
        assertNotNull(qtdDel);

    }

    @Test
    public void buscarTodosTest() throws SQLException {
        IProdutoDAO dao = new ProdutoDAO();

        Produto produto = new Produto();
        produto.setCodigo("1");
        produto.setNome("Pacote de Arroz");
        dao.cadastrar(produto);

        Produto produto2 = new Produto();
        produto2.setCodigo("2");
        produto2.setNome("Pacote de Feijão");
        dao.cadastrar(produto2);

        List<Produto> produtos = dao.buscarTodos();
        assertNotNull(produtos);
        assertTrue(produtos.size() >= 2 );

        produtos.forEach(prod -> System.out.println(prod.getNome()));

    }

    @Test
    public void atualizarTest() throws SQLException {
        IProdutoDAO dao = new ProdutoDAO();

        Produto produto = new Produto();
        produto.setCodigo("1");
        produto.setNome("Pacote de Arroz");
        dao.cadastrar(produto);

        Produto produtoCadastrado = dao.consultar("1");
        assertNotNull(produtoCadastrado);
        System.out.println("Produto cadastrado: " + produtoCadastrado.getNome());

        produto.setNome("Pacote de Arroz Integral");
        Integer qtdAtualizada = dao.atualizar(produto);
        System.out.println("Linhas atualizadas: " + qtdAtualizada);



        Produto produtoAtualizado = dao.consultar("1");
        assertNotNull(produtoAtualizado);
        assertEquals("Pacote de Arroz Integral", produtoAtualizado.getNome());

        assertTrue(qtdAtualizada ==  1);

        Produto produtoBD = dao.consultar("1");
        assertEquals("Pacote de Arroz Integral", produtoBD.getNome());






    }
}
