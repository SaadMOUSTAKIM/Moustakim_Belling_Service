package enset.ma.moustakimbellingservice.repositories;

import enset.ma.moustakimbellingservice.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice,String> {

    List<Invoice> findByCliendId(String clientdId);
}
