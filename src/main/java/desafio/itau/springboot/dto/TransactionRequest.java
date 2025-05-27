package desafio.itau.springboot.dto;
import java.time.OffsetDateTime;

import jakarta.validation.constraints.NotNull;
public class TransactionRequest {
    @NotNull //nao pode ser nulo
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
