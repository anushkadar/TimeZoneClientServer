package org.me.dtd.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.me.dtd.service.DateTimeService;
import org.me.dtd.entity.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;

import static java.lang.System.out;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class DateTimeController {
    Logger logger = LogManager.getLogger(DateTimeController.class);

    @Autowired
    DateTimeService dateTimeService;

    @GetMapping(value = "/get")
    public @ResponseBody
    ResponseEntity<Data> get() {
        Data data = new Data("hellow");
        data.setDate(new Date());
        return ResponseEntity.ok(data);
    }

    @PostMapping("/date")
    public @ResponseBody
    ResponseEntity<Date> date(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) {
        System.out.println(java.util.TimeZone.getDefault().getID());
        out.println(date);
        return new ResponseEntity<>(date, HttpStatus.OK);
    }

    @PostMapping("/datetime")
    public @ResponseBody
    ResponseEntity<Date> datetime(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date) {
        System.out.println(java.util.TimeZone.getDefault().getID());
        out.println(date);
        return new ResponseEntity<>(date, HttpStatus.OK);
    }

    @PostMapping("/local-date")
    public ResponseEntity<LocalDate> localDate(
            @RequestParam("localDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate localDate) {
        out.println(localDate);
        logger.debug(localDate);
        return new ResponseEntity<>(localDate, HttpStatus.OK);
    }

    @PostMapping("/local-date-time")
    public ResponseEntity<LocalDateTime> localDateTime(
            @RequestParam("localDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime localDateTime) {
        out.println(localDateTime);
        return new ResponseEntity<>(localDateTime, HttpStatus.OK);
    }

    @GetMapping("/get-local-date-time")
    public ResponseEntity<LocalDateTime> localDateTime2(
            @RequestParam("localDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime localDateTime) {
        out.println(localDateTime);
        return new ResponseEntity<>(localDateTime, HttpStatus.OK);
    }

    @PostMapping("/post-data1")
    public ResponseEntity<Data> saveData1(@RequestBody Data data) {
        out.println(Instant.now());
        out.println(data.toString());
        Data persistData = dateTimeService.saveJpa(data);
        out.println(persistData.toString());
        return new ResponseEntity<>(persistData, HttpStatus.OK);

    }

    @PostMapping("/post-data2")
    public ResponseEntity<Data> saveData2(@RequestBody Data data) {
        out.println(data.toString());
        Data persistData = dateTimeService.saveJdbcTemplate(data);
        out.println(persistData.toString());
        return new ResponseEntity<>(persistData, HttpStatus.OK);
    }

    @PostMapping("/post-data3")
    public ResponseEntity<Data> saveData3(@RequestBody Data data) {
        out.println(data.toString());
        Data persistData = dateTimeService.saveJdbc(data);
        out.println(persistData.toString());
        return new ResponseEntity<>(persistData, HttpStatus.OK);
    }
}
