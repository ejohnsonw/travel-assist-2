package co.travelful.microservices.domain

import grails.gorm.annotation.Entity
import org.grails.datastore.gorm.GormEntity

@Entity
class Trip implements GormEntity<Trip>{
    String tripId
    String bookingId
    String startDateTime
    String endDateTime
    String name;
    static hasMany = [stages:Stage, locations: Location]
    static  constraints = {
        bookingId nullable: true
        startDateTime nullable: true
        endDateTime nullable: true
    }
    static mapping = {
        stages order: "asc", sort: "whatOrder"
        locations order: "asc", sort: "whatOrder"
    }

}
