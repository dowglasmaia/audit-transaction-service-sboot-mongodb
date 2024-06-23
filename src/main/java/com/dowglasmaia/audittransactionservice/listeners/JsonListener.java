package com.dowglasmaia.audittransactionservice.listeners;


import com.dowglasmaia.audittransactionservice.document.TransactionDocumentMessage;
import com.dowglasmaia.audittransactionservice.repository.AuditRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class JsonListener {

    private final AuditRepository repository;

    @KafkaListener(
          groupId = "cancel",
          topics = "audit-transaction-topic",
          containerFactory = "jsonContainerFactory"
    )
    @Retryable(
          value = {DataAccessException.class},
          maxAttempts = 3,
          backoff = @Backoff(delay = 2000)
    )
    public void auditListener(@Payload TransactionDocumentMessage transactionMessage){
        log.info("### Transaction Message ###: {}", transactionMessage);

        try {
            repository.save(transactionMessage);
            log.info("### Transaction saved successfully ###: {}", transactionMessage);
        } catch (DataAccessException e) {
            log.error("Error saving transaction message: {}", transactionMessage, e);
            throw e; // rethrow to trigger retry
        } catch (Exception e) {
            log.error("Unexpected error processing transaction message: {}", transactionMessage, e);
        }
    }

}
