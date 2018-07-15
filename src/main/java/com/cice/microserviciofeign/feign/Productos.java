package com.cice.microserviciofeign.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("microproducto")
public interface Productos {
    @RequestMapping("/get-productos")
    Long getProductos(String idUsuario);
}
