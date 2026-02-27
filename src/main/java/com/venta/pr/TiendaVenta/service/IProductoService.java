package com.venta.pr.TiendaVenta.service;

import com.venta.pr.TiendaVenta.dto.ProductoDTO;

import java.util.List;

public interface IProductoService {

    List<ProductoDTO> traerProductos();
    ProductoDTO createProducto(ProductoDTO productodto);
    ProductoDTO updateProducto(Long id, ProductoDTO producctodto);
    void eliminarProducto (Long id);
}
