package com.venta.pr.TiendaVenta.controller;

import com.venta.pr.TiendaVenta.dto.ProductoDTO;
import com.venta.pr.TiendaVenta.dto.SucursalDTO;
import com.venta.pr.TiendaVenta.service.ISucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/api/sucursales")
public class SucursalController {

    @Autowired
    private ISucursalService sucursalService;

    @GetMapping
    public ResponseEntity<List<SucursalDTO>> traerSucursal (){
        return ResponseEntity.ok(sucursalService.traerSucursales());

    }

    @PostMapping
    public ResponseEntity<SucursalDTO> crearSucursal(@RequestBody SucursalDTO dto){
        SucursalDTO create = sucursalService.createSucursal(dto);

        return  ResponseEntity.created(URI.create("/api/sucursales" + create.getId())).body(create);

    }

    @PutMapping("/{id}")
    public ResponseEntity<SucursalDTO> actualizarSucursal(@PathVariable Long id,
                                                          @RequestBody SucursalDTO dto){
        return ResponseEntity.ok(sucursalService.updateSucursal(id,dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SucursalDTO> eliminarSucursal(@PathVariable Long id){
        sucursalService.eliminarSucursal(id);

        return ResponseEntity.noContent().build();
    }

}
