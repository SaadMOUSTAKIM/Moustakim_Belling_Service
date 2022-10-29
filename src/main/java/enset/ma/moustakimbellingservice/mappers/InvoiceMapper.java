package enset.ma.moustakimbellingservice.mappers;

import enset.ma.moustakimbellingservice.dtos.InvoiceRequestDTO;
import enset.ma.moustakimbellingservice.dtos.InvoiceResponseDTO;
import enset.ma.moustakimbellingservice.entities.Invoice;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {
    Invoice fromInvoiceRequestDTO(InvoiceRequestDTO invoiceRequestDTO);
    InvoiceResponseDTO fromInvoice(Invoice invoice);
}
