package co.travelful.microservices.domain

import grails.gorm.annotation.Entity
import org.grails.datastore.gorm.GormEntity


@Entity
class Stage implements  GormEntity<Stage>{
    String name;
    String objectKey;
    Integer whatOrder
    StageType type;
    Location location;
    String startDateTime
    String endDateTime
    String cacheKey;
    Trip trip;
    Stage next
    Stage previous
    static  hasMany = [stages: Stage]
    static constraints = {
        objectKey nullable: true;
        location nullable: true
        startDateTime nullable: true
        endDateTime nullable: true
        cacheKey nullable: true;
        next nullable: true
        previous nullable: true
    }
}
