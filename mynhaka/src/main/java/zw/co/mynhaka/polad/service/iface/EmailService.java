package zw.co.mynhaka.polad.service.iface;


import zw.co.mynhaka.polad.domain.model.Invoice;

public interface EmailService {
    void sendSimpleMessage(String to,
                           String subject,
                           String text);


    void sendMessageWithAttachment(String to,
                                   String subject,
                                   String text,
                                   String pathToAttachment);

    void sendInvoiceWithAttachment(Invoice invoice, String to, String attachment);


}