package zw.co.mynhaka.polad;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import zw.co.mynhaka.polad.scheduler.RunScheduledTasks;
import zw.co.mynhaka.polad.service.iface.EmployerService;

import java.util.Properties;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class MynhakaApplication implements CommandLineRunner {


    private final RunScheduledTasks runScheduledTasks;
    private final EmployerService employerService;

    public MynhakaApplication(RunScheduledTasks runScheduledTasks, EmployerService employerService) {
        this.runScheduledTasks = runScheduledTasks;
        this.employerService = employerService;
    }

    public static void main(String[] args) {
        SpringApplication.run(MynhakaApplication.class, args);
    }
  

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("mail.mynhaka.co.zw");
        mailSender.setPort(26);

        mailSender.setUsername("noreply@mynhaka.co.zw");
        mailSender.setPassword("Noreply?1986");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "false");
        props.put("mail.smtp.starttls.enable", "false");
        props.put("mail.debug", "true");

        return mailSender;
    }
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }
    @Override
    public void run(String... args) throws Exception {
        System.out.println();
//        runScheduledTasks.generateIndividualInvoices();
        //Log that I have started.
        //runScheduledTasks.email();
    }
}
