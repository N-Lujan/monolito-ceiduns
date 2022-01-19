package com.ceiduns.app.controlador;

import com.ceiduns.app.dominio.Alumno;
import com.ceiduns.app.dominio.Curso;
import com.ceiduns.app.dominio.Matricula;
import com.ceiduns.app.dominio.Pago;
import com.ceiduns.app.servicio.AlumnoService;
import com.ceiduns.app.servicio.CursoService;
import com.ceiduns.app.servicio.MatriculaService;
import com.ceiduns.app.servicio.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/matricula")
public class MatriculaController {
    @Autowired
    private MatriculaService matriculaService;

    @Autowired
    private PagoService pagoService;

    @Autowired
    private CursoService cursoService;

    @Autowired
    private AlumnoService alumnoService;

    @GetMapping("/search")
    public String getAlumnoMatricula(Model model){
        // muestra el cuadro de selección de alumnos
        model.addAttribute("listaAlumnos", alumnoService.listarTodos());
        model.addAttribute("alumno", new Alumno());

        return "matricula/alumnoMatriculaForm";
    }

    @PostMapping("/registro")
    public String formularioMatricula(
            @Valid @ModelAttribute("alumno") Alumno alumno,
            Model model){

        Optional<Alumno> buscado = alumnoService.buscar(alumno.getId());
        if(buscado.isPresent()){
            model.addAttribute("alumno", buscado.get());
            List<Pago> listaPagos = new ArrayList<>();
            for(Pago pago:buscado.get().getPagos()){
                if (pago.getEstado().equals("Disponible"))
                    listaPagos.add(pago);

            }
            model.addAttribute("listaPagos", listaPagos);
        }
        model.addAttribute("listaCursos", cursoService.listarTodos());
        model.addAttribute("matricula",new Matricula());
        return "matricula/formCreate";
    }

    @PostMapping("/create")
    public String guardar(
            @Valid @ModelAttribute Matricula matricula){
        Pago pago = pagoService.buscar(matricula.getPago().getId()).get();
        pago.setEstado("Procesado");
        pagoService.actualizar(pago);
        matriculaService.agregar(matricula);
        return "redirect:/home";
    }
}
