/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositorios;

import entidades.Aluguer;
import basededados.BaseDeDados;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jailsonlopes
 */
public class RepositorioAlugueres {

    private static RepositorioAlugueres instance;
    private final BaseDeDados baseDeDados;
    private final String tableName = "Aluguer";

    public RepositorioAlugueres() {
        this.baseDeDados = BaseDeDados.getInstance();
    }
    
    public Aluguer salvar (Aluguer aluguer) {
        try {
            PreparedStatement myStmt = this.baseDeDados.createPreparedStatement(
                    String.format("insert into %s (data_inicio, duracao_prevista, valor_acordado, cliente_id, veiculo_id) values (?, ?, ?, ?, ?)", tableName),
                    Statement.RETURN_GENERATED_KEYS);
            myStmt.setObject(1,  aluguer.getInicioALuguer().toInstant()               
                                .atZone( ZoneId.of("Africa/Maputo" ) )  
                                .toLocalDate() );
            myStmt.setInt(2, aluguer.getDuracaoPrevista());
            myStmt.setFloat(3, aluguer.getValorPagamento());
            myStmt.setInt(4, aluguer.getCliente().getId());
            myStmt.setInt(5, aluguer.getVeiculo().getId());
            myStmt.executeUpdate();
            return this.consultarPorId(this.baseDeDados.extrairID(myStmt.getGeneratedKeys()));
        } catch (SQLException ex) {
            Logger.getLogger(BaseDeDados.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public List<Aluguer> listar () {
        try {
            PreparedStatement myStmt = this.baseDeDados.createPreparedStatement(
                    String.format("select * from %s", tableName));
            ResultSet myRs = myStmt.executeQuery();
            return this.baseDeDados.extrairListaAluguer(myRs);
        } catch (SQLException ex) {
            Logger.getLogger(BaseDeDados.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ArrayList<>();
    }
    
    public void editar(Aluguer aluguer) {
        try {
            PreparedStatement myStmt = this.baseDeDados.createPreparedStatement(
                    String.format("update %s set data_inicio = ?, duracao_prevista = ?, valor_acordado = ?, cliente_id = ?, veiculo_id = ? where id = ?;", tableName));
            myStmt.setObject(1,  aluguer.getInicioALuguer().toInstant()               
                                .atZone( ZoneId.of("Africa/Maputo" ) )  
                                .toLocalDate() );
            myStmt.setInt(2, aluguer.getDuracaoPrevista());
            myStmt.setDouble(3, aluguer.getValorPagamento());
            myStmt.setInt(4, aluguer.getCliente().getId());
            myStmt.setInt(5, aluguer.getVeiculo().getId());
            myStmt.setInt(6, aluguer.getId());
            myStmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(BaseDeDados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void remover(int id){
        
    }
    
    public Aluguer consultarPorId(int id){
        try {
            PreparedStatement myStmt = this.baseDeDados.createPreparedStatement(
                    String.format("select * from %s where id=?", tableName));
            myStmt.setInt(1, id);
            ResultSet myRs = myStmt.executeQuery();
            return this.baseDeDados.extrairPrimeiroAluguer(myRs);
        } catch (SQLException ex) {
            Logger.getLogger(BaseDeDados.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Aluguer consultarPorClienteId(Integer id) {
       try {
            PreparedStatement myStmt = this.baseDeDados.createPreparedStatement(
                    String.format("select * from %s where cliente=?", tableName, id));
            myStmt.setInt(1, id);
            ResultSet myRs = myStmt.executeQuery();
            return this.baseDeDados.extrairPrimeiroAluguer(myRs);
        } catch (SQLException ex) {
            Logger.getLogger(BaseDeDados.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Aluguer consultarPorVeiculoId(int id){
        try {
            PreparedStatement myStmt = this.baseDeDados.createPreparedStatement(
                    String.format("select * from %s where cliente=?", tableName, id));
            myStmt.setInt(1, id);
            ResultSet myRs = myStmt.executeQuery();
            return this.baseDeDados.extrairPrimeiroAluguer(myRs);
        } catch (SQLException ex) {
            Logger.getLogger(BaseDeDados.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static RepositorioAlugueres getInstance() {
        if(instance == null) {
            instance = new RepositorioAlugueres();
        }
        return instance;
    }
}
