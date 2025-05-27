package desafio.itau.springboot.dto;
import java.time.OffsetDateTime;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
public class TransactionRequest {
    @NotNull //nao pode ser nulo
    @Min(0) //valor minimo 0
    private Double valor;

    @NotNull
    private OffsetDateTime dataHora;

    public Double getValor() {
        return valor;
    }

    public OffsetDateTime getDataHora() {
        return dataHora;
    }
}
