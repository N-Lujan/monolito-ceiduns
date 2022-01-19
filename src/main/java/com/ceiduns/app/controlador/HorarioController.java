package com.ceiduns.app.controlador;

import com.ceiduns.app.componente.beans.Turno;
import com.ceiduns.app.dominio.Horario;
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
@RequestMapping("/horario")
public class HorarioController {
    @Autowired
    private HorarioService horarioService;

    @Autowired
    private List<Turno> turnos;

    @GetMapping("/index")
    public String getHorario(Model model){
        List<Horario> listaHorario = horarioService.listarTodos();
        model.addAttribute("listHorario",listaHorario);
        return "horario/listar";
    }

    @GetMapping("/create")
    public String crearHorario(Model model){
        Horario horario = new Horario();
        model.addAttribute("listTurnos",turnos);
        model.addAttribute("horario",horario);
        return "horario/formCreate";
    }

    @PostMapping("/save")
    public String guardar(
            @Valid @ModelAttribute Horario horario,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            // Si hubo errores muestra el formulario para corregir
            return "horario/formCreate";
        }
        horarioService.agregar(horario);
        redirectAttributes.addFlashAttribute("flash", "Agregado Correctamente");
        return "redirect:/horario/index";
    }

    @GetMapping("/edit/{id}")
    public String editar(
            @PathVariable("id") Long id,
            Model model){

        Optional<Horario> buscado = horarioService.buscar(id);
        if(buscado.isPresent()){
            model.addAttribute("horario", buscado.get());
        }else {
            model.addAttribute("horario", new Horario());
        }
        model.addAttribute("listTurnos",turnos);
        return "horario/formCreate";
    }

    @PostMapping("/edit")
    public String actualizar(
            @Valid @ModelAttribute Horario horario,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            // Si hubo errores muestra el formulario para corregir
            return "horario/formCreate";
        }
        horarioService.actualizar(horario);
        redirectAttributes.addFlashAttribute("flash", "Actualizado Correctamente");
        return "redirect:/horario/index";
    }

    @GetMapping("/delete/{id}")
    public String eliminar(
            @PathVariable("id") Long id,
            RedirectAttributes redirectAttributes){
        horarioService.eliminar(id);
        redirectAttributes.addFlashAttribute("flash", "Eliminado Correctamente");
        return "redirect:/horario/index";
    }
}
