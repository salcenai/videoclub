package org.example.videoclub.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/registro")
    public String registro(){
        return "registro";
    }

    @GetMapping("/busqueda")
    public String busqueda(){
        return "busqueda";
    }

    @GetMapping("/perfil")
    public String perfil(){
        return "perfil";
    }

    @GetMapping("/pelicula")
    public String pelicula(){
        return "pelicula";
    }

    @GetMapping("/nuevaPelicula")
    public String nuevaPelicula(){
        return "nuevaPelicula";
    }

}
