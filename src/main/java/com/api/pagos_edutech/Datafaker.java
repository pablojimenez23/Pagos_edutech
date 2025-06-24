package com.api.pagos_edutech;

import com.api.pagos_edutech.models.ModeloPagos;
import com.api.pagos_edutech.repositories.IPagos;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Configuration
public class Datafaker {

    @Bean
    CommandLineRunner cargarDatosFalsos(IPagos pagosRepository) {
        return args -> {
            if (pagosRepository.count() == 0) {
                Faker faker = new Faker(new Locale("es"));
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                for (int i = 0; i < 10; i++) {
                    ModeloPagos pago = new ModeloPagos();
                    pago.setNombres(faker.name().firstName());
                    pago.setApellidos(faker.name().lastName());
                    pago.setCorreo(faker.internet().emailAddress());
                    pago.setCelular(faker.phoneNumber().cellPhone());
                    pago.setPrecio(faker.number().randomDouble(2, 50000, 120000));
                    pago.setMetodoPago(faker.options().option("Transferencia", "Webpay", "Débito", "Crédito"));
                    pago.setFechaPago(faker.date().birthday().toInstant()
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate()
                            .format(formatter));
                    pago.setCurso(faker.educator().course());

                    pagosRepository.save(pago);
                }

                System.out.println("✅ 10 pagos generados con Datafaker.");
            } else {
                System.out.println("ℹ️ Ya existen pagos, no se generó nada.");
            }
        };
    }
}
