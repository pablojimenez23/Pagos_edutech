package com.api.pagos_edutech;

import com.api.pagos_edutech.models.ModeloPagos;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PagosEdutechApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void deberiaCrearUnPago() throws Exception {
		ModeloPagos nuevoPago = new ModeloPagos();
		nuevoPago.setNombres("Lucia");
		nuevoPago.setApellidos("Perez");
		nuevoPago.setCorreo("lucia@example.com");
		nuevoPago.setCelular("123456789");
		nuevoPago.setPrecio(19000.00);
		nuevoPago.setMetodoPago("Transferencia");
		nuevoPago.setFechaPago("2025-06-21");
		nuevoPago.setCurso("Spring Boot Avanzado");

		mockMvc.perform(post("/Pagos")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(nuevoPago)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.Nombres").value("Lucia"));
	}
	@Test
	void deberiaActualizarUnPago() throws Exception {
		ModeloPagos pagoActualizado = new ModeloPagos();
		pagoActualizado.setNombres("Lucia Modificada");
		pagoActualizado.setApellidos("Perez");
		pagoActualizado.setCorreo("lucia.new@example.com");
		pagoActualizado.setCelular("987654321");
		pagoActualizado.setPrecio(20000.00);
		pagoActualizado.setMetodoPago("Tarjeta");
		pagoActualizado.setFechaPago("2025-07-01");
		pagoActualizado.setCurso("Java Backend");


		mockMvc.perform(put("/Pagos/98")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(pagoActualizado)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.content.Correo").value("pedro@example.com"));
	}
	@Test
	void deberiaEliminarUnPago() throws Exception {
		// Crear primero un pago para eliminar
		ModeloPagos nuevoPago = new ModeloPagos();
		nuevoPago.setNombres("Eliminar");
		nuevoPago.setApellidos("Test");
		nuevoPago.setCorreo("delete@test.com");
		nuevoPago.setCelular("111111111");
		nuevoPago.setPrecio(15000.0);
		nuevoPago.setMetodoPago("Débito");
		nuevoPago.setFechaPago("2025-06-30");
		nuevoPago.setCurso("Curso Eliminar");

		String body = objectMapper.writeValueAsString(nuevoPago);

		String jsonResponse = mockMvc.perform(post("/Pagos")
						.contentType(MediaType.APPLICATION_JSON)
						.content(body))
				.andExpect(status().isCreated())
				.andReturn().getResponse().getContentAsString();

		Long idCreado = objectMapper.readTree(jsonResponse).get("id").asLong();

		mockMvc.perform(delete("/Pagos/" + idCreado))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("ha sido eliminado")));
	}


	@Test
	void deberiaListarTodosLosPagos() throws Exception {
		mockMvc.perform(get("/Pagos"))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("crear")))
				.andExpect(jsonPath("$._links.self").exists());
	}

	@Test
	void deberiaRetornar404ParaPagoInexistente() throws Exception {
		mockMvc.perform(get("/Pagos/99999"))
				.andExpect(status().isNotFound());
	}
	@Test
	void deberiaRetornar404AlEliminarPagoInexistente() throws Exception {
		mockMvc.perform(delete("/Pagos/999999"))
				.andExpect(status().isNotFound());
	}


}


