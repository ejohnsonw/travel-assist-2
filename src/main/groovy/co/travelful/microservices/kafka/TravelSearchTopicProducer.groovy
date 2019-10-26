package co.travelful.microservices.kafka

import io.micronaut.configuration.kafka.annotation.KafkaClient
import io.micronaut.configuration.kafka.annotation.KafkaKey
import io.micronaut.configuration.kafka.annotation.Topic

@KafkaClient
interface TravelSearchTopicProducer {
    @Topic("flight_search")
    void sendSearch(@KafkaKey String searchId, String data);
}
