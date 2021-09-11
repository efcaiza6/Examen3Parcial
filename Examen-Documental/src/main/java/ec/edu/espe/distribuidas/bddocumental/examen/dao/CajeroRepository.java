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
package ec.edu.espe.distribuidas.bddocumental.examen.dao;

import ec.edu.espe.distribuidas.bddocumental.examen.model.Cajero;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author Windows Boo
 */
public interface CajeroRepository extends MongoRepository<Cajero, String> {

    List<Cajero> findByMontoLessThan(Integer monto);

    Cajero findByIdCajero(String id);

    Boolean existsByIdCajero(String id);
}
