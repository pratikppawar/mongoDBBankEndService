package com.javaadvent.bootrest.todo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.javaadvent.bootrest.util.ExcelTOMongoUtil;

import javax.validation.Valid;
import java.util.List;

/**
 * This controller provides the public API that is used to manage the information
 * of Attendee entries.
 * @author Petri Kainulainen
 */
@RestController
@RequestMapping("/api/user")
final class AttendeeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AttendeeController.class);

    private final AttendeeService service;

    @Autowired
    AttendeeController(AttendeeService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    AttendeeDTO create(@RequestBody @Valid AttendeeDTO AttendeeEntry) {
        LOGGER.info("Creating a new Attendee entry with information: {}", AttendeeEntry);

        AttendeeDTO created = service.create(AttendeeEntry);
        LOGGER.info("Created a new Attendee entry with information: {}", created);

        return created;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    AttendeeDTO delete(@PathVariable("id") String id) {
        LOGGER.info("Deleting Attendee entry with id: {}", id);

        AttendeeDTO deleted = service.delete(id);
        LOGGER.info("Deleted Attendee entry with information: {}", deleted);

        return deleted;
    }

    @RequestMapping(method = RequestMethod.GET)
    List<AttendeeDTO> findAll() {
        LOGGER.info("Finding all Attendee entries");

        List<AttendeeDTO> AttendeeEntries = service.findAll();
        LOGGER.info("Found {} Attendee entries", AttendeeEntries.size());

        return AttendeeEntries;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    AttendeeDTO findById(@PathVariable("id") String id) {
        LOGGER.info("Finding Attendee entry with id: {}", id);

        AttendeeDTO AttendeeEntry = service.findById(id);
        LOGGER.info("Found Attendee entry with information: {}", AttendeeEntry);

        return AttendeeEntry;
    }
    
   

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    String update(@PathVariable("id") String id) {
        LOGGER.info("Updating Attendee entry with information: {}", id);
        ExcelTOMongoUtil.loadDataFromExcel();
        //AttendeeDTO updated = service.update(AttendeeEntry);
        LOGGER.info("Updated Attendee entry with information: {}", id);

        return id;
    }

  /*  @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleAttendeeNotFound(AttendeeNotFoundException ex) {
        LOGGER.error("Handling error with message: {}", ex.getMessage());
    }*/
}
