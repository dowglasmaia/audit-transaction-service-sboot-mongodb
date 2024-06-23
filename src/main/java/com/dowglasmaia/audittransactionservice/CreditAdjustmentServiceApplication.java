package com.dowglasmaia.audittransactionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class CreditAdjustmentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CreditAdjustmentServiceApplication.class, args);
	}

}
