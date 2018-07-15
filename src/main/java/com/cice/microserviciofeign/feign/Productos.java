package com.cice.microserviciofeign.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.websocket.server.PathParam;
import java.util.List;

@FeignClient("microproducto")
public interface Productos {
    @RequestMapping(name="/get-productos",method=RequestMethod.GET)
    List<Long> getProductos(@PathParam(value = "idUsuario") String idUsuario);
}
