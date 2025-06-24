package com.api.pagos_edutech;

import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.api.pagos_edutech")
public class PagosEdutechApplication {

    public static void main(String[] args) {
        // Cambiado para apuntar a la carpeta "wallet" en la raíz del proyecto / contenedor
        Path walletPath = Paths.get("wallet");
        System.setProperty("oracle.net.tns_admin", walletPath.toAbsolutePath().toString());
        System.out.println("[MAIN] TNS_ADMIN configurado en: " + walletPath.toAbsolutePath());

        SpringApplication.run(PagosEdutechApplication.class, args);
    }
}
