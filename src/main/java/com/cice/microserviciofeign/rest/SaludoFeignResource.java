package com.cice.microserviciofeign.rest;

import com.cice.microserviciofeign.feign.Saludo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SaludoFeignResource {

    @Autowired
    Saludo saludo;

    @RequestMapping("/get-saludo")
    String getSaludo() {
        return saludo.saludo();
    }

}
