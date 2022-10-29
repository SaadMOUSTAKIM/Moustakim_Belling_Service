package enset.ma.moustakimbellingservice.services;

import enset.ma.moustakimbellingservice.dtos.InvoiceRequestDTO;
import enset.ma.moustakimbellingservice.dtos.InvoiceResponseDTO;
import enset.ma.moustakimbellingservice.entities.Customer;
import enset.ma.moustakimbellingservice.entities.Invoice;
import enset.ma.moustakimbellingservice.mappers.InvoiceMapper;
import enset.ma.moustakimbellingservice.openfeign.CustomerRestClient;
import enset.ma.moustakimbellingservice.repositories.InvoiceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceService {
    private InvoiceRepository invoiceRepository;
    private InvoiceMapper invoiceMapper;
    private CustomerRestClient customerRestClient;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, InvoiceMapper invoiceMapper, CustomerRestClient customerRestClient) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceMapper = invoiceMapper;
        this.customerRestClient = customerRestClient;
    }

    @Override
    public InvoiceResponseDTO save(InvoiceRequestDTO invoiceRequestDTO) {
        Invoice invoice=invoiceMapper.fromInvoiceRequestDTO(invoiceRequestDTO);
        invoice.setId(UUID.randomUUID().toString());
        invoice.setDate(new Date());
        Invoice saveInvoice=invoiceRepository.save(invoice);
        return invoiceMapper.fromInvoice(saveInvoice);
    }

    @Override
    public InvoiceResponseDTO getInvoice(String invoiceId) {
        Invoice invoice=invoiceRepository.findById(invoiceId).get();
        Customer customer=customerRestClient.getCustomer(invoice.getCustomerId());
        invoice.setCustomer(customer);
        return invoiceMapper.fromInvoice(invoice);
    }

    @Override
    public List<InvoiceResponseDTO> invoicesByCustomerId(String customerId) {

        List<Invoice> invoices=invoiceRepository.findByCustomerId(customerId);
        return invoices.
                stream()
                .map(invoice->invoiceMapper.fromInvoice(invoice))
                .collect(Collectors.toList());
    }
}
