package co.travelful.microservices.kafka

import co.travelful.common.json.JsonUtilities
import co.travelful.microservices.services.FileCacheService
import co.travelful.microservices.services.TravelAssistService
import grails.gorm.transactions.Transactional
import io.micronaut.configuration.kafka.annotation.KafkaKey
import io.micronaut.configuration.kafka.annotation.KafkaListener
import io.micronaut.configuration.kafka.annotation.OffsetReset
import io.micronaut.configuration.kafka.annotation.Topic
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import javax.inject.Inject
import javax.inject.Singleton
import java.text.SimpleDateFormat

@Singleton
@KafkaListener(offsetReset = OffsetReset.EARLIEST )
public class TripTopicsConsumer {
    private static final Logger LOG = LoggerFactory.getLogger(TripTopicsConsumer.class);

    @Inject
    TravelAssistService travelAssistService


    @Inject
    FileCacheService fileCacheService


    @Transactional
    @Topic("flight_booked")
    Object processBooked(@KafkaKey String key, String value) {

        try{
            //println value
            def data = JsonUtilities.fromJsonString(value)

            if(data instanceof  HashMap){
                return travelAssistService.booking(data);
            }else{

            }
        }catch(Exception ex){
            ex.printStackTrace();
        }


        //Save update cache...
    }

    @Transactional
    @Topic("flight_event")
    Object processTicketed(@KafkaKey String key, String value) {
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd hh:mm")
        try{
            //println value
            def data = JsonUtilities.fromJsonString(value)


        }catch(Exception ex){
            ex.printStackTrace();
        }

        //Save update cache...
    }


//    @Transactional
//    @Topic("flight_delayed")
//    Object processDelayed(@KafkaKey String key, String value) {
//        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd hh:mm")
//        try{
//            //println value
//            def data = JsonUtilities.fromJsonString(value)
//
//
//        }catch(Exception ex){
//            ex.printStackTrace();
//        }
//
//        //Save update cache...
//    }
//
//
//    @Transactional
//    @Topic("flight_cancelled")
//    Object processCancelled(@KafkaKey String key, String value) {
//        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd hh:mm")
//        try{
//            //println value
//            def data = JsonUtilities.fromJsonString(value)
//
//
//        }catch(Exception ex){
//            ex.printStackTrace();
//        }
//
//        //Save update cache...
//    }
//
//    @Transactional
//    @Topic("flight_rebooked")
//    Object processRebooked(@KafkaKey String key, String value) {
//        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd hh:mm")
//        try{
//            //println value
//            def data = JsonUtilities.fromJsonString(value)
//
//
//        }catch(Exception ex){
//            ex.printStackTrace();
//        }
//
//        //Save update cache...
//    }


}
