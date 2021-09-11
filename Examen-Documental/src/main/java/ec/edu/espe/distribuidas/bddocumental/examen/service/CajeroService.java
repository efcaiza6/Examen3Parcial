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
package ec.edu.espe.distribuidas.bddocumental.examen.service;

import ec.edu.espe.distribuidas.bddocumental.examen.dao.CajeroRepository;
import ec.edu.espe.distribuidas.bddocumental.examen.model.Cajero;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Windows Boo
 */
@Service
public class CajeroService {

    private final CajeroRepository cajeroRepo;

    public CajeroService(CajeroRepository cajeroRepo) {
        this.cajeroRepo = cajeroRepo;
    }

    public List<Cajero> listMenorMonto(Integer monto) {
        return this.cajeroRepo.findByMontoLessThan(monto);
    }

    public Cajero obtainById(String id) {
        Cajero cajeroFind = this.cajeroRepo.findByIdCajero(id);
        if (cajeroFind == null) {
            throw new IllegalArgumentException("Cajero not found.");
        }
        return cajeroRepo.findByIdCajero(id);
    }

    public void retiroCajero(Cajero cajero) {

        if (!cajeroRepo.existsByIdCajero(cajero.getIdCajero())) {
            throw new IllegalArgumentException("El cajero no existe");
        } else {
            Cajero caj = obtainById(cajero.getIdCajero());
            caj.setMonto(caj.getMonto() - cajero.getMonto());
            caj.setBilleteVeinte(caj.getBilleteVeinte() - cajero.getBilleteVeinte());
            caj.setBilleteDiez(caj.getBilleteDiez() - cajero.getBilleteDiez());
            cajeroRepo.save(caj);
        }
    }
    
    public void dineroIngresadoCajero(Cajero cajero) {

        if (!cajeroRepo.existsByIdCajero(cajero.getIdCajero())) {
            throw new IllegalArgumentException("El cajero no existe");
        } else {
            Cajero caj = obtainById(cajero.getIdCajero());
            caj.setMonto(caj.getMonto() + cajero.getMonto());
            caj.setBilleteVeinte(caj.getBilleteVeinte() + cajero.getBilleteVeinte());
            caj.setBilleteDiez(caj.getBilleteDiez() + cajero.getBilleteDiez());
            cajeroRepo.save(caj);
        }
    }

}
