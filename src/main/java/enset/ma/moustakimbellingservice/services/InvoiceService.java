package enset.ma.moustakimbellingservice.services;

import enset.ma.moustakimbellingservice.dtos.InvoiceRequestDTO;
import enset.ma.moustakimbellingservice.dtos.InvoiceResponseDTO;
import enset.ma.moustakimbellingservice.entities.Invoice;

import java.util.List;

public interface InvoiceService {
    InvoiceResponseDTO save(InvoiceRequestDTO invoiceRequestDTO);
    InvoiceResponseDTO getInvoice(String invoiceId);
    List<InvoiceResponseDTO> invoicesByCustomerId(String customerId);
}
