package enset.ma.moustakimbellingservice;

import enset.ma.moustakimbellingservice.dtos.InvoiceRequestDTO;
import enset.ma.moustakimbellingservice.entities.Invoice;
import enset.ma.moustakimbellingservice.services.InvoiceService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
@EnableFeignClients
public class MoustakimBellingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoustakimBellingServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(InvoiceService invoiceService){
        return args -> {
            invoiceService.save(new InvoiceRequestDTO(BigDecimal.valueOf(98000), "A01"));
            invoiceService.save(new InvoiceRequestDTO(BigDecimal.valueOf(50000), "B02"));
            invoiceService.save(new InvoiceRequestDTO(BigDecimal.valueOf(45980), "C01"));
        };
    }
}
