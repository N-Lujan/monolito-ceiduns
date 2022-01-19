package com.ceiduns.app.controlador;

import com.ceiduns.app.componente.beans.TipoAlumno;
import com.ceiduns.app.dominio.Alumno;
import com.ceiduns.app.servicio.AlumnoService;
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
@RequestMapping("/alumno")
public class AlumnoController {
    @Autowired
    private AlumnoService alumnoService;

    @Autowired
    private List<TipoAlumno> tipoAlumnos;

    @GetMapping("/index")
    public String getAlumno(Model model){
        List<Alumno> listadoAlumnos = alumnoService.listarTodos();
        model.addAttribute("listaAlumnos",listadoAlumnos);
        return "alumno/listar";

    }

    @GetMapping("/create")
    public String crearAlumno(Model model){
        model.addAttribute("tipoAlumnos",tipoAlumnos);
        model.addAttribute("alumno", new Alumno());
        return "alumno/formCreate";
    }

    @PostMapping("/save")
    public String guardar(
            @Valid @ModelAttribute("alumno") Alumno alumno,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            // Si hubo errores muestra el formulario para corregir
            return "alumno/formCreate";
        }

        alumnoService.agregar(alumno);
        redirectAttributes.addFlashAttribute("flash", "Agregado Correctamente");
        return "redirect:/alumno/index";
    }

    @GetMapping("/edit/{id}")
    public String editar(
            @PathVariable("id") Long id,
            Model model){

        model.addAttribute("titulo","Formulario Editar Alumno");
        Optional<Alumno> buscado = alumnoService.buscar(id);
        if(buscado.isPresent()){
            model.addAttribute("alumno", buscado.get());
        }else {
            model.addAttribute("alumno", new Alumno());
        }
        model.addAttribute("tipoAlumnos",tipoAlumnos);
        return "alumno/formCreate";
    }

    @PostMapping("/edit")
    public String actualizar(
            @Valid @ModelAttribute("alumno") Alumno alumno,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            // Si hubo errores muestra el formulario para corregir
            return "alumno/formCreate";
        }

        alumnoService.actualizar(alumno);
        redirectAttributes.addFlashAttribute("flash", "Actualizado Correctamente");
        return "redirect:/alumno/index";
    }

    @GetMapping("/delete/{id}")
    public String eliminar(
            @PathVariable("id") Long id,
            RedirectAttributes redirectAttributes){
        alumnoService.eliminar(id);
        redirectAttributes.addFlashAttribute("flash", "Eliminado Correctamente");
        return "redirect:/alumno/index";
    }
}
