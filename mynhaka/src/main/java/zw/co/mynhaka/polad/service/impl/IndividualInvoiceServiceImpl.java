package zw.co.mynhaka.polad.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import zw.co.mynhaka.polad.domain.dtos.invoice.InvoiceResultDTO;
import zw.co.mynhaka.polad.domain.dtos.invoice.InvoiceUpdateDTO;
import zw.co.mynhaka.polad.domain.model.*;
import zw.co.mynhaka.polad.repository.InvoiceRepository;
import zw.co.mynhaka.polad.service.iface.*;
import zw.co.mynhaka.polad.service.exception.EntityNotFoundException;
import zw.co.mynhaka.polad.service.mapper.invoice.InvoiceToInvoiceResultDTO;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;


@Slf4j
@Service
public class IndividualInvoiceServiceImpl implements IndividualInvoiceService {

    private final ApplicationEventPublisher applicationEventPublisher;
    private final InvoiceToInvoiceResultDTO toInvoiceResultDTO;
    private final InvoiceRepository invoiceRepository;
    private final PolicyHolderService policyHolderService;
    private final PolicySavingsService policySavingsService;
    private final PolicyAccidentService policyAccidentService;
    private final PolicyFuneralService policyFuneralService;
    private final PolicyComprehensiveService policyComprehensiveService;
    private final SavingsProductService savingsProductService;
    private final FuneralProductService funeralProductService;
    private final ComprehensiveProductService comprehensiveProductService;
    private final AccidentProductService accidentProductService;

    public IndividualInvoiceServiceImpl(final ApplicationEventPublisher applicationEventPublisher,
                                        final InvoiceToInvoiceResultDTO toInvoiceResultDTO,
                                        final InvoiceRepository invoiceRepository,
                                        final PolicyHolderService policyHolderService,
                                        final PolicySavingsService policySavingsService,
                                        final PolicyAccidentService policyAccidentService,
                                        final PolicyFuneralService policyFuneralService,
                                        final PolicyComprehensiveService policyComprehensiveService,
                                        final SavingsProductService savingsProductService,
                                        final FuneralProductService funeralProductService,
                                        final ComprehensiveProductService comprehensiveProductService,
                                        final AccidentProductService accidentProductService) {
        this.applicationEventPublisher = applicationEventPublisher;
        this.toInvoiceResultDTO = toInvoiceResultDTO;
        this.invoiceRepository = invoiceRepository;
        this.policyHolderService = policyHolderService;
        this.policySavingsService = policySavingsService;
        this.policyAccidentService = policyAccidentService;
        this.policyFuneralService = policyFuneralService;
        this.policyComprehensiveService = policyComprehensiveService;
        this.savingsProductService = savingsProductService;
        this.funeralProductService = funeralProductService;
        this.comprehensiveProductService = comprehensiveProductService;
        this.accidentProductService = accidentProductService;
    }


    @Override
    public InvoiceResultDTO save(Long id) throws EntityNotFoundException {

        PolicyHolder policyHolder = policyHolderService.getOne(id);

        List<PolicyAccident> accidentPolicies = policyAccidentService.
                findPolicyAccidentByPolicyHolder_Id(id);
//        List<PolicySavings> savingspolicies = policySavingsService.
//                findPolicySavingsByPolicyHolder(policyHolder);
        List<PolicyComprehensive> comprehensivePolicies = policyComprehensiveService.
                findPolicyComprehensiveByPolicyHolder(policyHolder);
        List<PolicyFuneral> funeralPolicies = policyFuneralService.
                findPolicyFuneralByPolicyHolder(policyHolder);


        return null;
    }

    @Override
    public InvoiceResultDTO individualInvoice(Long id) throws EntityNotFoundException {
        return null;
    }

    @Override
    public InvoiceResultDTO save(InvoiceUpdateDTO invoiceUpdateDTO) {
        return null;
    }

    @Override
    public Page<InvoiceResultDTO> findAll(Pageable pageable) {
        return invoiceRepository.findAll(pageable).map(toInvoiceResultDTO::convert);
    }

    @Override
    public Optional<Invoice> findOne(Long id) {
        return invoiceRepository.findById(id);
    }

    @Override
    public Invoice getOne(Long id) {
        return invoiceRepository.getOne(id);
    }

    @Override
    public InvoiceResultDTO getOneDto(Long id) {
        Invoice invoice = invoiceRepository.findById(id).orElseThrow(javax.persistence.EntityNotFoundException::new);
        return toInvoiceResultDTO.convert(invoice);
    }

    @Override
    public void delete(Long id) {
        Invoice invoice = invoiceRepository.findById(id).orElseThrow(javax.persistence.EntityNotFoundException::new);
        invoiceRepository.deleteById(id);
    }

    @Override
    public void generateIndividualInvoices(PolicyHolder policyHolder) throws FileNotFoundException, JRException {

    }
}
