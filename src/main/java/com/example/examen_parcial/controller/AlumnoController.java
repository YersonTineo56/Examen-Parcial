package com.example.examen_parcial.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.examen_parcial.exception.ResourceNotFoundException;
import com.example.examen_parcial.model.Alumno;
import com.example.examen_parcial.service.AlumnoService;
import com.example.examen_parcial.util.Routes;

import java.util.List;

@RestController
@RequestMapping(Routes.API_ALUMNOS)
public class AlumnoController {

    private static final Logger logger = LoggerFactory.getLogger(AlumnoController.class);

    @Autowired
    private AlumnoService service;

    // Listar todos los alumnos
    @GetMapping
    public ResponseEntity<List<Alumno>> listar() {
        try {
            List<Alumno> alumnos = service.listarTodos();
            if (alumnos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Devuelve 204 si no hay alumnos
            }
            return new ResponseEntity<>(alumnos, HttpStatus.OK); // Devuelve 200 OK con la lista de alumnos
        } catch (Exception e) {
            logger.error("Error al listar alumnos", e); // Log de error
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Devuelve 500 si hay un error
        }
    }

    // Guardar un nuevo alumno
    @PostMapping
    public ResponseEntity<Alumno> guardar(@RequestBody Alumno alumno) {
        try {
            Alumno nuevoAlumno = service.guardar(alumno);
            return new ResponseEntity<>(nuevoAlumno, HttpStatus.CREATED); // Devuelve 201 si se crea el alumno
        } catch (Exception e) {
            logger.error("Error al guardar alumno: " + alumno, e); // Log de error
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Devuelve 500 si hay un error
        }
    }

    // Buscar un alumno por ID
    @GetMapping(Routes.ALUMNO_ID)
    public ResponseEntity<Alumno> buscarPorId(@PathVariable Integer id) {
        try {
            Alumno alumno = service.buscarPorId(id);
            if (alumno == null) {
                throw new ResourceNotFoundException("Alumno no encontrado con ID " + id);
            }
            return new ResponseEntity<>(alumno, HttpStatus.OK); // Devuelve 200 OK si se encuentra el alumno
        } catch (ResourceNotFoundException e) {
            logger.error("Alumno no encontrado con ID " + id, e); // Log de error
            throw e;
        } catch (Exception e) {
            logger.error("Error al buscar alumno con ID " + id, e); // Log de error
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Devuelve 500 si hay un error
        }
    }

    // Eliminar un alumno por ID
    @DeleteMapping(Routes.ALUMNO_ID)
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        try {
            Alumno alumno = service.buscarPorId(id);
            if (alumno == null) {
                throw new ResourceNotFoundException("No se puede eliminar. Alumno no encontrado con ID " + id);
            }
            service.eliminar(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Devuelve 204 si se elimina correctamente
        } catch (ResourceNotFoundException e) {
            logger.error("No se puede eliminar el alumno con ID " + id, e); // Log de error
            throw e;
        } catch (Exception e) {
            logger.error("Error al eliminar alumno con ID " + id, e); // Log de error
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Devuelve 500 si hay un error
        }
    }
}
 