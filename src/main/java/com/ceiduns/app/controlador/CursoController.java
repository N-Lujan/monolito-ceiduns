package com.ceiduns.app.controlador;

import com.ceiduns.app.componente.beans.Ciclo;
import com.ceiduns.app.componente.beans.Idioma;
import com.ceiduns.app.componente.beans.Nivel;
import com.ceiduns.app.dominio.Curso;
import com.ceiduns.app.servicio.AulaService;
import com.ceiduns.app.servicio.CursoService;
import com.ceiduns.app.servicio.HorarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/curso")
public class CursoController {
    @Autowired
    private CursoService cursoService;

    @Autowired
    private HorarioService horarioService;

    @Autowired
    private AulaService aulaService;

    @Autowired
    private List<Ciclo> ciclos;

    @Autowired
    private List<Idioma> idiomas;

    @Autowired
    private List<Nivel> niveles;

    @GetMapping("/index")
    public String getCurso(Model model){
        List<Curso> cursoList = cursoService.listarTodos();
        model.addAttribute("listaCursos",cursoList);
        return "curso/listar";
    }

    @GetMapping("/create")
    public String crearCurso(Model model){
        Curso curso = new Curso();
        model.addAttribute("ciclos",ciclos);
        model.addAttribute("idiomas",idiomas);
        model.addAttribute("niveles",niveles);
        model.addAttribute("curso",curso);
        model.addAttribute("horarios",horarioService.listarTodos());
        model.addAttribute("aulas",aulaService.listarTodos());
        return "curso/formCreate";
    }

    @PostMapping("/save")
    public String guardar(
            @Valid @ModelAttribute Curso curso,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            // Si hubo errores muestra el formulario para corregir
            return "curso/formCreate";
        }
        cursoService.agregar(curso);
        redirectAttributes.addFlashAttribute("flash", "Agregado Correctamente");
        return "redirect:/curso/index";
    }

    @GetMapping("/edit/{id}")
    public String editar(
            @PathVariable("id") Long id,
            Model model){

        Optional<Curso> buscado = cursoService.buscar(id);
        if(buscado.isPresent()){
            model.addAttribute("curso", buscado.get());
        }else {
            model.addAttribute("curso", new Curso());
        }
        model.addAttribute("ciclos",ciclos);
        model.addAttribute("idiomas",idiomas);
        model.addAttribute("niveles",niveles);
        model.addAttribute("horarios",horarioService.listarTodos());
        model.addAttribute("aulas",aulaService.listarTodos());
        return "curso/formCreate";
    }

    @PostMapping("/edit")
    public String actualizar(
            @Valid @ModelAttribute("curso") Curso curso,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            // Si hubo errores muestra el formulario para corregir
            return "curso/formCreate";
        }

        cursoService.actualizar(curso);
        redirectAttributes.addFlashAttribute("flash", "Actualizado Correctamente");
        return "redirect:/curso/index";
    }

    @GetMapping("/delete/{id}")
    public String eliminar(
            @PathVariable("id") Long id,
            RedirectAttributes redirectAttributes){
        cursoService.eliminar(id);
        redirectAttributes.addFlashAttribute("flash", "Eliminado Correctamente");
        return "redirect:/curso/index";
    }

    @GetMapping("/monitorearMatricula")
    public String getMonitorearLista(Model model){
        List<Curso> cursoList = cursoService.listarTodos();
        model.addAttribute("listaCursos",cursoList);
        return "curso/cursoMatriculaIndex";
    }

    @GetMapping("/monitorearMatricula/{id}")
    public String getMonitorearDetalle(
            @PathVariable("id") Long id,
            Model model){

        Optional<Curso> buscado = cursoService.buscar(id);
        if (buscado.isPresent()){
            Curso curso = buscado.get();
            model.addAttribute("scurso",curso);
            model.addAttribute("listaMatriculas",curso.getMatriculas());
        }

        return "curso/cursoMatriculaDetalle";
    }
}
