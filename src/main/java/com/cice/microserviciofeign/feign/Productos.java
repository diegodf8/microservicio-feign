package com.cice.microserviciofeign.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "micro-producto")
public interface Productos {
   /* @RequestMapping(name="/get-productos",method=RequestMethod.GET)
    List<Long> getProductos(@PathParam(value = "idUsuario") String idUsuario);
*/
   @RequestMapping(path = "/producto/{idUsuario}",method = RequestMethod.DELETE)
   ResponseEntity<String> eliminarProductoByIdUsuario(@PathVariable("id") Long id);
}
