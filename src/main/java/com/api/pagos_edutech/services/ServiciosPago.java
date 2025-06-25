package com.api.pagos_edutech.services;

import com.api.pagos_edutech.models.ModeloPagos;
import com.api.pagos_edutech.repositories.IPagos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ServiciosPago {

    @Autowired
    IPagos pagos;

    // Obtener todos los pagos
    public ArrayList<ModeloPagos> getusers() {
        return (ArrayList<ModeloPagos>) pagos.findAll();
    }

    // Guardar nuevo pago
    public ModeloPagos saveUser(ModeloPagos tablapagos) {
        return pagos.save(tablapagos);
    }

    // Obtener pago por ID
    public Optional<ModeloPagos> getById(Long id) {
        return pagos.findById(id);
    }

    // Actualizar pago existente por ID
    public ModeloPagos updateById(ModeloPagos request, Long id) {
        ModeloPagos modeloPagos = pagos.findById(id).orElseThrow(() ->
                new RuntimeException("No se encontró el pago con ID: " + id));

        modeloPagos.setNombres(request.getNombres());
        modeloPagos.setApellidos(request.getApellidos());
        modeloPagos.setCorreo(request.getCorreo());
        modeloPagos.setCelular(request.getCelular());
        modeloPagos.setPrecio(request.getPrecio());
        modeloPagos.setMetodoPago(request.getMetodoPago());
        modeloPagos.setFechaPago(request.getFechaPago());
        modeloPagos.setCurso(request.getCurso());

        return pagos.save(modeloPagos); // ✅ Guarda los cambios en la base de datos
    }

    // Eliminar pago por ID
    public boolean deleteUser(Long id) {
        if (pagos.existsById(id)) {
            pagos.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
