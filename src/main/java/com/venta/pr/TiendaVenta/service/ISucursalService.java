package com.venta.pr.TiendaVenta.service;

import com.venta.pr.TiendaVenta.dto.SucursalDTO;

import java.util.List;

public interface ISucursalService {

    List<SucursalDTO> traerSucursales();
    SucursalDTO createSucursal (SucursalDTO sucursaldto);
    SucursalDTO updateSucursal (Long id, SucursalDTO sucursaldto);
    void eliminarSucursal(Long id);
}
