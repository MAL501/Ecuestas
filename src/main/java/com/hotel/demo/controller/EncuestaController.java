package com.hotel.demo.controller;

import org.springframework.ui.Model;
import com.hotel.demo.entity.Encuesta;
import com.hotel.demo.repository.EncuestaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

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
        Long total=encuestaRepository.count();
        model.addAttribute("encuestas", encuestaRepository.findAll());
        String edadMedia = mediaEdad();
        List porcentajes = procentajeSatisfaccion();
        model.addAttribute("porcentajes", porcentajes);
        model.addAttribute("edadMedia",edadMedia);
        model.addAttribute("total",total);
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
    @PostMapping("/satisfaction")
    public String satisfaction(@RequestParam("satisfaccion") String satisfaccion, Model model){
        List<Encuesta> filtrado= new ArrayList<>();
        AtomicInteger cantidadAtom=new AtomicInteger(0);
        int cantidad=0;
        encuestaRepository.findAll().forEach(encuesta -> {
            if(encuesta.getSatisfaccion().equals(satisfaccion)){
                filtrado.add(encuesta);
                cantidadAtom.incrementAndGet();
            }
        });
        cantidad=cantidadAtom.get();
        model.addAttribute("cantidad",cantidad);
        model.addAttribute("filtrado", filtrado);
        return "satisfaction-filtred";
    }
    public String mediaEdad(){
        AtomicInteger totalEdad=new AtomicInteger(0);
        double cant=0;
        double media = 0;
        double cuenta=encuestaRepository.count();
        String ret="";
        encuestaRepository.findAll().forEach(encuesta -> {
            totalEdad.addAndGet(encuesta.getEdad());
        });
        cant=totalEdad.get();
        media=cant*cuenta;
        ret=Double.toString(media);
        return ret;
    }
    public List<String> procentajeSatisfaccion(){
        List<Encuesta> encuestas = encuestaRepository.findAll();
        List<String> ret= new ArrayList<String>();
        double total=encuestaRepository.count();
        double []cants=new double[5];
        cants[0]=0;
        cants[1]=0;
        cants[2]=0;
        cants[3]=0;
        cants[4]=0;
        encuestaRepository.findAll().forEach(encuesta -> {
           switch (encuesta.getSatisfaccion()){
               case "muy-satisfecho":
                   cants[0]++;
                   break;
               case "satisfecho":
                   cants[1]++;
                   break;
               case "neutral":
                   cants[2]++;
                   break;
               case "insatisfecho":
                   cants[3]++;
                   break;
               case "muy-insatisfecho":
                   cants[4]++;
                   break;
           }
        });
        //Calcular cantidad de las medias
        ret.add("El porcentaje de usuarios muy satisfechos es: "+String.valueOf((cants[0]*total/100))+"%");
        ret.add("El porcentaje de usuarios satisfechos es: "+String.valueOf((cants[1]*total/100))+"%");
        ret.add("El porcentaje de usuarios neutrales es: "+String.valueOf((cants[2]*total/100))+"%");
        ret.add("El porcentaje de usuarios insatisfechos es: "+String.valueOf((cants[3]*total/100))+"%");
        ret.add("El porcentaje de usuarios muy insatisfechos es: "+String.valueOf((cants[4]*total/100))+"%");
        return ret;
    }
}
