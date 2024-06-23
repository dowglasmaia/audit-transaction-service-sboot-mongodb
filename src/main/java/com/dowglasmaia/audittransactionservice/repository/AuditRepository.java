package com.dowglasmaia.audittransactionservice.repository;

import com.dowglasmaia.audittransactionservice.document.TransactionDocumentMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuditRepository extends MongoRepository<TransactionDocumentMessage, String> {
}
