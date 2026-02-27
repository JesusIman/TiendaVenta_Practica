package com.venta.pr.TiendaVenta.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SucursalDTO {
    private Long id;
    private String nombre;
    private String direccion;
}
