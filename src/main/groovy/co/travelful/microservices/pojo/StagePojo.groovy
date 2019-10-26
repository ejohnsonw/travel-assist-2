package co.travelful.microservices.pojo

import co.travelful.microservices.domain.Location
import co.travelful.microservices.domain.Stage
import co.travelful.microservices.domain.StageType
import co.travelful.microservices.domain.Trip

class StagePojo {
    String name;
    String objectKey;
    Integer whatOrder
    Long typeId;
    String typeName;
    LocationPojo location;
    String startDateTime
    String endDateTime
    String cacheKey;
    Long next
    Long previous
    Long id
    StagePojo(Stage s){
        this.name = s.name
        this.objectKey = s.objectKey
        this.whatOrder = s.whatOrder
        this.typeId = s.type.id
        this.typeName = s.type.name
        if(s.location){
            this.location = new LocationPojo(s.location)
        }

        this.startDateTime = s.startDateTime
        this.endDateTime = s.endDateTime
        this.cacheKey = s.cacheKey
        this.id = s.id
        if(s.next){
           next = s.next.id
        }

        if(s.previous){
            previous = s.previous.id
        }

    }

}
