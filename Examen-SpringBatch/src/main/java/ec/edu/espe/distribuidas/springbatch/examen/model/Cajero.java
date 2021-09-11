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
package ec.edu.espe.distribuidas.springbatch.examen.model;

import java.math.BigDecimal;
import lombok.Data;

/**
 *
 * @author Windows Boo
 */
@Data
public class Cajero {

    private String idCajero;
    private BigDecimal monto;
    private Integer billeteDiez;
    private Integer billeteVeinte;
}
