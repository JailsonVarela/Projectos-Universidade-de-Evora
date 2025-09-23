/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositorios;

import basededados.BaseDeDados;
import entidades.Veiculo;
import entidades.tipos.TipoEstadoAluguer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jailsonlopes
 */
public class RepositorioVeiculos {

    private static RepositorioVeiculos instance;
    private final BaseDeDados baseDeDados;
    private final String tableName = "Veiculo";

    public RepositorioVeiculos() {
        this.baseDeDados = BaseDeDados.getInstance();
    }
    
    public Veiculo salvar (Veiculo veiculo) {
        try {
            PreparedStatement myStmt = this.baseDeDados.createPreparedStatement(
                    String.format("insert into %s (matricula, localizacao_atual, modelo, estado_administrativo, tipo, estado_aluguer) values (?, ?, ?, ?, ?, ?)", tableName),
                    Statement.RETURN_GENERATED_KEYS);
            myStmt.setString(1, veiculo.getMatricula());
            myStmt.setString(2, veiculo.getLocalizacaoAtual());
            myStmt.setString(3, veiculo.getModelo());
            myStmt.setString(4, veiculo.getEstadoAdministrativo().toString());
            myStmt.setString(5, veiculo.getTipo().toString());
            myStmt.setString(6, veiculo.getEstadoAluguer().toString());
            myStmt.executeUpdate();
            return this.consultarPorId(this.baseDeDados.extrairID(myStmt.getGeneratedKeys()));
        } catch (SQLException ex) {
            Logger.getLogger(BaseDeDados.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    public List<Veiculo> listar () {
        try {
            PreparedStatement myStmt = this.baseDeDados.createPreparedStatement(
                    String.format("select * from %s", tableName));
            ResultSet myRs = myStmt.executeQuery();
            return this.baseDeDados.extrairListaDeVeiculos(myRs);
        } catch (SQLException ex) {
            Logger.getLogger(BaseDeDados.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ArrayList<>();
    }
    
    public void editar(Veiculo veiculo) {
        try {
            PreparedStatement myStmt = this.baseDeDados.createPreparedStatement(
                    String.format("update %s set matricula = ?, localizacao_atual = ?, modelo = ?, estado_administrativo = ?, tipo = ?, estado_aluguer = ? where id = ?;", tableName));
            myStmt.setString(1, veiculo.getMatricula());
            myStmt.setString(2, veiculo.getLocalizacaoAtual());
            myStmt.setString(3, veiculo.getModelo());
            myStmt.setString(4, veiculo.getEstadoAdministrativo().toString());
            myStmt.setString(5, veiculo.getTipo().toString());
            myStmt.setString(6, veiculo.getEstadoAluguer().toString());
            myStmt.setInt(7, veiculo.getId());
            myStmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(BaseDeDados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void remover(int id){
        
    }
    
    public Veiculo consultarPorId(int id){
        try {
            PreparedStatement myStmt = this.baseDeDados.createPreparedStatement(
                    String.format("select * from %s where id=?", tableName));
            myStmt.setInt(1, id);
            ResultSet myRs = myStmt.executeQuery();
            return this.baseDeDados.extrairPrimeiroVeiculo(myRs);
        } catch (SQLException ex) {
            Logger.getLogger(BaseDeDados.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
 
    public Veiculo consultaPorMatricula(String matricula){
        try {
            PreparedStatement myStmt = this.baseDeDados.createPreparedStatement(
                    String.format("select * from %s where matricula=?", tableName));
            myStmt.setString(1, matricula);
            ResultSet myRs = myStmt.executeQuery();
            return this.baseDeDados.extrairPrimeiroVeiculo(myRs);
        } catch (SQLException ex) {
            Logger.getLogger(BaseDeDados.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public List<Veiculo> consultaPorDisponibilidadeParaAluguer(TipoEstadoAluguer estadoAluguer){
        try {
            PreparedStatement myStmt = this.baseDeDados.createPreparedStatement(
                    String.format("select * from %s where estado_aluguer=?", tableName));
            myStmt.setString(1, estadoAluguer.label());
            ResultSet myRs = myStmt.executeQuery();
            return this.baseDeDados.extrairListaDeVeiculos(myRs);
        } catch (SQLException ex) {
            Logger.getLogger(BaseDeDados.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static RepositorioVeiculos getInstance() {
        if(instance == null) {
            instance = new RepositorioVeiculos();
        }
        return instance;
    }
    
}
