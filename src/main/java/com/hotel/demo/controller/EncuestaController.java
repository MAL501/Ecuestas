package com.hotel.demo.controller;

import org.springframework.ui.Model;
import com.hotel.demo.entity.Encuesta;
import com.hotel.demo.repository.EncuestaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class EncuestaController {
    private EncuestaRepository  encuestaRepository;

    public EncuestaController(EncuestaRepository repository) {
        this.encuestaRepository = repository;
    }


    @GetMapping("/")
    public String findAll(Model model){
        List<Encuesta> encuestas = encuestaRepository.findAll();
        model.addAttribute("encuestas", encuestas);
        return "redirect:/new";
    }
    @GetMapping("/new")
    public String newEncuesta(Model model){
        model.addAttribute("encuesta", new Encuesta());
        return "form-new";
    }
    //Cuando todo este terminado en vez de redirigir a filtrer, hazlo a success
    @PostMapping("/new")
    public String newForm(Encuesta encuesta, Model model){
        encuestaRepository.save(encuesta);
        model.addAttribute("encuesta",encuesta);
        return "redirect:/filtrer";
    }
    @GetMapping("/filtrer")
    public String filtrer(Model model){
        model.addAttribute("encuestas", encuestaRepository.findAll());
        return "form-filtrer";
    }
    @GetMapping("/view/{id}")
    public String view(@PathVariable Long id,Model model){
        Optional<Encuesta> encuesta= encuestaRepository.findById(id);
        model.addAttribute("encuesta",encuesta.get());
        return "form-view";
    }
    @GetMapping("/del/{id}")
    public String del(@PathVariable Long id){
        encuestaRepository.deleteById(id);
        return "redirect:/filtrer";
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model){
        Optional<Encuesta> encuesta = encuestaRepository.findById(id);
        if(encuesta.isPresent()){
            model.addAttribute("encuesta", encuesta.get());
            return "form-edit";
        }
        return "redirect:/filtrer";
    }
    @PostMapping("/edit/{id}")
    public String editForm(Encuesta encuesta, @PathVariable Long id){
        encuesta.setId(id);
        encuestaRepository.save(encuesta);
        return "redirect:/filtrer";
    }
}
