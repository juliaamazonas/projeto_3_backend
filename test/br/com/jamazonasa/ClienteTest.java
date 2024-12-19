package br.com.jamazonasa;

import br.com.jamazonasa.dao.generic.jbc.dao.ClienteDAO;
import br.com.jamazonasa.dao.generic.jbc.dao.IClienteDAO;
import br.com.jamazonasa.dao.generic.jbc.dao.IProdutoDAO;
import br.com.jamazonasa.dao.generic.jbc.dao.ProdutoDAO;
import br.com.jamazonasa.domain.Cliente;
import br.com.jamazonasa.domain.Produto;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ClienteTest {

    private IClienteDAO clienteDAO;

    @Test
    public void cadastrarTest() throws Exception {
        clienteDAO = new ClienteDAO();

        Cliente cliente = new Cliente();
        cliente.setCodigo("10");
        cliente.setNome("Rodrigo Pires");

        Integer qtd = clienteDAO.cadastrar(cliente);
        assertTrue(qtd == 1);

        Cliente clienteBD = clienteDAO.buscar("10");
        assertNotNull(clienteBD);
        assertNotNull(clienteBD.getId());
        assertEquals(cliente.getCodigo(), clienteBD.getCodigo());
        assertEquals(cliente.getNome(), clienteBD.getNome());

        Integer qtdDel = clienteDAO.excluir(clienteBD);
        assertNotNull(qtdDel);
    }

    @Test
    public void buscarTodosTest() throws Exception {
        IClienteDAO dao = new ClienteDAO();

        Cliente cliente = new Cliente();
        cliente.setCodigo("1");
        cliente.setNome("Ana Maria");
        dao.cadastrar(cliente);

        Cliente cliente2 = new Cliente();
        cliente2.setCodigo("2");
        cliente2.setNome("Flavia Alessandra");
        dao.cadastrar(cliente2);

        List<Cliente> clientes = dao.buscarTodos();
        assertNotNull(clientes);
        assertTrue(clientes.size() >= 2 );

        clientes.forEach(prod -> System.out.println(cliente.getNome()));

    }

    @Test
    public void atualizarTest() throws Exception {
        IClienteDAO dao = new ClienteDAO();

        Cliente cliente = new Cliente();
        cliente.setCodigo("1");
        cliente.setNome("Ana Maria");
        dao.cadastrar(cliente);

        Cliente clienteCadastrado = dao.buscar("1");
        assertNotNull(clienteCadastrado);
        System.out.println("Cliente cadastrado: " + clienteCadastrado.getNome());

        cliente.setNome("Marcela Moreira");
        Integer qtdAtualizada = dao.atualizar(cliente);
        System.out.println("Linhas atualizadas: " + qtdAtualizada);



        Cliente clienteAtualizado = dao.buscar("1");
        assertNotNull(clienteAtualizado);
        assertEquals("Marcela Moreira", clienteAtualizado.getNome());

        assertTrue(qtdAtualizada ==  1);

        Cliente clienteBD = dao.buscar("1");
        assertEquals("Marcela Moreira", clienteBD.getNome());

    }


}
