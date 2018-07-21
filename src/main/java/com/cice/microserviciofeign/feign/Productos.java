package com.cice.microserviciofeign.feign;

import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.websocket.server.PathParam;
import java.util.List;

@FeignClient(name = "micro-producto")
public interface Productos {
   /* @RequestMapping(name="/get-productos",method=RequestMethod.GET)
    List<Long> getProductos(@PathParam(value = "idUsuario") String idUsuario);
*/
   @RequestMapping(value = "/producto/{idUsuario}",method = RequestMethod.DELETE)
   ResponseEntity<String> eliminarProductoByIdUsuario(Long id);
}
