package zw.co.mynhaka.policyholderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableFeignClients
public class PolicyHolderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PolicyHolderServiceApplication.class, args);
	}

	/**
	 * Black Jack
	 * Pocker
	 * Tic Tac Toe
	 * Snake
	 * Snakes & Ladders
	 * Maize
	 * */
}
