package com.example.examen_parcial.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.examen_parcial.model.Alumno;
import com.example.examen_parcial.repository.AlumnoRepository;

@Service
public class AlumnoService {

    private static final Logger logger = LoggerFactory.getLogger(AlumnoService.class);

    @Autowired
    private AlumnoRepository repository;

    // Listar todos los alumnos
    public List<Alumno> listarTodos() {
        return repository.findAll();
    }

    public Alumno guardar(Alumno alumno) {
        try {
            logger.info("Guardando alumno: " + alumno.getNombre());
            return repository.save(alumno);
        } catch (Exception e) {
            logger.error("Error al guardar el alumno: " + alumno.getNombre(), e);
            throw new RuntimeException("Error al guardar el alumno", e);
        }
    }

    public Alumno buscarPorId(Integer id) {
        logger.info("Buscando alumno con ID: " + id);
        return repository.findById(id).orElse(null);
    }

    public void eliminar(Integer id) {
        try {
            logger.info("Eliminando alumno con ID: " + id);
            repository.deleteById(id);
        } catch (Exception e) {
            logger.error("Error al eliminar el alumno con ID: " + id, e);
            throw new RuntimeException("Error al eliminar el alumno", e);
        }
    }

}
