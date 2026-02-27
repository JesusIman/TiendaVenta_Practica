package com.venta.pr.TiendaVenta.repository;

import com.venta.pr.TiendaVenta.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductoRepository extends JpaRepository <Producto,Long>{

    //Buscar Producto Por Nombre

    Optional<Producto> findByNombre(String nombre);
}
