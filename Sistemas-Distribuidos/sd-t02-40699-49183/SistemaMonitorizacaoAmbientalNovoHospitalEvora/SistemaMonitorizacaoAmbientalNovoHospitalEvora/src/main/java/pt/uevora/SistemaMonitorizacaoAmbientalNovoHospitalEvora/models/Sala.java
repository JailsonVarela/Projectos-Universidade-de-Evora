package pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.models;


import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "sala") // nome da tabela na BD
public class Sala {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Id -> chave primária, GeneratedValue -> incremento automático
    private Long id;
    
    @Column(name = "nome", nullable = false) // Column -> coluna unica na BD, nullable = false -> não pode ser nulo na BD
    private String nome;
    //@Name
    
    @Column(name = "piso", nullable = true)
    private int piso; // Para o projeto piso = 2 porque apenas é referido o piso 2 na imagem do enunciado para todas as zonas
    // no entanto deixamos em aberto para futuras alterações na dimenção do projeto.


    @ManyToOne(cascade = CascadeType.DETACH) //ManyToOne -> muitas salas para um edificio
    @JoinColumn(name = "edificio_id", referencedColumnName = "id", nullable =  false) //JoinColumn -> chave estrangeira, refere a chave primária de outra tabela
    private Edificio edificio;

    @OneToMany(cascade = CascadeType.DETACH, mappedBy = "sala") //mappedBy = "localizacao" -> nome do atributo na classe Servico, CascadeType.DETACH -> se apagar uma sala não apaga os serviços
    private List<Servico> servicos;
    
    @OneToMany(cascade = CascadeType.DETACH, mappedBy = "localizacao") //mappedBy = "localizacao" -> nome do atributo na classe DispositivoIoT, CascadeType.DETACH -> se apagar uma sala não apaga os dispositivos
    private List<DispositivoIoT> dispositivos;
    
    // Construtores
    public Sala() {
    }

    public Sala(Long id) {
        this.id = id;
    }

    public Sala(String nome) {
        this.nome = nome;
    }

    public Sala(String nome, int piso) {
        this.nome = nome;
        this.piso = piso;
    }

    public Sala(Long id, String nome, int piso, Edificio edificio) {
        this.id = id;
        this.nome = nome;
        this.piso = piso;
        this.edificio = edificio;
    }

    public Sala(String nome, int piso, Edificio edificio) {
        this.nome = nome;
        this.piso = piso;
        this.edificio = edificio;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

    public Edificio getEdificio() {
        return edificio;
    }

    public void setEdificio(Edificio edificio) {
        this.edificio = edificio;
    }

//    public Servico getServico() {
//        return servico;
//    }

//    public void setServico(Servico servico) {
//        this.servico = servico;
//    }

    public List<DispositivoIoT> getDispositivos() {
        return dispositivos;
    }

    public void setDispositivos(List<DispositivoIoT> dispositivos) {
        this.dispositivos = dispositivos;
    }

    public List<Servico> getServicos() {
        return servicos;
    }

    public void setServicos(List<Servico> servicos) {
        this.servicos = servicos;
    }

}
