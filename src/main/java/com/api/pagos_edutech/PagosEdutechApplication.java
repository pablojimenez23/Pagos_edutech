package com.api.pagos_edutech;

import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class PagosEdutechApplication {

	public static void main(String[] args) {
		Path walletPath = Paths.get("src/main/resources/wallet");
		System.setProperty("oracle.net.tns_admin", walletPath.toAbsolutePath().toString());
		System.out.println("[MAIN] TNS_ADMIN configurado en: " + walletPath.toAbsolutePath());

		SpringApplication.run(PagosEdutechApplication.class, args);
	}
}
