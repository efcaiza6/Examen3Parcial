/*
 * Copyright (c) 2021 Windows Boo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Windows Boo - initial API and implementation and/or initial documentation
 */
package ec.edu.espe.distribuidas.bddocumental.examen.controller;

import ec.edu.espe.distribuidas.bddocumental.examen.model.Cajero;
import ec.edu.espe.distribuidas.bddocumental.examen.service.CajeroService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Windows Boo
 */
@RestController
@RequestMapping("/v1/cajero")
@Slf4j
public class CajeroController {

    private final CajeroService service;

    public CajeroController(CajeroService service) {
        this.service = service;
    }

    @GetMapping(value = "{valor}")
    public ResponseEntity listarTodosMenorValor(@PathVariable("valor") Integer valor) {
        List<Cajero> cajero = this.service.listMenorMonto(valor);
        return ResponseEntity.ok(cajero);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity obtenerDatosCajero(
            @PathVariable("id") String id) {
        try {
            Cajero cajero = this.service.obtainById(id);
            return ResponseEntity.ok(cajero);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/retiro")
    public ResponseEntity retiroDineroCajero(@RequestBody Cajero cajero) {
        try {
            log.info("Va a retirar dinero de la cuenta", cajero);
            this.service.retiroCajero(cajero);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("Ocurrio un error {}"
                    + " - retorna badrequest", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/ingreso")
    public ResponseEntity ingresoDineroCajero(@RequestBody Cajero cajero) {
        try {
            log.info("Va a depositar dinero en la cuenta", cajero);
            this.service.dineroIngresadoCajero(cajero);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("Ocurrio un error {}"
                    + " - retorna badrequest", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}
