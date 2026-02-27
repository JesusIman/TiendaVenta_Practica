package com.venta.pr.TiendaVenta.service;

import com.venta.pr.TiendaVenta.dto.SucursalDTO;
import com.venta.pr.TiendaVenta.exception.NotFoundException;
import com.venta.pr.TiendaVenta.mapper.Mapper;
import com.venta.pr.TiendaVenta.model.Sucursal;
import com.venta.pr.TiendaVenta.repository.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SucursalService implements ISucursalService {

    @Autowired
    private SucursalRepository repo;

    @Override
    public List<SucursalDTO> traerSucursales() {

        return repo.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public SucursalDTO createSucursal(SucursalDTO sucursaldto) {

        Sucursal suc = Sucursal.builder()
                .nombre(sucursaldto.getNombre())
                .direccion(sucursaldto.getDireccion())
                .build();


        return Mapper.toDTO(repo.save(suc));
    }

    @Override
    public SucursalDTO updateSucursal(Long id, SucursalDTO sucursaldto) {

        //buscamos si existe
        Sucursal suc = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Sucursal No entrada"));

                suc.setNombre(sucursaldto.getNombre());
                suc.setDireccion(sucursaldto.getDireccion());


                return Mapper.toDTO(repo.save(suc));
    }

    @Override
    public void eliminarSucursal(Long id) {

        if (!repo.existsById(id)) {
            throw new NotFoundException("Producto no Encontrado");

        }
        repo.deleteById(id);
    }
}
