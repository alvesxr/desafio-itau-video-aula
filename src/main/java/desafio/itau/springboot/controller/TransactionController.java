package desafio.itau.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import desafio.itau.springboot.dto.TransactionRequest;
import desafio.itau.springboot.model.Transaction;
import desafio.itau.springboot.service.TransactionService;
import jakarta.validation.Valid;

import java.time.OffsetDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/transactions") // Define a rota base para este controlador
public class TransactionController {
    
    private final TransactionService transactionService; //final significa que essa variável não pode ser alterada depois de inicializada

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService; //Injeção de dependência do TransactionService
    }   

    @PostMapping
    public ResponseEntity<Void> createTransaction (@Valid @RequestBody TransactionRequest request) {
        if (request.getDataHora().isAfter(OffsetDateTime.now()) || request.getValor() <= 0) {
            return ResponseEntity.unprocessableEntity().build(); //Retorna 422 se a dataHora for no futuro
        }
        transactionService.addTransaction(new Transaction(request.getValor(), request.getDataHora()));
        return ResponseEntity.status(HttpStatus.CREATED).build(); //Retorna 201 Created se a transação for adicionada com sucesso
    }
    
    @DeleteMapping
    public ResponseEntity<Void> clearTransactions() {
        transactionService.clearTransactions(); //Limpa todas as transações
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); //Retorna 204 No Content se a limpeza for bem-sucedida
    }

}
