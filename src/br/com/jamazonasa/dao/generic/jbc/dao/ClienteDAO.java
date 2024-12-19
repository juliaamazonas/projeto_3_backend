package br.com.jamazonasa.dao.generic.jbc.dao;

import br.com.jamazonasa.dao.generic.jdbc.ConnectionFactory;
import br.com.jamazonasa.domain.Cliente;
import br.com.jamazonasa.domain.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO implements IClienteDAO {

    @Override
    public Integer cadastrar(Cliente cliente) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        try {
            connection = ConnectionFactory.getConnection();
            String sql = "INSERT INTO TB_CLIENTE_2 (ID, CODIGO, NOME) VALUES (nextval('SQ_CLIENTE_2'),?,?)" +
                    "ON CONFLICT (CODIGO) DO UPDATE SET NOME = EXCLUDED.NOME";;
            stm = connection.prepareStatement(sql);
            stm.setString(1, cliente.getCodigo());
            stm.setString(2, cliente.getNome());
            return stm.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            if (stm != null && !stm.isClosed()) {
                stm.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }


    @Override
    public Cliente buscar(String codigo) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Cliente cliente = null;
        try {
            connection = ConnectionFactory.getConnection();
            String sql = "select * from tb_cliente_2 where codigo = ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, codigo);
            rs = stm.executeQuery();
            if (rs.next()) {
                cliente = new Cliente();
                cliente.setId(rs.getLong("id"));
                cliente.setCodigo(rs.getString("codigo"));
                cliente.setNome(rs.getString("nome"));
            }
            return cliente;
        } catch (Exception e) {
            throw e;
        } finally {
            if (stm != null && !stm.isClosed()) {
                stm.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }


    @Override
    public Integer excluir(Cliente cliente) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        try {
            connection = ConnectionFactory.getConnection();
            String sql = "DELETE FROM TB_CLIENTE_2 WHERE CODIGO = ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, cliente.getCodigo());
            return stm.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            if (stm != null && !stm.isClosed()) {
                stm.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }



    @Override
    public List<Cliente> buscarTodos() throws SQLException {
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<Cliente> clientes = new ArrayList<>();

        try {
            connection = ConnectionFactory.getConnection();
            String sql = "SELECT * FROM TB_CLIENTE_2";
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente();

                cliente.setId(rs.getLong("id"));
                cliente.setCodigo(rs.getString("codigo"));
                cliente.setNome(rs.getString("nome"));
                clientes.add(cliente);

            }

        } catch (Exception e) {
            throw e;

        } finally {
            if (stm != null && !stm.isClosed()) {
                stm.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }

        }


        return clientes;
    }

    @Override
    public Integer atualizar(Cliente cliente) throws SQLException {
        Connection connection = null;
        PreparedStatement stm = null;

        try {
            connection = ConnectionFactory.getConnection();
            String sql = "UPDATE TB_CLIENTE_2 SET NOME = ? WHERE CODIGO =  ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, cliente.getNome());
            stm.setString(2, cliente.getCodigo());

            return stm.executeUpdate();


        } catch (Exception e) {
            throw e;

        } finally {
            if (stm != null && !stm.isClosed()) {
                stm.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }

        }
    }
}

