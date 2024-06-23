package com.dowglasmaia.audittransactionservice.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "TransactionAudit")
public class TransactionDocumentMessage implements Serializable {

    @Id
    private String ID;
    private String userId;
    private String transactionId;
    private String operationType;
    private LocalDateTime dateTime;
}
