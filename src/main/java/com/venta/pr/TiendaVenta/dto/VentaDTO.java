package com.venta.pr.TiendaVenta.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VentaDTO {
    //datos de la Venta
    private Long id;
    private LocalDate fecha;
    private String estado;

    //datos de la sucursal
    private Long idSucursal;
    //Lista de detalles
    private List<DetalleVentaDTO> detalle;

    //Total de la venta
    private Double total;


}
