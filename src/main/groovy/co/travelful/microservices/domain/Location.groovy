package co.travelful.microservices.domain

import grails.gorm.annotation.Entity
import org.grails.datastore.gorm.GormEntity


@Entity
class Location  implements  GormEntity<Location>{
    String name;
    String code;
    String address;
    String phone;
    String email;
    String website;
    Long cityId;
    Integer whatOrder
    LocationType type;

    static belongsTo = [trip:Trip]
    static  constraints = {
        address nullable: true
        cityId nullable: true;
        email nullable: true
        website nullable: true;
        phone nullable: true;
        code nullable: true;
    }
}
