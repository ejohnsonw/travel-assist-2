package co.travelful.microservices.controller

import co.travelful.microservices.domain.Trip
import co.travelful.microservices.pojo.TripPojo
import co.travelful.microservices.services.TravelAssistService
import grails.gorm.transactions.Transactional
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post

import javax.inject.Inject

@Controller("/travel-assist/")
class ApiController {

    @Inject
    TravelAssistService travelAssistService;

    @Post("/booking")
    @Transactional
    def TripPojo booking(@Body HashMap data){
        def trip = travelAssistService.booking(data);
        TripPojo tp = new TripPojo(trip)
        return tp;
    }

    @Get("/trip/{bookingId}")
    @Transactional
    def TripPojo booking(String bookingId){
        def trip = Trip.findByBookingId(bookingId);
        TripPojo tp = new TripPojo(trip)
        return tp;
    }
}
