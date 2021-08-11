package com.everis.clientservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class customertServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(customertServiceApplication.class, args);
    System.out.println("-Micro servicio cliente, activado.");
  }
}
