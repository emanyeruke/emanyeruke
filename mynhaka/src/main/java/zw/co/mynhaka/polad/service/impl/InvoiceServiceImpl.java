package zw.co.mynhaka.polad.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import zw.co.mynhaka.polad.domain.dtos.invoice.InvoiceResultDTO;
import zw.co.mynhaka.polad.domain.dtos.invoice.InvoiceUpdateDTO;
import zw.co.mynhaka.polad.domain.enums.InvoiceStatus;
import zw.co.mynhaka.polad.domain.enums.PolicyType;
import zw.co.mynhaka.polad.domain.model.*;
import zw.co.mynhaka.polad.events.invoice.OnCreateInvoiceEvent;
import zw.co.mynhaka.polad.repository.EmployerRepository;
import zw.co.mynhaka.polad.repository.InvoiceItemRepository;
import zw.co.mynhaka.polad.repository.InvoiceRepository;
import zw.co.mynhaka.polad.repository.PolicyHolderRepository;
import zw.co.mynhaka.polad.service.iface.*;
import zw.co.mynhaka.polad.service.exception.BusinessValidationException;
import zw.co.mynhaka.polad.service.exception.EntityNotFoundException;
import zw.co.mynhaka.polad.service.exception.NoInvoiceException;
import zw.co.mynhaka.polad.service.mapper.invoice.InvoiceToInvoiceResultDTO;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    private final ApplicationEventPublisher applicationEventPublisher;
    private final InvoiceItemRepository invoiceItemRepository;
    private final InvoiceToInvoiceResultDTO toInvoiceResultDTO;
    private final EmployerRepository employerRepository;
    private final PolicyHolderRepository policyHolderRepository;
    private final InvoiceRepository invoiceRepository;
    private final PolicyHolderService policyHolderService;
    private final EmployerService employerService;
    private final PolicySavingsService policySavingsService;
    private final PolicyAccidentService policyAccidentService;
    private final PolicyFuneralService policyFuneralService;
    private final PolicyComprehensiveService policyComprehensiveService;
    private final SavingsProductService savingsProductService;
    private final FuneralProductService funeralProductService;
    private final ComprehensiveProductService comprehensiveProductService;
    private final AccidentProductService accidentProductService;

    @Override
    public InvoiceResultDTO save(Long id) throws EntityNotFoundException, FileNotFoundException, JRException {
        PolicyHolder policyHolder = policyHolderService.getOne(id);
        return toInvoiceResultDTO.convert(generateIndividualInvoice(policyHolder));
    }

    @Override
    public InvoiceResultDTO employerInvoice(Long id) throws EntityNotFoundException, FileNotFoundException, JRException {

        return toInvoiceResultDTO.convert(
                generateEmployerInvoice(employerService.getOne(id))
        );
    }

    @Override
    public InvoiceResultDTO individualInvoice(Long id) throws EntityNotFoundException, FileNotFoundException, JRException {
        return save(id);
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
    public List<Invoice> findAll() {
        return invoiceRepository.findAll();
    }

    @Override
    public Invoice findOne(Long id) {
        return invoiceRepository.findById(id).orElseThrow(javax.persistence.EntityNotFoundException::new);
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
        invoiceRepository.delete(getOne(id));
    }

    @Override
    public void generateEmployerInvoices(Employer employer) throws FileNotFoundException, JRException {
        generateEmployerInvoice(employer);
    }

    @Override
    public void generateIndividualInvoices(PolicyHolder policyHolder) throws FileNotFoundException, JRException {
        generateIndividualInvoice(policyHolder);
    }

    @Override
    public List<InvoiceResultDTO> getByIdNumber(String idnumber) {

        PolicyHolder policyHolder = policyHolderService.getOneByNationalID(idnumber);
        return null;
//        return policyHolder.getInvoices()
//                .stream()
//                .filter(invoice -> invoice.getInvoiceStatus() != InvoiceStatus.PAID)
//                .map(toInvoiceResultDTO::convert)
//                .collect(Collectors.toList());
    }

    @Override
    public List<InvoiceResultDTO> getByPolicyNumber(String policyNumber) {

        List<InvoiceItem> invoiceItems = invoiceItemRepository.findAllByPolicyNumber(policyNumber);
        List<Invoice> invoices = new ArrayList<>();
        for (InvoiceItem invoiceItem : invoiceItems) {
            invoices.add(invoiceItem.getInvoice());
        }

        List<Invoice> distinctElements = invoices.stream()
                .distinct()
                .collect(Collectors.toList());

        return distinctElements
                .stream()
                .filter(invoice -> invoice.getInvoiceStatus() != InvoiceStatus.PAID)
                .map(toInvoiceResultDTO::convert)
                .collect(Collectors.toList());
    }

    @Override
    public Invoice getUnpaidInvoiceByPolicyHolder(PolicyHolder policyHolder) {
        return invoiceRepository.findByPolicyHolderAndInvoiceStatus(policyHolder, InvoiceStatus.PARTIALLY_PAID.name())
                .orElse(invoiceRepository.findByPolicyHolderAndInvoiceStatus(policyHolder, InvoiceStatus.NEW.name())
                        .orElseThrow(() -> new BusinessValidationException("No partially paid or unpaid invoices found.")));
    }

    public Invoice generateEmployerInvoice(Employer employer) throws FileNotFoundException, JRException {

//        final Set<PolicyHolder> policyHolders = employer.getPolicyHolderList();
//
//        List<InvoiceItem> invoiceItems = new ArrayList<>();
//
//
//        for (PolicyHolder policyHolder : policyHolders) {
//            List<PolicyAccident> policyAccidents = policyAccidentService.findPolicyAccidentByPolicyHolder(policyHolder);
////            List<PolicySavings> policySavings = policySavingsService.findPolicySavingsByPolicyHolder(policyHolder);
//            List<PolicyComprehensive> policyComprehensives = policyComprehensiveService.findPolicyComprehensiveByPolicyHolder(policyHolder);
//            List<PolicyFuneral> policyFunerals = policyFuneralService.findPolicyFuneralByPolicyHolder(policyHolder);
//
//            if (policyAccidents.size() == 0 && policySavings.size() == 0 && policyComprehensives.size() == 0 && policyFunerals.size() == 0) {
//                throw new IllegalStateException("No policies found");
//            }
//            /**
//             * Adding Accident Policies
//             */
//            for (PolicyAccident policyAccident : policyAccidents) {
//                InvoiceItem invoiceItem = new InvoiceItem();
//                invoiceItem.setPrice(policyAccident.getAccidentProduct().getPremium());
//                invoiceItem.setPolicyType(PolicyType.ACCIDENT);
//                invoiceItem.setPolicyNumber(policyAccident.getPolicyNumber());
//                invoiceItem.setProduct(policyAccident.getAccidentProduct().getName());
//                invoiceItem.setPolicyholder(policyHolder.getId().toString());
//                invoiceItems.add(invoiceItem);
//                for (BeneficiaryAccident beneficiary : policyAccident.getBeneficiaryList()
//                ) {
//                    InvoiceItem beneficiaryInvoiceItem = new InvoiceItem();
//                    beneficiaryInvoiceItem.setPrice(beneficiary.getAccident().getPremium());
//                    beneficiaryInvoiceItem.setProduct(beneficiary.getAccident().getName());
//                    beneficiaryInvoiceItem.setBeneficiary(beneficiary.getId().toString());
//                    invoiceItems.add(beneficiaryInvoiceItem);
//                }
//            }
//
///**
// * Adding Savings Policies
// */
//            for (PolicySavings savings : policySavings
//            ) {
//                InvoiceItem invoiceItem = new InvoiceItem();
//                invoiceItem.setPrice(savings.getSavingsProduct().getMonthlyInvestmentPremium().add(savings.getSavingsProduct().getPremiumWaiverRate()));
//                invoiceItem.setProduct(savings.getSavingsProduct().getName());
//                invoiceItem.setPolicyType(PolicyType.SAVINGS);
//                invoiceItem.setPolicyNumber(savings.getPolicyNumber());
//                invoiceItem.setPolicyholder(policyHolder.getId().toString());
//                invoiceItems.add(invoiceItem);
//                for (BeneficiarySavings beneficiary : savings.getBeneficiaryList()
//                ) {
//                    InvoiceItem beneficiaryInvoiceItem = new InvoiceItem();
//                    beneficiaryInvoiceItem.setPrice(beneficiary.getSavings().getMonthlyInvestmentPremium().add(beneficiary.getSavings().getPremiumWaiverRate()));
//                    beneficiaryInvoiceItem.setProduct(beneficiary.getSavings().getName());
//                    beneficiaryInvoiceItem.setBeneficiary(beneficiary.getId().toString());
//                    invoiceItems.add(beneficiaryInvoiceItem);
//                }
//            }
//
//            for (PolicyFuneral policyFuneral : policyFunerals
//            ) {
//                InvoiceItem invoiceItem = new InvoiceItem();
//                invoiceItem.setPrice(policyFuneral.getFuneralProduct().getPremium());
//                invoiceItem.setProduct(policyFuneral.getFuneralProduct().getName());
//                invoiceItem.setPolicyType(PolicyType.FUNERAL);
//                invoiceItem.setPolicyNumber(policyFuneral.getPolicyNumber());
//                invoiceItem.setPolicyholder(policyHolder.getId().toString());
//                invoiceItems.add(invoiceItem);
//                for (BeneficiaryFuneral beneficiary : policyFuneral.getBeneficiaryList()
//                ) {
//                    InvoiceItem beneficiaryInvoiceItem = new InvoiceItem();
//                    beneficiaryInvoiceItem.setPrice(beneficiary.getFuneral().getPremium());
//                    beneficiaryInvoiceItem.setProduct(beneficiary.getFuneral().getName());
//                    beneficiaryInvoiceItem.setBeneficiary(beneficiary.getId().toString());
//                    invoiceItems.add(beneficiaryInvoiceItem);
//                }
//            }
//
//            for (PolicyComprehensive policyComprehensive : policyComprehensives
//            ) {
//                InvoiceItem invoiceItem = new InvoiceItem();
//                invoiceItem.setPrice(policyComprehensive.getComprehensiveFuneralProduct().getPremium());
//                invoiceItem.setProduct(policyComprehensive.getComprehensiveFuneralProduct().getName());
//                invoiceItem.setPolicyType(PolicyType.COMPREHENSIVE);
//                invoiceItem.setPolicyNumber(policyComprehensive.getPolicyNumber());
//                invoiceItem.setPolicyholder(policyHolder.getId().toString());
//                invoiceItems.add(invoiceItem);
//                for (BeneficiaryComprehensive beneficiary : policyComprehensive.getBeneficiaryList()
//                ) {
//                    InvoiceItem beneficiaryInvoiceItem = new InvoiceItem();
//                    beneficiaryInvoiceItem.setPrice(beneficiary.getComprehensiveFuneral().getPremium());
//                    beneficiaryInvoiceItem.setProduct(beneficiary.getComprehensiveFuneral().getName());
//                    beneficiaryInvoiceItem.setBeneficiary(beneficiary.getId().toString());
//                    invoiceItems.add(beneficiaryInvoiceItem);
//                }
//            }
//        }
//
//        BigDecimal openingBalance = BigDecimal.ZERO;
//        if (employer.getBalance() != null) {
//            openingBalance = employer.getBalance();
//        }
//
//
//        if (!invoiceItems.isEmpty()) {
//            Invoice invoice = new Invoice();
//            invoice.setEmployer(employer);
//            BigDecimal invoiceTotal = invoice.getInvoiceItemSet().stream()
//                    .map(invoiceItem -> invoiceItem.getPrice())
//                    .reduce(BigDecimal.ZERO, BigDecimal::add);
//            invoice.setClosingBalance(invoiceTotal.add(openingBalance));
//            employer.setBalance(invoiceTotal.add(openingBalance));
//            invoice.setInvoiceStatus(InvoiceStatus.NEW);
//            invoice.setInvoiceItemSet(new HashSet<>(invoiceItems));
//            invoice.setInvoicingDate(LocalDate.now());
//            invoice.setDueDate(LocalDate.now().plusDays(30));
//            invoice.setClosingBalance(invoiceTotal.add(openingBalance));
//            employer.setBalance(invoiceTotal.add(openingBalance));
//            invoice.setInvoiceStatus(InvoiceStatus.NEW);
//            invoiceItems.forEach(invoiceItem -> invoiceItem.addInvoice(invoice));
//            employer.setBalance(invoiceTotal);
//            Invoice newInvoice = invoiceRepository.save(invoice);
//            return newInvoice;
//        } else {
//            throw new NoInvoiceException("No invoice to be generated");
//        }
        return null;
    }

    public Invoice generateIndividualInvoice(PolicyHolder policyHolder) throws FileNotFoundException, JRException {

        List<InvoiceItem> invoiceItems = new ArrayList<>();

        /**
         * Adding Accident Policies
         */

/*
PolicyAccident policyAccident : policyHolder.getPolicyAccidents()
                .stream()
                .filter(policyAccident ->
                        policyAccident.getNextInvoicingDate().isEqual(LocalDate.now()))
                .collect(Collectors.toList())
 */
        for (PolicyAccident policyAccident : policyAccidentService.findPolicyAccidentByPolicyHolderAndNextInvoicingDate(policyHolder, LocalDate.now())) {
            InvoiceItem invoiceItem = new InvoiceItem();
            AccidentProduct accidentProduct = policyAccident.getAccidentProduct();
            invoiceItem.setPrice(accidentProduct.getPremium());
            invoiceItem.setPolicyType(PolicyType.ACCIDENT);
            invoiceItem.setPolicyNumber(policyAccident.getPolicyNumber());
            invoiceItem.setProduct(accidentProduct.getName());
            invoiceItem.setPolicyholder(policyHolder.getFirstname().concat(" ").concat(policyHolder.getLastname()));
            invoiceItems.add(invoiceItem);

            Set<BeneficiaryAccident> beneficiaryAccidentList = policyAccident.getBeneficiaryList();
            List<InvoiceItem> invoiceItems1 = beneficiaryAccidentList
                    .stream()
                    .filter(beneficiaryAccident -> beneficiaryAccident.getAccident() != null)
                    .map(beneficiaryAccident -> new InvoiceItem(null,
                            beneficiaryAccident.getName(),
                            beneficiaryAccident.getAccident().getName(),
                            beneficiaryAccident.getAccident().getPremium()))
                    .collect(Collectors.toList());


            for (BeneficiaryAccident beneficiary : policyAccident.getBeneficiaryList()
            ) {
                if (beneficiary.getAccident() != null) {
                    InvoiceItem beneficiaryInvoiceItem = new InvoiceItem();
                    beneficiaryInvoiceItem.setPrice(beneficiary.getAccident().getPremium());
                    beneficiaryInvoiceItem.setPolicyType(PolicyType.ACCIDENT);
                    beneficiaryInvoiceItem.setPolicyNumber(policyAccident.getPolicyNumber());
                    beneficiaryInvoiceItem.setProduct(beneficiary.getAccident().getName());
                    beneficiaryInvoiceItem.setBeneficiary(beneficiary.getName().concat(" ").concat(beneficiary.getSurname()));
                    invoiceItems.add(beneficiaryInvoiceItem);
                }
            }

            /*switch (policyAccident.getPaymentFrequency().toString()) {
                case "MONTHLY":
                    policyAccident.setNextInvoicingDate(LocalDate.now().plusMonths(1));
                    break;
                case "QUARTERLY":
                    policyAccident.setNextInvoicingDate(LocalDate.now().plusMonths(3));
                    break;
                case "HALF_YEARLY":
                    policyAccident.setNextInvoicingDate(LocalDate.now().plusMonths(6));
                    break;
                case "ANNUALLY":
                    policyAccident.setNextInvoicingDate(LocalDate.now().plusMonths(12));
                    break;
            }*/
        }

/**
 * Adding Savings Policies
 */
//        for (PolicySavings savings : policySavingsService.findPolicySavingsByPolicyHolderAndNextInvoicingDate(policyHolder, LocalDate.now())
//        ) {
//            InvoiceItem invoiceItem = new InvoiceItem();
//            invoiceItem.setPrice(savings.getSavingsProduct().getMonthlyInvestmentPremium().add(savings.getSavingsProduct().getPremiumWaiverRate()));
//            invoiceItem.setProduct(savings.getSavingsProduct().getName());
//            invoiceItem.setPolicyType(PolicyType.SAVINGS);
//            invoiceItem.setPolicyNumber(savings.getPolicyNumber());
//            invoiceItem.setPolicyholder(policyHolder.getFirstname().concat(" ").concat(policyHolder.getLastname()));
//            invoiceItems.add(invoiceItem);
//            for (BeneficiarySavings beneficiary : savings.getBeneficiaryList()
//            ) {
//                InvoiceItem beneficiaryInvoiceItem = new InvoiceItem();
//                beneficiaryInvoiceItem.setPrice(beneficiary.getSavings().getMonthlyInvestmentPremium().add(beneficiary.getSavings().getPremiumWaiverRate()));
//                beneficiaryInvoiceItem.setProduct(beneficiary.getSavings().getName());
//                beneficiaryInvoiceItem.setPolicyType(PolicyType.SAVINGS);
//                beneficiaryInvoiceItem.setPolicyNumber(savings.getPolicyNumber());
//                beneficiaryInvoiceItem.setBeneficiary(beneficiary.getName().concat(" ").concat(beneficiary.getSurname()));
//                invoiceItems.add(beneficiaryInvoiceItem);
//            }
//        }

        for (PolicyFuneral policyFuneral : policyFuneralService.findPolicyFuneralByPolicyHolderAndNextInvoicingDate(policyHolder, LocalDate.now())
        ) {
            InvoiceItem invoiceItem = new InvoiceItem();
            invoiceItem.setPrice(policyFuneral.getFuneralProduct().getPremium());
            invoiceItem.setProduct(policyFuneral.getFuneralProduct().getName());
            invoiceItem.setPolicyType(PolicyType.FUNERAL);
            invoiceItem.setPolicyNumber(policyFuneral.getPolicyNumber());
            invoiceItem.setPolicyholder(policyHolder.getFirstname().concat(" ").concat(policyHolder.getLastname()));
            invoiceItems.add(invoiceItem);
            for (BeneficiaryFuneral beneficiary : policyFuneral.getBeneficiaryList()
            ) {
                InvoiceItem beneficiaryInvoiceItem = new InvoiceItem();
                beneficiaryInvoiceItem.setPrice(beneficiary.getFuneral().getPremium());
                beneficiaryInvoiceItem.setProduct(beneficiary.getFuneral().getName());
                beneficiaryInvoiceItem.setPolicyType(PolicyType.FUNERAL);
                beneficiaryInvoiceItem.setPolicyNumber(policyFuneral.getPolicyNumber());
                beneficiaryInvoiceItem.setBeneficiary(beneficiary.getName().concat(" ").concat(beneficiary.getSurname()));
                invoiceItems.add(beneficiaryInvoiceItem);
            }
        }

        for (PolicyComprehensive policyComprehensive : policyComprehensiveService.findPolicyComprehensiveByPolicyHolderAndNextInvoicingDate(policyHolder, LocalDate.now())
        ) {
            InvoiceItem invoiceItem = new InvoiceItem();
            invoiceItem.setPrice(policyComprehensive.getComprehensiveFuneralProduct().getPremium());
            invoiceItem.setProduct(policyComprehensive.getComprehensiveFuneralProduct().getName());
            invoiceItem.setPolicyType(PolicyType.COMPREHENSIVE);
            invoiceItem.setPolicyNumber(policyComprehensive.getPolicyNumber());
            invoiceItem.setPolicyholder(policyHolder.getFirstname().concat(" ").concat(policyHolder.getLastname()));
            invoiceItems.add(invoiceItem);
            for (BeneficiaryComprehensive beneficiary : policyComprehensive.getBeneficiaryList()
            ) {
                InvoiceItem beneficiaryInvoiceItem = new InvoiceItem();
                beneficiaryInvoiceItem.setPrice(beneficiary.getComprehensiveFuneral().getPremium());
                beneficiaryInvoiceItem.setProduct(beneficiary.getComprehensiveFuneral().getName());
                beneficiaryInvoiceItem.setPolicyType(PolicyType.COMPREHENSIVE);
                beneficiaryInvoiceItem.setPolicyNumber(policyComprehensive.getPolicyNumber());
                beneficiaryInvoiceItem.setBeneficiary(beneficiary.getName().concat(" ").concat(beneficiary.getSurname()));
                invoiceItems.add(beneficiaryInvoiceItem);
            }
        }

        if (!invoiceItems.isEmpty()) {
            //double openingBalance = policyHolder.getBalance();
            Invoice invoice = new Invoice();
            invoice.setInvoiceItemSet(new HashSet<>(invoiceItems));
            double invoiceTotal = 0.0; /*= invoice.getInvoiceItemSet().stream()
                    .map(InvoiceItem::getPrice)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);*/

            invoice.setPolicyHolder(policyHolder);
            invoice.setInvoicingDate(LocalDate.now());
            invoice.setDueDate(LocalDate.now().plusDays(30));
           // invoice.setClosingBalance(invoiceTotal + openingBalance);
           // policyHolder.setBalance(invoiceTotal+ openingBalance);
            invoice.setInvoiceStatus(InvoiceStatus.NEW);
            invoiceItems.forEach(invoiceItem -> invoiceItem.addInvoice(invoice));
           // policyHolder.setBalance(invoiceTotal);
            Invoice newInvoice = invoiceRepository.save(invoice);

            /*OnCreateInvoiceEvent createInvoiceEvent = new OnCreateInvoiceEvent(this, newInvoice, policyHolder.getEmail(), reportServiceImpl.exportCompanyInvoiceReport("pdf", newInvoice));
            applicationEventPublisher.publishEvent(createInvoiceEvent);*/
            return newInvoice;
        } else {
            throw new NoInvoiceException("No invoice to be generated");
        }
    }


}
