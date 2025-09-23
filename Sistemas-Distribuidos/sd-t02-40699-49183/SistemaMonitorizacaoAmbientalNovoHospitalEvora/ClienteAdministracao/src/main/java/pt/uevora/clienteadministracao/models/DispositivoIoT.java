package pt.uevora.clienteadministracao.models;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DispositivoIoT {

    @SerializedName("id")
    Long id;
    @SerializedName("localizacao")
    Sala localizacao;
    @SerializedName("servico")
    Servico servico;

    public DispositivoIoT() {}

    public DispositivoIoT(Long id) {
        this.id = id;
    }

    public DispositivoIoT(Sala localizacao, Servico servico) {
        this.localizacao = localizacao;
        this.servico = servico;
    }

    public DispositivoIoT(Long id, Sala localizacao, Servico servico) {
        this.id = id;
        this.localizacao = localizacao;
        this.servico = servico;
    }

    @Override
    public String toString() {
        return "DispositivoIoT(ID=" + id +
                ", localizacao=" + localizacao +
                ", servico=" + servico + ")";
    }
}
