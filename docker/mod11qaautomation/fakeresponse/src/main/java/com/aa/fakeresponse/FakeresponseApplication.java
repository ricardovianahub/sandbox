package com.aa.fakeresponse;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class FakeresponseApplication {

    public static void main(String[] args) {
        SpringApplication.run(FakeresponseApplication.class, args);
    }

    @GetMapping(value = "/")
    public String root() {
        return "root";
    }

    @GetMapping(value = "/three", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Data3> three() {
        List<Data3> data3List = new ArrayList<>();
        data3List.add(new Data3("ORDDFW", "Tomatoes", "Mrs", "Carrots", "555-1234"));
        data3List.add(new Data3("ORDDFW", "Tomatoes", "Mrs", "Carrots", "555-1234"));
        data3List.add(new Data3("ORDDFW", "Tomatoes", "Mrs", "Carrots", "555-1234"));
        data3List.add(new Data3("ORDDFW", "Tomatoes", "Mrs", "Carrots", "555-1234"));
        data3List.add(new Data3("ORDDFW", "Tomatoes", "Mrs", "Carrots", "555-1234"));
        data3List.add(new Data3("DFWLAX", "Carrots", "Mr", "", "555-1234"));
        data3List.add(new Data3("LAXYYZ", "Ice Cream", "Miss", "", "555-1234"));
        data3List.add(new Data3("LAXYYZ", "Ice Cream", "Miss", "    ", "555-1234"));
        data3List.add(new Data3("LAXYYZ", "Ice Cream", "Miss", " ", "555-1234"));
        data3List.add(new Data3("LAXYYZ", "Ice Cream", "Miss", "   ", "555-1234"));
        data3List.add(new Data3("LAXYYZ", "Ice Cream", "Miss", "LAX", "555-1234"));

        return data3List;
    }

    @GetMapping(value = "/one", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Data1> one() {
        List<Data1> data1List = new ArrayList<>();
        data1List.add(new Data2("Alice", "Doe", 21, "Mrs"));
        data1List.add(new Data2("John", "Doe", 30, "Mr"));
        data1List.add(new Data2("Jane", "Doe", 28, "Miss"));
        data1List.add(new Data2("Alan", "Smithee", 25, "N/A"));
        data1List.add(new Data2("Bob", "Smithee", 24, "N/A"));
        data1List.add(new Data2("Joe", "Smithee", 25, "N/A"));
        data1List.add(new Data2("Joe", "Schmo", 22, "Mr"));
        data1List.add(new Data2("Julia", "Schmo", 26, "Miss"));
        data1List.add(new Data2("Bob", "Slob", 27, "Mr"));
        data1List.add(new Data2("NEW", "NEW", 99, "N/A"));
        data1List.add(new Data2("No", "Way", 44, "N/A"));
        data1List.add(new Data2("Sergio", "David", 24, "N/A"));
        data1List.add(new Data2("Ricardo", "Test04", 16, "Mr"));

        return data1List;
    }

    public static class Data2 extends Data1 {

        private String salutation;
        private String carrier;

        public Data2(String firstName, String lastName, int age) {
            super(firstName, lastName, age);
            this.salutation = "n/a";
            this.carrier = "AA";
        }

        public Data2(String firstName, String lastName, int age, String salutation) {
            super(firstName, lastName, age);
            this.salutation = salutation;
            this.carrier = "AA";
        }

        public String getSalutation() {
            return salutation;
        }

        public String getCarrier() {
            return carrier;
        }
    }

    public static class Data1 {
        private String firstName;
        private String lastName;
        private int age;

        public Data1(String firstName, String lastName, int age) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public int getAge() {
            return age;
        }

        public Data1 setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Data1 setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Data1 setAge(int age) {
            this.age = age;
            return this;
        }
    }

    public static class Data3 {
        private String itinerary;
        private String origin;
        private String date;
        private String destination;
        private String phone;

        public Data3(String itinerary, String origin, String date, String destination, String phone) {
            this.itinerary = itinerary;
            this.origin = origin;
            this.date = date;
            this.destination = destination;
            this.phone = phone;
        }

        public String getItinerary() {
            return itinerary;
        }

        public Data3 setItinerary(String itinerary) {
            this.itinerary = itinerary;
            return this;
        }

        public String getOrigin() {
            return origin;
        }

        public Data3 setOrigin(String origin) {
            this.origin = origin;
            return this;
        }

        public String getDate() {
            return date;
        }

        public Data3 setDate(String date) {
            this.date = date;
            return this;
        }

        public String getDestination() {
            return destination;
        }

        public Data3 setDestination(String destination) {
            this.destination = destination;
            return this;
        }

        public String getPhone() {
            return phone;
        }

        public Data3 setPhone(String phone) {
            this.phone = phone;
            return this;
        }
    }

}
