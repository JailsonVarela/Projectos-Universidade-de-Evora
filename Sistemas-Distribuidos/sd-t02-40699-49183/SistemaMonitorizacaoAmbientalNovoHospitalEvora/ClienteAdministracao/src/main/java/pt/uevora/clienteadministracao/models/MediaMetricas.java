package pt.uevora.clienteadministracao.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MediaMetricas {
    private Double temperaturaMedia;
    private Double humidadeMedia;

    public  MediaMetricas(Double temperaturaMedia, Double humidadeMedia) {
        this.temperaturaMedia = temperaturaMedia;
        this.humidadeMedia = humidadeMedia;
    }

    @Override
    public String toString() {
        return String.format("Temperatura média: %.2f, Humidade média: %.2f", temperaturaMedia, humidadeMedia);
    }
}
