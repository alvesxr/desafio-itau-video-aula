package desafio.itau.springboot.service;

import java.time.OffsetDateTime;
import java.util.DoubleSummaryStatistics;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import org.springframework.stereotype.Service;

import desafio.itau.springboot.model.Transaction;

@Service //Anotação @Service indica que essa classe é um serviço, ou seja, uma camada de lógica de negócio
public class TransactionService {
    //Fila para armazenar transações
    private final Queue<Transaction> transactions = new ConcurrentLinkedDeque<>(); 
    //ConcurrentLinkedDeque é uma implementação de fila concorrente, que permite acesso seguro por múltiplas threads); 
    public void addTransaction(Transaction transaction) {
        //Adiciona uma transação à fila
        transactions.add(transaction);
    }

    public void clearTransactions() {
        //Limpa todas as transações da fila
        transactions.clear();
    }

    public DoubleSummaryStatistics getStatistics() {
        OffsetDateTime now = OffsetDateTime.now(); //nao precisa do new, pois OffsetDateTime.now() já cria uma nova instância com o horário atual
        //Obtém estatísticas de resumo para transações nos últimos 60 segundos
        return transactions.stream()
                //.filter(t -> t.getDataHora().isAfter(now.minusSeconds(60))) //Filtra transações que ocorreram nos últimos 60 segundos
                .mapToDouble(Transaction::getValor) //Mapeia as transações para seus valores
                .summaryStatistics(); //Obtém estatísticas de resumo (contagem, soma, média, mínimo, máximo)
    }

}
