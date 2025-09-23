/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package basededados;

import entidades.Aluguer;
import entidades.Cliente;
import entidades.Veiculo;
import entidades.tipos.TipoEstadoAdministrativo;
import entidades.tipos.TipoEstadoAluguer;
import entidades.tipos.TipoVeiculo;
import java.sql.*;
import java.util.Properties;
import java.io.FileInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author jailsonlopes
 */
public class BaseDeDados {
    
    private static BaseDeDados instance;
    private final Connection connection;
    
    public BaseDeDados() throws Exception {
        this.connection = createConnection();
    }
    
    private Connection createConnection() throws Exception {
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String appConfigPath = rootPath + "../../db.properties";
        Properties appProps = new Properties();
        appProps.load(new FileInputStream(appConfigPath));
        Connection conn = DriverManager.getConnection(appProps.getProperty("db.url"), 
            appProps.getProperty("db.username"), appProps.getProperty("db.password"));
        return conn;
    }
    
    public PreparedStatement createPreparedStatement(String query) throws SQLException {
        return this.connection.prepareStatement(query);
    }
    
    public PreparedStatement createPreparedStatement(String query, int statementType) throws SQLException {
        return this.connection.prepareStatement(query, statementType);
    }
    
    public int extrairID(ResultSet rs) {
        try {
            if(rs.next())
                return rs.getInt(1);
        }catch(SQLException e) {
            Logger.getLogger(BaseDeDados.class.getName()).log(Level.SEVERE, null, e);
        }
        return 0;
    }
    
    public Cliente extrairPrimeiroCliente(ResultSet rs) {
        Cliente cliente = null;
         try {
            if(rs.next()) {
                cliente = new Cliente(rs.getString("nome"), rs.getString("documento_identificacao"), rs.getString("contacto"));
                cliente.setId(rs.getInt("id"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseDeDados.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cliente;
    }
    
    public Veiculo extrairPrimeiroVeiculo(ResultSet rs) {
        Veiculo veiculo = null;
        try {
            if(rs.next()) {
                veiculo = new Veiculo();
                veiculo.setMatricula(rs.getString("matricula"));
                veiculo.setModelo(rs.getString("modelo"));
                veiculo.setLocalizacaoAtual(rs.getString("localizacao_atual"));
                veiculo.setEstadoAdministrativo(TipoEstadoAdministrativo.valueOf(rs.getString("estado_administrativo")));
                veiculo.setEstadoAluguer(TipoEstadoAluguer.valueOf(rs.getString("estado_aluguer")));
                veiculo.setTipo(TipoVeiculo.valueOf(rs.getString("tipo")));
                veiculo.setId(rs.getInt("id"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseDeDados.class.getName()).log(Level.SEVERE, null, ex);
        }
        return veiculo;
    }
    
    public ResultSet consultarPorId(String tableName, int id){
        try {
            PreparedStatement myStmt = this.createPreparedStatement(
                    String.format("select * from %s where id=?", tableName));
            myStmt.setInt(1, id);
            ResultSet myRs = myStmt.executeQuery();
            return myRs;
        } catch (SQLException ex) {
            Logger.getLogger(BaseDeDados.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Aluguer extrairPrimeiroAluguer(ResultSet rs) {
        Aluguer aluguer = null;
        try {
            if(rs.next()) {
                aluguer = new Aluguer();
                aluguer.setInicioALuguer(rs.getDate("data_inicio"));
                aluguer.setDuracaoPrevista(rs.getInt("duracao_prevista"));
                aluguer.setValorPagamento(rs.getFloat("valor_acordado"));
                aluguer.setCliente(this.extrairPrimeiroCliente(this.consultarPorId("Cliente", rs.getInt("cliente_id"))));
                aluguer.setVeiculo(this.extrairPrimeiroVeiculo(this.consultarPorId("Veiculo", rs.getInt("veiculo_id"))));
                aluguer.setId(rs.getInt("id"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseDeDados.class.getName()).log(Level.SEVERE, null, ex);
        }
        return aluguer;
    }
    
    public List<Cliente> extrairListaDeClientes(ResultSet rs) {
        List<Cliente> clients = new ArrayList<>();
        while(true) {
            Cliente cliente = this.extrairPrimeiroCliente(rs);
            if(cliente == null) break;
            clients.add(cliente);
        }
        return clients;
    }
    
    public List<Veiculo> extrairListaDeVeiculos(ResultSet rs) {
        List<Veiculo> veiculos = new ArrayList<>();
        while(true) {
            Veiculo veiculo = this.extrairPrimeiroVeiculo(rs);
            if(veiculo == null) break;
            veiculos.add(veiculo);
        }
        return veiculos;
    }
    
    public List<Aluguer> extrairListaAluguer(ResultSet rs) {
        List<Aluguer> aluguers = new ArrayList<>();
        while(true) {
            Aluguer aluguer = this.extrairPrimeiroAluguer(rs);
            if(aluguer == null) break;
            aluguers.add(aluguer);
        }
        return aluguers;
    }
    
    public static BaseDeDados getInstance() {
        if(instance == null) {
            try {
                instance = new BaseDeDados();
            } catch (Exception ex) {
                Logger.getLogger(BaseDeDados.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return instance;
    }
}
