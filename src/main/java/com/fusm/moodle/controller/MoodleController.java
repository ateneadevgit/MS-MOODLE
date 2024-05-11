package com.fusm.moodle.controller;

import com.fusm.moodle.model.CalendarEvent;
import com.fusm.moodle.model.EventSearch;
import com.fusm.moodle.service.IMoodleService;
import com.fusm.moodle.util.AppRoutes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Clase que expone todos los servicios consumidos de moodle
 * ITSense Inc - Andrea Gómez
 */

@RestController
@RequestMapping(value = AppRoutes.MOODLE_ROUTE)
public class MoodleController {

    @Autowired
    private IMoodleService moodleService;


    /**
     * Obtiene los eventos según el usuario
     * @param eventSearch Modelo que contiene los parámetros de búsqueda para realizar filtros
     * @return eventos
     */
    @PostMapping("/user")
    public ResponseEntity<CalendarEvent> getEventsByUser(
            @RequestBody EventSearch eventSearch
            ) {
        return ResponseEntity.ok(moodleService.getEventsByUser(eventSearch));
    }

}
