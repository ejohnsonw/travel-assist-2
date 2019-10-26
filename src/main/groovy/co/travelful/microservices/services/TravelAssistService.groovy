package co.travelful.microservices.services


import co.travelful.microservices.clients.TravelfulClient
import co.travelful.microservices.domain.Location
import co.travelful.microservices.domain.Trip
import co.travelful.microservices.domain.Stage
import co.travelful.microservices.domain.StageType
import grails.gorm.transactions.Transactional

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class TravelAssistService {
    @Inject
    FileCacheService fileCacheService;

    @Inject
    TravelfulClient travelfulClient;

    @Transactional
    public Trip booking(HashMap data) {
        fileCacheService.save(data.bookingId, JsonUtilities.toStringFromHashmap(data), false)
        Trip t = Trip.findByTripId(data.searchId)
        if (!t) {
            t = new Trip()
            t.tripId = data.searchId
            if (data.event) {
                t.name = data.event.name
            } else {
                t.name = "-"
            }
        }
        t.bookingId = data.booking.bookingId
        int i = 0;
        t.save()


        Stage last;
        int contTrip = 0;
        for (HashMap trip : data.itinerary.trips) {

            //Leaving home or office
            Stage start = findOrCreateTripContext(t, i, "Departure Address: " + trip.origin, StageType.findById(1),"Origin Address")
            start.startDateTime = "--"
            start.endDateTime = "--"
            i++

            if (last) {
                last.next = start;
                start.previous = last;
                last.save()
            }
            //Trip to the airport
            Stage tta1 = findOrCreateTripContext(t, i, "Trip To Airport: " + trip.origin, StageType.findById(2),null)
            tta1.previous = start;
            start.next = tta1;
            start.save()
            i++
            last = tta1;
            for (HashMap segment : trip.segments) {

                Stage departureAirport = findOrCreateTripContext(t, i, "Departure Airport| " + segment.DepartureAirport.LocationCode + "|" + segment.departureDateTime, StageType.findById(3),segment.DepartureAirport.LocationCode)
                if (!departureAirport.endDateTime) {
                    departureAirport.endDateTime = segment.departureDateTime
                }
                departureAirport.startDateTime = segment.departureDateTime.substring(0, 10)
                departureAirport.objectKey = segment.DepartureAirport.LocationCode
                last.next = departureAirport;
                departureAirport.previous = last


                last = departureAirport;
                //Flight
                i++
                Stage flight = findOrCreateTripContext(t, i, "Flight: " + segment.flight, StageType.findById(4),null)
                flight.startDateTime = segment.departureDateTime
                flight.endDateTime = segment.arrivalDateTime
                flight.previous = last
                flight.objectKey = segment.flight
                departureAirport.next = flight;
                departureAirport.save()

                i++;
                Stage arrivalAirport = findOrCreateTripContext(t, i, "Arrival Airport|" + segment.ArrivalAirport.LocationCode + "|" + segment.arrivalDateTime, StageType.findById(3),segment.ArrivalAirport.LocationCode)
                if (!arrivalAirport.startDateTime) {
                    arrivalAirport.startDateTime = segment.arrivalDateTime
                }
                arrivalAirport.objectKey = segment.ArrivalAirport.LocationCode
                last = arrivalAirport;
                flight.next = arrivalAirport
                flight.save()
                arrivalAirport.previous = flight
                arrivalAirport.save()
                i++

            }
            //Trip to the Hotel


            Stage tta2 = findOrCreateTripContext(t, i, "Trip from airport: " + trip.destination, StageType.findById(2),null)
            tta2.startDateTime = last.endDateTime
            tta2.previous = last;

            if (!last.next) {
                last.next = tta2;
                last.save()
            }
            i++

            //Hotel/Airbnb
            Stage end = findOrCreateTripContext(t, i, "Address at Destination " + trip.destination, StageType.findById(1),"Destination Address")
            end.previous = tta2;
            tta2.next = end;
            tta2.save()
            i++
            end.save()
            last = end;
            contTrip++;

        }
        t.save()
        return t;
    }

    public Stage findOrCreateTripContext(Trip t, Integer order, String name, StageType type, String locationKey) {
        Stage stage = Stage.findByTripAndName(t, name)
        if (!stage) {
            stage = new Stage();
            stage.whatOrder = order;
            stage.name = name;
            stage.trip = t;
            stage.type = type;
            if(type.id == 1 || type.id == 3){
                def location = findOrCreateLocation(t,locationKey,order);
                stage.location = location
                t.addToLocations(location)
            }
            stage.save()
            t.addToStages(stage)
        }
        t.addToStages(stage)
        return stage;
    }

    Location findOrCreateLocation(Trip t, String name, Integer whatOrder){
        Location location  = Location.findByTripAndName(t,name)
        if(!location){
            location = new Location()
            location.name = name
            location.trip = t;
            location.whatOrder = whatOrder
            location.save()
        }
        return location;
    }
}
