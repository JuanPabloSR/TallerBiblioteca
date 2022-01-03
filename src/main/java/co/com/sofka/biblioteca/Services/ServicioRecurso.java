package co.com.sofka.biblioteca.Services;

import co.com.sofka.biblioteca.DTOs.RecursoDTO;
import co.com.sofka.biblioteca.Mappers.RecursoMapper;
import co.com.sofka.biblioteca.Utils.Mensaje;
import co.com.sofka.biblioteca.Models.Recurso;
import co.com.sofka.biblioteca.Repositories.RepositorioRecurso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class ServicioRecurso {

    @Autowired
    RepositorioRecurso repositorioRecurso;
    RecursoMapper mapper = new RecursoMapper();

    public RecursoDTO encontrarPorId(String id){
        Recurso recurso = repositorioRecurso.findById(id).orElseThrow(() -> new RuntimeException("Recurso no encontrado"));
        return mapper.fromCollection(recurso);
    }

    public List<RecursoDTO> buscarTodos() {
        List<Recurso> recurso = repositorioRecurso.findAll();
        return mapper.fromCollectionList(recurso);
    }

    public void borrarRecursoPorId(String id) {
        repositorioRecurso.deleteById(id);
    }

    public Mensaje comprobarDisponibilidad(String id){
        RecursoDTO recursoDTO = encontrarPorId(id);
        return new Mensaje().imprimirMensajeDisponibilidad(recursoDTO.isDisponible(), recursoDTO.getFechaPrestamo());
    }

    public Mensaje prestarUnRecurso(String id){
        RecursoDTO recursoDTO = encontrarPorId(id);
        Mensaje mensaje = new Mensaje().imprimirMensajePrestamo(recursoDTO.isDisponible(), recursoDTO.getFechaPrestamo());

        if (recursoDTO.isDisponible()){
            recursoDTO.setDisponible(false);
            recursoDTO.setFechaPrestamo(new Date());
            Recurso recurso = mapper.fromDTO(recursoDTO);
            mapper.fromCollection(repositorioRecurso.save(recurso));
        }
        return mensaje;
    }

    public Mensaje devolverUnRecurso(String id){
        RecursoDTO recursoDTO = encontrarPorId(id);
        Mensaje mensaje = new Mensaje().imprimirMensajeDevolucion(recursoDTO.isDisponible(), recursoDTO.getFechaPrestamo());

        if (!recursoDTO.isDisponible()){
            recursoDTO.setDisponible(true);
            recursoDTO.setFechaPrestamo(null);
            Recurso recurso = mapper.fromDTO(recursoDTO);
            mapper.fromCollection(repositorioRecurso.save(recurso));
        }
        return mensaje;
    }

    public RecursoDTO crear(RecursoDTO recursoDTO){
        Recurso recurso = mapper.fromDTO(recursoDTO);
        return mapper.fromCollection(repositorioRecurso.save(recurso));
    }

    public List<RecursoDTO> encontrarPorAreaTematica(String area){
        List<Recurso> recursos = repositorioRecurso.findByAreaTematica(area);
        return mapper.fromCollectionList(recursos);
    }

    public List<RecursoDTO> encontrarPorTipo(String tipo){
        List<Recurso> recursos = repositorioRecurso.findByTipo(tipo);
        return mapper.fromCollectionList(recursos);
    }

    public List<RecursoDTO> encontrarPorAreaTematicaYTipo(String area, String tipo){
        List<Recurso> recursos = repositorioRecurso.findByAreaTematicaAndTipo(area, tipo);
        return mapper.fromCollectionList(recursos);
    }
}