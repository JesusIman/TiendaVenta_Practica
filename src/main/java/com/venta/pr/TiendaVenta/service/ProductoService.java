package com.venta.pr.TiendaVenta.service;

import com.venta.pr.TiendaVenta.dto.ProductoDTO;
import com.venta.pr.TiendaVenta.exception.NotFoundException;
import com.venta.pr.TiendaVenta.mapper.Mapper;
import com.venta.pr.TiendaVenta.model.Producto;
import com.venta.pr.TiendaVenta.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService implements IProductoService {

    @Autowired
    private ProductoRepository repo;

    @Override
    public List<ProductoDTO> traerProductos() {

        return repo.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public ProductoDTO createProducto(ProductoDTO productodto) {

        Producto prod = Producto.builder()
                .nombre(productodto.getNombre())
                .categoria(productodto.getCategoria())
                .precio(productodto.getPrecio())
                .cantidad(productodto.getCantidad())
                .build();
        return Mapper.toDTO(repo.save(prod));
    }

    @Override
    public ProductoDTO updateProducto(Long id, ProductoDTO productodto) {

        //buscamos si existe
        Producto prod = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Producto no Encontrado"));

                prod.setNombre(productodto.getNombre());
                prod.setCategoria(productodto.getCategoria());
                prod.setCantidad(productodto.getCantidad());
                prod.setPrecio(productodto.getPrecio());

                return Mapper.toDTO(repo.save(prod));
    }

    @Override
    public void eliminarProducto(Long id) {

        if (!repo.existsById(id)) {
            throw new NotFoundException("El Producto no fue Encontrado");
        }
        repo.deleteById(id);

    }
}
