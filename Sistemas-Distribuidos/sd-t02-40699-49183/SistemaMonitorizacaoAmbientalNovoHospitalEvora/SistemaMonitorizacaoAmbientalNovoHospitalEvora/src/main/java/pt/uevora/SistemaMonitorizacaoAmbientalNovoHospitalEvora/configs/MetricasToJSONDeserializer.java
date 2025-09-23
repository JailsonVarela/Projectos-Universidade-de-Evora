package pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.configs;

import java.io.IOException;
import java.time.LocalDateTime;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;

import org.springframework.boot.jackson.JsonComponent;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.services.dto.MetricasDTO;

@JsonComponent
public class MetricasToJSONDeserializer extends JsonObjectDeserializer<MetricasDTO> {

    @Override
    protected MetricasDTO deserializeObject(JsonParser jsonParser, DeserializationContext context, ObjectCodec codec,
                                            JsonNode tree) throws IOException {
        Long dispositivoIotId = tree.get("dispositivoIoTId").asLong();
        Double temperatura = tree.get("temperatura").asDouble();
        Double humidade = tree.get("humidade").asDouble();
        LocalDateTime timestamp = LocalDateTime.parse(tree.get("timestamp").asText());
        return new MetricasDTO(dispositivoIotId, temperatura, humidade, timestamp);
    }

}