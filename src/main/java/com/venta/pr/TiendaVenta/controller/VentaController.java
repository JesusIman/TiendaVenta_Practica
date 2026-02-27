package com.venta.pr.TiendaVenta.controller;

import com.venta.pr.TiendaVenta.dto.VentaDTO;
import com.venta.pr.TiendaVenta.service.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/api/ventas")
public class VentaController {

    @Autowired
    private IVentaService ventaService;

    @GetMapping
    public ResponseEntity<List<VentaDTO>> traerVenta (){
        return ResponseEntity.ok(ventaService.traerVenta());
    }

    @PostMapping
    public ResponseEntity<VentaDTO> crearVenta(@RequestBody VentaDTO dto) {
        VentaDTO created = ventaService.createVenta(dto);
        return ResponseEntity.created(URI.create("/api/ventas" + created.getId())).body(dto);
    }

    @PutMapping("/{id}")
    public VentaDTO actualizarVenta(@PathVariable Long id,
                                    @RequestBody VentaDTO dto){
        return ventaService.updateVenta(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<VentaDTO> eliminarVenta (@PathVariable Long id){
        ventaService.eliminarVenta(id);

        return ResponseEntity.noContent().build();

    }
}
