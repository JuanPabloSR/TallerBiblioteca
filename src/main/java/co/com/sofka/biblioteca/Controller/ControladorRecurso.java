package co.com.sofka.biblioteca.Controller;

import co.com.sofka.biblioteca.DTOs.RecursoDTO;
import co.com.sofka.biblioteca.Services.ServicioRecurso;
import co.com.sofka.biblioteca.Utils.Filtro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recursos")
public class ControladorRecurso {

    @Autowired
    ServicioRecurso servicioRecurso;

    @PostMapping("/crear")
    public ResponseEntity<RecursoDTO> create(@RequestBody RecursoDTO recursoDTO) {
        return new ResponseEntity(servicioRecurso.crear(recursoDTO), HttpStatus.CREATED);
    }

    @GetMapping("/disponibilidad/{id}")
    public ResponseEntity<RecursoDTO> mostrarDisponibilidad(@PathVariable("id") String id) {
        return new ResponseEntity(servicioRecurso.comprobarDisponibilidad(id), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<RecursoDTO> mostrarRecursos() {
        return new ResponseEntity(servicioRecurso.buscarTodos(), HttpStatus.OK);
    }

    @PutMapping("/prestar/{id}")
    public ResponseEntity<RecursoDTO> prestar(@PathVariable String id) {
        return new ResponseEntity(servicioRecurso.prestarUnRecurso(id), HttpStatus.OK);

    }
    @PutMapping("/devolver/{id}")
    public ResponseEntity<RecursoDTO> devolver(@PathVariable String id) {
        return new ResponseEntity(servicioRecurso.devolverUnRecurso(id), HttpStatus.OK);
    }

    @GetMapping("/filtrarArea/{area}")
    public ResponseEntity<RecursoDTO> filtrarArea(@PathVariable String area) {
        return new ResponseEntity(servicioRecurso.encontrarPorAreaTematica(area), HttpStatus.OK);
    }

    @GetMapping("/filtrarTipo/{tipo}")
    public ResponseEntity<RecursoDTO> mostrarDisponibilida(@PathVariable String tipo) {
        return new ResponseEntity(servicioRecurso.encontrarPorTipo(tipo), HttpStatus.OK);
    }

    @GetMapping("/filtrarAreaYTipo")
    public ResponseEntity<RecursoDTO> mostrarDisponibilidd(@RequestBody Filtro filtro) {
        return new ResponseEntity(servicioRecurso.encontrarPorAreaTematicaYTipo(filtro.getAreaTematica(), filtro.getTipo()), HttpStatus.OK);
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity delete(@PathVariable("id") String id){
        try {
            servicioRecurso.borrarRecursoPorId(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}