/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositorios;

import basededados.BaseDeDados;
import entidades.Cliente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jailsonlopes
 */ 
public class RepositorioClientes {

    private static RepositorioClientes instance;
    private final BaseDeDados baseDeDados;
    private final String tableName = "Cliente";

    public RepositorioClientes() {
        this.baseDeDados = BaseDeDados.getInstance();
    }

    
    public Cliente salvar (Cliente cliente) {
        try {
            PreparedStatement myStmt = this.baseDeDados.createPreparedStatement(
                    String.format("insert into %s (nome, documento_identificacao, contacto) values (?, ?, ?)", this.tableName),
                    Statement.RETURN_GENERATED_KEYS);
            myStmt.setString(1, cliente.getNome());
            myStmt.setString(2, cliente.getDocumentoIdentificacao());
            myStmt.setString(3, cliente.getContacto());
            myStmt.executeUpdate();
            return this.consultarPorId(this.baseDeDados.extrairID(myStmt.getGeneratedKeys()));
        } catch (SQLException ex) {
            Logger.getLogger(BaseDeDados.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    public List<Cliente> listar () {
        try {
            PreparedStatement myStmt = this.baseDeDados.createPreparedStatement(
                    String.format("select * from %s", tableName));
            ResultSet myRs = myStmt.executeQuery();
            return this.baseDeDados.extrairListaDeClientes(myRs);
        } catch (SQLException ex) {
            Logger.getLogger(BaseDeDados.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ArrayList<>();
    }
    
    public void editar(Cliente cliente) {
        try {
            PreparedStatement myStmt = this.baseDeDados.createPreparedStatement(
                    String.format("update %s set nome = ?, documento_identificacao = ?, contacto = ? where id = ?;", tableName));
            myStmt.setString(1, cliente.getNome());
            myStmt.setString(2, cliente.getDocumentoIdentificacao());
            myStmt.setString(3, cliente.getContacto());
            myStmt.setInt(4, cliente.getId());
            myStmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(BaseDeDados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void remover(int id){
        
    }
    
    public Cliente consultarPorId(int id){
        try {
            PreparedStatement myStmt = this.baseDeDados.createPreparedStatement(
                    String.format("select * from %s where id=?", tableName));
            myStmt.setInt(1, id);
            ResultSet myRs = myStmt.executeQuery();
            return this.baseDeDados.extrairPrimeiroCliente(myRs);
        } catch (SQLException ex) {
            Logger.getLogger(BaseDeDados.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Cliente consultarPorNome(String nome){
        try {
            PreparedStatement myStmt = this.baseDeDados.createPreparedStatement(
                    String.format("select * from %s where nome=?", tableName));
            myStmt.setString(1, nome);
            ResultSet myRs = myStmt.executeQuery();
            return this.baseDeDados.extrairPrimeiroCliente(myRs);
        } catch (SQLException ex) {
            Logger.getLogger(BaseDeDados.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Cliente consultarPorDocumentoIdentificacao(String documentoIdentificacao){
        try {
            PreparedStatement myStmt = this.baseDeDados.createPreparedStatement(
                    String.format("select * from %s where documento_identificacao=?", tableName));
            myStmt.setString(1, documentoIdentificacao);
            ResultSet myRs = myStmt.executeQuery();
            return this.baseDeDados.extrairPrimeiroCliente(myRs);
        } catch (SQLException ex) {
            Logger.getLogger(BaseDeDados.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null; 
    }
    
    public static RepositorioClientes getInstance() {
        if(instance == null) {
            instance = new RepositorioClientes();
        }
        return instance;
    }
}
