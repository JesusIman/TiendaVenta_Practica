package com.venta.pr.TiendaVenta.controller;

import com.venta.pr.TiendaVenta.dto.ProductoDTO;
import com.venta.pr.TiendaVenta.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private IProductoService productoService;

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> traerProducto(){

        return ResponseEntity.ok(productoService.traerProductos());
    }

    @PostMapping
    public ResponseEntity<ProductoDTO> crearProducto (@RequestBody ProductoDTO dto){
        ProductoDTO creado = productoService.createProducto(dto);

        return ResponseEntity.created(URI.create("/api/productos" + creado.getId())).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> actualizarProducto (@PathVariable Long id,
                                                           @RequestBody ProductoDTO dto) {
        return ResponseEntity.ok(productoService.updateProducto(id, dto));
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<ProductoDTO> eliminarProducto (@PathVariable Long id){
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }


}
