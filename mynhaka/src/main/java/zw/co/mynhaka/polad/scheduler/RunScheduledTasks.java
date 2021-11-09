package zw.co.mynhaka.polad.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;


@Slf4j
@Configuration
@RequiredArgsConstructor
public class RunScheduledTasks {

   /* private final InvoiceService invoiceService;
    private final PolicyHolderRepository policyHolderRepository;
    private final ReportService reportService;*/

    @Scheduled(cron = "${individual.cron.expression}")
    @Transactional(readOnly = true)
    public void generateIndividualInvoices() throws FileNotFoundException, JRException {
/*
        List<PolicyHolder> policyHolders = policyHolderRepository.findAll();

        for (PolicyHolder policyHolder : policyHolders) {
            invoiceService.generateIndividualInvoices(policyHolder);
        }*/
    /*    try {
            policyHolders.stream()
                    .filter(policyHolder ->
                            policyHolder.getEmployer() == null &&
                                    (!policyHolder.getPolicyAccidents().isEmpty()
                                            || !policyHolder.getPolicyComprehensives().isEmpty()
                                            || !policyHolder.getPolicyFunerals().isEmpty()
                                            || !policyHolder.getPolicySavings().isEmpty()))
                    .forEach(invoiceService::generateIndividualInvoices);

 try (Stream<PolicyHolder> policyHolderStream = policyHolderRepository
                .findAllBy()) {
          }

        } catch (Exception e) {
            throw new FileNotFoundException("File not found");
        }
    }*/
    }


    public void email() throws FileNotFoundException, JRException {
        /*invoiceService.findAll()
                .stream()
                .peek(invoice -> {
                    log.info("### Invoice {} ", invoice.getId());
                })
                .findFirst()
                .filter(invoice -> invoice.getCreatedDate().equals(LocalDate.now()))
                .ifPresent(invoice -> {
                    try {
                        reportService.exportCompanyInvoiceReport("pdf", invoice);
                    } catch (IOException | JRException ex) {
                        ex.printStackTrace();
                    }
                });*/
    }

    @Scheduled(cron = "${maturity.cron.expression}")
    @Transactional(readOnly = true)
    public void checkMaturedPolicies() {

    }

    @Scheduled(cron = "${lapsed.cron.expression}")
    @Transactional(readOnly = true)
    public void checkLapsedPolicies() {

    }
}
