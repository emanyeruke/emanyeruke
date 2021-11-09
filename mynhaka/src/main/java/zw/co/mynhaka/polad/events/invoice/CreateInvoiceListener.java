package zw.co.mynhaka.polad.events.invoice;


import lombok.SneakyThrows;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import zw.co.mynhaka.polad.service.iface.EmailService;
import zw.co.mynhaka.polad.service.iface.ReportService;


@Component
public class CreateInvoiceListener implements ApplicationListener<OnCreateInvoiceEvent> {

    private final EmailService emailService;
    private final ReportService reportService;

    public CreateInvoiceListener(final EmailService emailService,
                                 final ReportService reportService) {
        this.emailService = emailService;
        this.reportService = reportService;
    }

    @SneakyThrows
    @Override
    public void onApplicationEvent(OnCreateInvoiceEvent onCreateInvoiceEvent) {
        emailService.sendInvoiceWithAttachment(onCreateInvoiceEvent.getInvoice(), onCreateInvoiceEvent.getEmailAddress(), reportService.exportCompanyInvoiceReport("pdf", onCreateInvoiceEvent.getInvoice()));
    }


}
