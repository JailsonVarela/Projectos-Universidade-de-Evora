/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package configuracoes;

import entidades.Cliente;
import entidades.Veiculo;
import entidades.tipos.TipoVeiculo;
import exceptions.EntidadeJaExistenteException;
import exceptions.EntidadeNaoExistenteException;
import exceptions.VeiculoNaoDisponivelException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import servicos.ServicosPrincipaisDoSistema;

/**
 *
 * @author jailsonlopes
 */
public class InicializadorDeDados {
    public static void inicializarDados(ServicosPrincipaisDoSistema servicosPrincipais) {
        try {
            Cliente cliente1 = servicosPrincipais.registoCliente("Thawila Simbine", "1234561-UE", "1234567891");
            Cliente cliente2 = servicosPrincipais.registoCliente("Jerome Lin", "1234562-UL", "1234567892");
            servicosPrincipais.registoCliente("Raia Linda", "1234563-UL", "1234567893");
            servicosPrincipais.registoCliente("Gilmario Vemba", "1234564-UL", "1234567894");
            
            Veiculo veiculo1 = servicosPrincipais.registoNovoVeiculo("123451-PT", "MAZDA", TipoVeiculo.SUV, "Évora, rua da esperança");
            Veiculo veiculo2 = servicosPrincipais.registoNovoVeiculo("123452-PT", "NISSAN", TipoVeiculo.COMPACTO, "Lisboa, rua principal");
            Veiculo veiculo3 = servicosPrincipais.registoNovoVeiculo("123453-PT", "BMW Electron", TipoVeiculo.CAMIONETA, "Porto");
            servicosPrincipais.registoNovoVeiculo("123454-PT", "Tesla", TipoVeiculo.COMPACTO, "Porto");
            servicosPrincipais.aprovarVeiculo(veiculo1.getId());
            servicosPrincipais.aprovarVeiculo(veiculo2.getId());
            servicosPrincipais.aprovarVeiculo(veiculo3.getId());
            
            servicosPrincipais.registoNovoAluguer(veiculo1.getId(), cliente1.getId(), 100, new Date(), 100);
            servicosPrincipais.registoNovoAluguer(veiculo2.getId(), cliente2.getId(), 120, new Date(), 20);
            servicosPrincipais.registoNovoAluguer(veiculo3.getId(), cliente1.getId(), 80, new Date(), 30);
        
        } catch (EntidadeJaExistenteException | 
                VeiculoNaoDisponivelException | 
                EntidadeNaoExistenteException ex) {
            Logger.getLogger(InicializadorDeDados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
