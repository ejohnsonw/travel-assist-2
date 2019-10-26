package co.travelful.microservices.domain

import grails.gorm.annotation.Entity
import org.grails.datastore.gorm.GormEntity


@Entity
class LocationType implements  GormEntity<LocationType>{
    String name;
}
