package com.api.pagos_edutech.controllers;

import com.api.pagos_edutech.models.ModeloPagos;
import com.api.pagos_edutech.services.ServiciosPago;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/Pagos")
public class PagoController {

    @Autowired
    private ServiciosPago serviciosPago;


    @GetMapping()
    public ResponseEntity<CollectionModel<EntityModel<ModeloPagos>>> getUsers() {
        List<EntityModel<ModeloPagos>> pagos = serviciosPago.getusers().stream()
                .map(pago -> toModel(pago))
                .collect(Collectors.toList());

        CollectionModel<EntityModel<ModeloPagos>> collectionModel = CollectionModel.of(pagos);

        collectionModel.add(linkTo(methodOn(PagoController.class).getUsers()).withSelfRel());
        collectionModel.add(linkTo(methodOn(PagoController.class).saveUser(null)).withRel("crear"));

        return ResponseEntity.ok(collectionModel);
    }

    @PostMapping
    public ResponseEntity<EntityModel<ModeloPagos>> saveUser(@RequestBody ModeloPagos tablapagos) {
        ModeloPagos nuevoPago = this.serviciosPago.saveUser(tablapagos);
        EntityModel<ModeloPagos> pagoModel = toModel(nuevoPago);

        return ResponseEntity
                .created(pagoModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(pagoModel);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<EntityModel<ModeloPagos>> getUserById(@PathVariable("id") Long id) {
        Optional<ModeloPagos> optionalPago = this.serviciosPago.getById(id);

        if (optionalPago.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(toModel(optionalPago.get()));
    }


    @PutMapping(path = "/{id}")
    public ResponseEntity<EntityModel<ModeloPagos>> updateUserById(@RequestBody ModeloPagos request, @PathVariable("id") Long id) {
        ModeloPagos actualizado = this.serviciosPago.updateById(request, id);
        return ResponseEntity.ok(toModel(actualizado));
    }


    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        boolean ok = this.serviciosPago.deleteUser(id);
        if (ok) {
            return ResponseEntity.ok("El pago con el id: " + id + " ha sido eliminado");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private EntityModel<ModeloPagos> toModel(ModeloPagos pago) {
        return EntityModel.of(pago,
                linkTo(methodOn(PagoController.class).getUserById(pago.getId())).withSelfRel(),
                linkTo(methodOn(PagoController.class).getUsers()).withRel("Todos los pagos"),
                linkTo(methodOn(PagoController.class).saveUser(null)).withRel("Crear Pago"),
                linkTo(methodOn(PagoController.class).deleteById(pago.getId())).withRel("Eliminar Pago")
        );
    }
}
