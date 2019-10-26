package co.travelful.microservices.pojo

import co.travelful.microservices.domain.Location
import co.travelful.microservices.domain.Stage
import co.travelful.microservices.domain.Trip

class TripPojo {
    String tripId
    String bookingId
    String startDateTime
    String endDateTime
    String name;
    Long id
    ArrayList<StagePojo> stages = new ArrayList();
    ArrayList<LocationPojo> locations = new ArrayList();
    TripPojo(Trip t){
        tripId = t.tripId
        bookingId = t.bookingId
        startDateTime = t.startDateTime
        endDateTime = t.endDateTime
        name = t.name
        id = t.id
        for(Stage s : t.stages){
            stages.add(new StagePojo(s))
        }

        for(Location s : t.locations){
            locations.add(new LocationPojo(s))
        }
    }
}
