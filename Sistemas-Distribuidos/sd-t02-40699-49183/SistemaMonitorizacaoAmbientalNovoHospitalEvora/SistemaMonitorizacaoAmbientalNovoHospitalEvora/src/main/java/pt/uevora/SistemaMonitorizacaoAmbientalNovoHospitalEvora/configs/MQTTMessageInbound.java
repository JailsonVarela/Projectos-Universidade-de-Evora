package pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.configs;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.exceptions.EntidadeNaoExistenteException;
import pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.services.MetricasServices;
import pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.services.dto.MetricasDTO;

import java.io.IOException;
import java.util.UUID;

@Configuration
public class MQTTMessageInbound {

    private static final Logger log = LoggerFactory.getLogger(MQTTMessageInbound.class);
    private final MetricasToJSONDeserializer deserializer = new MetricasToJSONDeserializer();
    private final ObjectMapper mapper = new ObjectMapper();

    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageProducer inbound() {
        MqttPahoMessageDrivenChannelAdapter adapter =
                new MqttPahoMessageDrivenChannelAdapter("tcp://broker.mqttdashboard.com:1883",
                        UUID.randomUUID().toString(),
                        "partilha-do-estado-da-sala");
        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(2);
        adapter.setOutputChannel(mqttInputChannel());
        return adapter;
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public MessageHandler handler(MetricasServices metricasServices) {
        return new MessageHandler() {
            @Override
            public void handleMessage(Message<?> message) throws MessagingException {
                try {
                    JsonNode actualObj = mapper.readTree(message.getPayload().toString());
                    MetricasDTO metricas = deserializer.deserializeObject(null, null, null, actualObj);
                    try {
                        metricasServices.createMetricas(metricas);
                    } catch (EntidadeNaoExistenteException ex) {
                        log.info(String.format("Dispositivo IoT de ID %d não está registado. As métricas serão ignoradas.", metricas.getDispositivoIoTId()));
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        };
    }

}
