package co.travelful.microservices.kafka

import co.travelful.common.json.JsonUtilities
import co.travelful.microservices.services.TravelAssistService
import grails.gorm.transactions.Transactional
import io.micronaut.configuration.kafka.annotation.KafkaKey
import io.micronaut.configuration.kafka.annotation.KafkaListener
import io.micronaut.configuration.kafka.annotation.OffsetReset
import io.micronaut.configuration.kafka.annotation.Topic

import javax.inject.Singleton

@Singleton
@KafkaListener(offsetReset = OffsetReset.EARLIEST)
public class MerchantConsumer {

    TravelAssistService travelAssistService

    MerchantConsumer(TravelAssistService travelAssistService){
        this.travelAssistService = travelAssistService
    }


//    @Transactional
//    @Topic("merchant_order")
//    Object processMerchantOrder(@KafkaKey String bookingId, String value) {
//
//        try{
//            //println value
//            def data = JsonUtilities.fromJsonString(value)
//
//            if(data instanceof  HashMap){
//                    println "BOOKED BY: ${data?.bookedBy}"
//                travelAssistService.processMerchantOrder(bookingId,data)
//            }else{
//                //println "?? ${data}"
//            }
//        }catch(Exception ex){
//            ex.printStackTrace();
//        }
//
//
//        //Save update cache...
//    }



}
