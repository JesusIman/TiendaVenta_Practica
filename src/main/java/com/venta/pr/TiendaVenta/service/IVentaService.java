package com.venta.pr.TiendaVenta.service;

import com.venta.pr.TiendaVenta.dto.VentaDTO;

import java.util.List;

public interface IVentaService {

    List<VentaDTO> traerVenta();
    VentaDTO createVenta (VentaDTO ventadto);
    VentaDTO updateVenta (Long id, VentaDTO ventadto);
    void eliminarVenta (Long id);
}
