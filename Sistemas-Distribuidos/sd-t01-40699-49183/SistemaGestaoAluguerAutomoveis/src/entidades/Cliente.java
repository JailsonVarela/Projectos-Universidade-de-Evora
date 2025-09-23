/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author jailsonlopes
 */
public class Cliente extends EntidadeBase {
    private String nome;
    private String documentoIdentificacao;
    private String contacto;
    private String email;

    public Cliente(int id, String nome, String documentoIdentificacao, String contacto, String email) {
        super(id);
        this.nome = nome;
        this.documentoIdentificacao = documentoIdentificacao;
        this.contacto = contacto;
        this.email = email;
    }

    public Cliente(String nome, String documentoIdentificacao, String contacto, int id) {
        super(id);
        this.nome = nome;
        this.documentoIdentificacao = documentoIdentificacao;
        this.contacto = contacto;
    }

    public Cliente(String nome, String documentoIdentificacao, String contacto) {
        this.nome = nome;
        this.documentoIdentificacao = documentoIdentificacao;
        this.contacto = contacto;
    }

    public Cliente(String nome, String documentoIdentificacao, String contacto, String email) {
        this.nome = nome;
        this.documentoIdentificacao = documentoIdentificacao;
        this.contacto = contacto;
        this.email = email;
    }

    public Cliente() {
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDocumentoIdentificacao(String documentoIdentificacao) {
        this.documentoIdentificacao = documentoIdentificacao;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getNome() {
        return nome;
    }

    public String getDocumentoIdentificacao() {
        return documentoIdentificacao;
    }

    public String getContacto() {
        return contacto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    @Override
    public String toString() {
        return String.format("%s (#%s)", this.nome, this.documentoIdentificacao);
    }
}
