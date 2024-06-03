package isel.sisinf.model.dto;

import java.time.LocalDateTime;

public class ReservaDTO {
    private String numeroReserva;

    // tirar numero de cliente
    private String numeroCliente;
    private String numerobicicleta;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private double valorPagar;

}
