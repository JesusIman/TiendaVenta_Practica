package com.venta.pr.TiendaVenta.mapper;

import com.venta.pr.TiendaVenta.dto.DetalleVentaDTO;
import com.venta.pr.TiendaVenta.dto.ProductoDTO;
import com.venta.pr.TiendaVenta.dto.SucursalDTO;
import com.venta.pr.TiendaVenta.dto.VentaDTO;
import com.venta.pr.TiendaVenta.model.Producto;
import com.venta.pr.TiendaVenta.model.Sucursal;
import com.venta.pr.TiendaVenta.model.Venta;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Mapper {

    //Mapeo de ProductoDTO
    public static ProductoDTO toDTO(Producto p) {
        if (p == null) return null;

        return ProductoDTO.builder()
                .id(p.getId())
                .nombre(p.getNombre())
                .categoria(p.getCategoria())
                .precio(p.getPrecio())
                .cantidad(p.getCantidad())
                .build();
    }

    //Mapeo de SucursalDto
    public  static SucursalDTO toDTO(Sucursal s) {
        if(s == null) return null;

        return SucursalDTO.builder()
                .id(s.getId())
                .nombre(s.getNombre())
                .direccion(s.getDireccion())
                .build();

    }

    //Mapeo de VentaDTO
    public static VentaDTO toDTO(Venta v){
        if (v == null) return null;

        var detalle = v.getDetalle().stream().map( det -> DetalleVentaDTO.builder()
                .id(det.getProd().getId())
                .nombreProd(det.getProd().getNombre())
                .cantProd(det.getCantProd())
                .precio(det.getPrecio())
                .subtotal(det.getPrecio() * det.getCantProd())
                .build()
        ) .collect(Collectors.toList());

        var total = detalle.stream()
                .map(DetalleVentaDTO::getSubtotal)
                .reduce(0.0, Double::sum);

        return VentaDTO.builder()
                .id(v.getId())
                .fecha(v.getFecha())
                .idSucursal(v.getSucursal().getId())
                .estado(v.getEstado())
                .detalle(detalle)
                .total(total)
                .build();
    }
}
