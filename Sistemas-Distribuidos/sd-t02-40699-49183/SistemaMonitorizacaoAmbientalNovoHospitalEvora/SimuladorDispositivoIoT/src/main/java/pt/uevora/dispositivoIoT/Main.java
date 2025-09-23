package pt.uevora.dispositivoIoT;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Main {

    private static final long[] LIMITES_ID_DISPOSITIVOS_IOT = {1L, 50L}; // O ID máximo de um dispositivo IoT
    private static final double[] LIMITES_DE_TEMPERATURA = {0, 45.0}; // Limites de temperaturas aceites pelo sistema
    private static final double[] LIMITES_DE_HUMIDADE = {1, 100}; // Grau de humidade de 1% a 100%

    public static void main(String[] args) throws MqttException {
        Random random = new Random();

        String clientId = UUID.randomUUID().toString();
        String brokerUrl = "tcp://broker.mqttdashboard.com:1883"; // URL de um Broker ativo
        String topic = "partilha-do-estado-da-sala";
        int qos = 2;

        MqttClient mqttClient = new MqttClient(brokerUrl, clientId, new MemoryPersistence());
        MqttConnectOptions connOpts = new MqttConnectOptions();
        connOpts.setCleanSession(true);

        System.out.println("Iniciando o simulador  de Dispositivo IoT...");
        System.out.println("> Estabelecendo conexão com o broker: " + brokerUrl + " ...");
        mqttClient.connect(connOpts);
        System.out.println("> Conexão com o broker estabelecida com sucesso.");
        System.out.printf("> Subscrito no tópico: %s\n", topic);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                System.out.println("Fechando conexão com o broker...");
                mqttClient.disconnect();
                mqttClient.close();
                System.out.println("Conexão com o broker fechada com sucesso...");
            } catch (MqttException ex) {
                System.out.println(ex.toString());
            }
        }));

        try {
            while (true) {
                Mensagem mensagem = new Mensagem(
                        random.nextLong(LIMITES_ID_DISPOSITIVOS_IOT[0], LIMITES_ID_DISPOSITIVOS_IOT[1]),
                        random.nextDouble(LIMITES_DE_TEMPERATURA[0], LIMITES_DE_TEMPERATURA[1]),
                        random.nextDouble(LIMITES_DE_HUMIDADE[0], LIMITES_DE_HUMIDADE[1]),
                        LocalDateTime.now()
                );
                MqttMessage message = new MqttMessage(mensagem.toString().getBytes());
                message.setQos(qos);
                mqttClient.publish(topic, message);
                System.out.printf("Mensagem %s enviada\n", message);
//                Thread.sleep(1000);
                TimeUnit.SECONDS.sleep(1)   ;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
}