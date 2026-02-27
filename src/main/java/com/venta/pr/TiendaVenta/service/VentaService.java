package com.venta.pr.TiendaVenta.service;

import com.venta.pr.TiendaVenta.dto.DetalleVentaDTO;
import com.venta.pr.TiendaVenta.dto.VentaDTO;
import com.venta.pr.TiendaVenta.exception.NotFoundException;
import com.venta.pr.TiendaVenta.mapper.Mapper;
import com.venta.pr.TiendaVenta.model.DetalleVenta;
import com.venta.pr.TiendaVenta.model.Producto;
import com.venta.pr.TiendaVenta.model.Sucursal;
import com.venta.pr.TiendaVenta.model.Venta;
import com.venta.pr.TiendaVenta.repository.ProductoRepository;
import com.venta.pr.TiendaVenta.repository.SucursalRepository;
import com.venta.pr.TiendaVenta.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VentaService implements IVentaService {
    @Autowired
    private VentaRepository ventaRepo;
    @Autowired
    private ProductoRepository productoRepo;
    @Autowired
    private SucursalRepository sucursalRepo;

    @Override
    public List<VentaDTO> traerVenta() {

       List<Venta> ventas = ventaRepo.findAll();
       List<VentaDTO> ventasDto = new ArrayList<>();

       VentaDTO dto;
        for (Venta v : ventas) {
            dto = Mapper.toDTO(v);
            ventasDto.add(dto);

        }
        return ventasDto;
    }

    @Override
    public VentaDTO createVenta(VentaDTO ventadto) {

        if (ventadto == null) throw new RuntimeException("VentaDto es null");
        if (ventadto.getIdSucursal() == null) throw new RuntimeException("Debe indicar la sucursal");
        if (ventadto.getDetalle() == null || ventadto.getDetalle().isEmpty())
            throw  new RuntimeException("Debe incluir al menos un Producto");

        //buscar la Sucursal
        Sucursal suc = sucursalRepo.findById(ventadto.getIdSucursal()).orElse(null);
        if (suc == null) {
            throw new NotFoundException("Sucursal no encontrada");
        }
        //Crear venta
        Venta vent = new Venta();
        vent.setFecha(ventadto.getFecha());
        vent.setEstado(ventadto.getEstado());
        vent.setSucursal(suc);
        vent.setTotal(ventadto.getTotal());

        //Lista de detalle
        List<DetalleVenta> detalles = new ArrayList<>();
        Double totalCalculado = 0.0;

        for (DetalleVentaDTO detDTO : ventadto.getDetalle()){
            //buscar producto por nombre
            Producto p = productoRepo.findByNombre(detDTO.getNombreProd()).orElse(null);
            if (p == null)
            {throw new RuntimeException("Producto no encontrado" + detDTO.getNombreProd());}


            //Crear Detalle
            DetalleVenta detalleVent = new DetalleVenta();
                detalleVent.setProd(p);
                detalleVent.setPrecio(detDTO.getPrecio());
                detalleVent.setCantProd(detDTO.getCantProd());
                detalleVent.setVenta(vent);

                detalles.add(detalleVent);

                totalCalculado = totalCalculado+(detDTO.getPrecio() * detDTO.getCantProd());
        }
        //Seteamos la lista de venta
        vent.setDetalle(detalles);
        //Guardamos en la BD
        vent = ventaRepo.save(vent);

        //Mapeo salida
        VentaDTO ventaSalida = Mapper.toDTO(vent);

        return ventaSalida;
    }

    @Override
    public VentaDTO updateVenta(Long id, VentaDTO ventadto) {
        //Buscar si la venta existe
        Venta v = ventaRepo.findById(id).orElse(null);
        if (v == null) throw new RuntimeException("Venta no encontrada");

        if (ventadto.getFecha()!=null){
            v.setFecha(ventadto.getFecha());
        }

        if (ventadto.getEstado()!=null){
            v.setEstado(ventadto.getEstado());
        }

        if (ventadto.getIdSucursal()!=null){
            Sucursal suc = sucursalRepo.findById(ventadto.getIdSucursal()).orElse(null);
            if (suc == null) throw new RuntimeException("Sucursal no encontrada");

            v.setSucursal(suc);
        }

        ventaRepo.save(v);

        VentaDTO ventaSalida = Mapper.toDTO(v);

        return ventaSalida;
    }

    @Override
    public void eliminarVenta(Long id) {

        Venta v = ventaRepo.findById(id).orElse(null);
        if (v == null) throw new RuntimeException("Venta no encontrada");
        ventaRepo.delete(v);


    }
}
