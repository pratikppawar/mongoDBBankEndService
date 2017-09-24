package com.javaadvent.bootrest.todo;

import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

/**
 * This repository provides CRUD operations for {@link com.javaadvent.bootrest.todo.Todo}
 * objects.
 * @author Petri Kainulainen
 */
interface AttendeeRepository extends Repository<Attendee, String> {

    /**
     * Deletes a Attendee entry from the database.
     * @param deleted   The deleted Attendee entry.
     */
    void delete(Attendee deleted);

    /**
     * Finds all Attendee entries from the database.
     * @return  The information of all Attendee entries that are found from the database.
     */
    List<Attendee> findAll();

    /**
     * Finds the information of a single Attendee entry.
     * @param id    The id of the requested Attendee entry.
     * @return      The information of the found Attendee entry. If no Attendee entry
     *              is found, this method returns an empty {@link java.util.Optional} object.
     */
    Optional<Attendee> findOne(String id);

    /**
     * Saves a new Attendee entry to the database.
     * @param saved The information of the saved Attendee entry.
     * @return      The information of the saved Attendee entry.
     */
    Attendee save(Attendee saved);
}
