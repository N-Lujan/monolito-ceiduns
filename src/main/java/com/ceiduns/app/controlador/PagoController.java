package com.ceiduns.app.controlador;

import com.ceiduns.app.componente.beans.TipoPago;
import com.ceiduns.app.dominio.*;
import com.ceiduns.app.servicio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/pago")
public class PagoController {
    @Autowired
    private PagoService pagoService;

    @Autowired
    private AlumnoService alumnoService;

    @Autowired
    private List<TipoPago> tipoPagos;

    @GetMapping("/search")
    public String getAlumnoPago(Model model){
        // muestra el cuadro de selección de alumnos
        model.addAttribute("listaAlumnos", alumnoService.listarTodos());
        model.addAttribute("alumno", new Alumno());

        return "pago/alumnoPagoForm";
    }

    @PostMapping("/data")
    public String pagosAlumno(
            @Valid @ModelAttribute("alumno") Alumno alumno,
            Model model){

        Optional<Alumno> buscado = alumnoService.buscar(alumno.getId());
        if(buscado.isPresent()){
            model.addAttribute("alumno", buscado.get());
            model.addAttribute("listaPago", buscado.get().getPagos());
        }
        return "pago/listar";
    }

    @GetMapping("/createFor/{id}")
    public String crearPago(
            @PathVariable("id") Long id,
            Model model){

        Pago pago = new Pago();
        Optional<Alumno> buscado = alumnoService.buscar(id);
        Alumno alumno = buscado.get();
        pago.setAlumno(alumno);
        model.addAttribute("pago",pago);
        model.addAttribute("tipoPagos",tipoPagos);
        return "pago/formCreate";
    }

    @PostMapping("/save")
    public String guardar(
            @Valid @ModelAttribute("pago") Pago pago,
            RedirectAttributes redirectAttributes,
            Model model){

        pago.setEstado("Disponible");
        pago.setFechaPago(new Date());
        pagoService.agregar(pago);
        Optional<Pago> temp = pagoService.buscar(pago.getId());

        Alumno alumno = temp.get().getAlumno();
        alumno = alumnoService.buscar(alumno.getId()).get();
        model.addAttribute("alumno",alumno);
        model.addAttribute("listaPago", alumno.getPagos());

        redirectAttributes.addFlashAttribute("flash", "Agregado Correctamente");
        return "pago/listar";
    }
}
