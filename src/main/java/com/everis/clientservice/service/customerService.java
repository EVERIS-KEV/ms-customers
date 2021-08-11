package com.everis.clientservice.service;

import com.everis.clientservice.dto.message;
import com.everis.clientservice.model.customer;
import com.everis.clientservice.model.typeCustomers;
import com.everis.clientservice.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.*;

@Service
@Transactional
public class customerService {
  @Autowired
  customerRepository repository;

  public Mono<Boolean> verifyId(String id) {
    return Mono.just(repository.existsById(id));
  }

  public customer searchClient(String id) {
    return repository.findById(id).get();
  }

  public Mono<Object> save(customer obj) {
    String m = "Datos registrados.";

    if (
      typeCustomers.empresial().equals(obj.getType()) ||
      typeCustomers.personal().equals(obj.getType())
    ) {
      try {
        if (
          repository.existsByFirstname(obj.getFirstname()) &&
          repository.existsByLastname(obj.getLastname()) ||
          repository.existsByDni(obj.getDni())
        ) m = "Datos repetidos."; else repository.save(obj);
      } catch (Exception e) {
        m = "Datos invalidos.";
      }
    } else m = "Tipo incorrecto.";

    return Mono.just(new message(m));
  }

  public Mono<Object> detailsClient(String id) {
    if (repository.existsById(id)) return Mono.just(searchClient(id));

    return Mono.just(new message("Cliente no registrado."));
  }

  public Mono<Object> update(
    String id,
    String dni,
    String firstname,
    String lastname,
    String typecustomer
  ) {
    if (repository.existsById(id)) {
      customer obj = new customer(id, dni, firstname, lastname, typecustomer);
      return save(obj);
    }

    return Mono.just(new message("Cliente no registrado."));
  }

  public Mono<Object> register(
    String dni,
    String firstname,
    String lastname,
    String typecustomer
  ) {
    customer obj = new customer(null, dni, firstname, lastname, typecustomer);
    return save(obj);
  }

  public Mono<Object> delete(String id) {
    String m = "Cliente no registrado.";

    if (repository.existsById(id)) {
      repository.deleteById(id);
      m = "Cliente eliminado.";
    }

    return Mono.just(new message(m));
  }

  public Mono<Object> searchByDni(String dni) {
    if (repository.existsByDni(dni)) return Mono.just(
      repository.findByDni(dni)
    ); else return Mono.just(new message("Dni no encontrado."));
  }

  public Flux<Object> listAll() {
    return Flux.fromIterable(repository.findAll());
  }
}
