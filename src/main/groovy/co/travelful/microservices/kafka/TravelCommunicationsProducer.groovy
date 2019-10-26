package co.travelful.microservices.kafka

import io.micronaut.configuration.kafka.annotation.KafkaClient
import io.micronaut.configuration.kafka.annotation.KafkaKey
import io.micronaut.configuration.kafka.annotation.Topic

@KafkaClient
interface TravelCommunicationsProducer {
    @Topic("send_email")
    void sendCommunication(@KafkaKey String searchId, String data);


    @Topic("booking_ticketing")
    void booking(@KafkaKey String bookingId, String data);
}
