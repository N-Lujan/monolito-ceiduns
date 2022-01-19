package com.ceiduns.app.controlador;

import com.ceiduns.app.dominio.Aula;
import com.ceiduns.app.servicio.AulaService;
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
@RequestMapping("/aula")
public class AulaController {
    @Autowired
    private AulaService aulaService;

    @GetMapping("/index")
    public String getAula(Model model){
        List<Aula> listadoAula = aulaService.listarTodos();
        model.addAttribute("titulo","Listado de Aulas");
        model.addAttribute("listaAulas",listadoAula);
        return "aula/listar";

    }
    @GetMapping("/create")
    public String crearAula(Model model){
        model.addAttribute("titulo","Datos de Aula");
        model.addAttribute("aula", new Aula());
        return "aula/formCreate";
    }

    @PostMapping("/save")
    public String guardar(
            @Valid @ModelAttribute("aula") Aula aula,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            // Si hubo errores muestra el formulario para corregir
            return "aula/formCreate";
        }

        aulaService.agregar(aula);
        redirectAttributes.addFlashAttribute("flash", "Agregado Correctamente");
        return "redirect:/aula/index";
    }

    @GetMapping("/edit/{id}")
    public String editar(
            @PathVariable("id") Long id,
            Model model){

        model.addAttribute("titulo","Datos de Aula");
        Optional<Aula> buscado = aulaService.buscar(id);
        if(buscado.isPresent()){
            model.addAttribute("aula", buscado.get());
        }else {
            model.addAttribute("aula", new Aula());
        }
        return "aula/formCreate";
    }

    @PostMapping("/edit")
    public String actualizar(
            @Valid @ModelAttribute("aula") Aula aula,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            // Si hubo errores muestra el formulario para corregir
            return "aula/formCreate";
        }

        aulaService.actualizar(aula);
        redirectAttributes.addFlashAttribute("flash", "Actualizado Correctamente");
        return "redirect:/aula/index";
    }

    @GetMapping("/delete/{id}")
    public String eliminar(
            @PathVariable("id") Long id,
            RedirectAttributes redirectAttributes){
        aulaService.eliminar(id);
        redirectAttributes.addFlashAttribute("flash", "Eliminado Correctamente");
        return "redirect:/aula/index";
    }
}
