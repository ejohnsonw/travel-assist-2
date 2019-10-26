package co.travelful.microservices.pojo

import co.travelful.microservices.domain.Location

class LocationPojo {

    String name;
    String address;
    String phone;
    String email;
    String website;
    Long cityId;
    Long id;
    Long type;
    Integer whatOrder
    LocationPojo(Location l){
        name = l.name
        address = l.address
        phone = l.phone
        email = l.email
        website = l.website
        cityId = l.cityId
        id = l.id
        whatOrder = l.whatOrder
    }
}
