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
package ec.edu.espe.distribuidas.springbatch.examen.tasks;

import ec.edu.espe.distribuidas.springbatch.examen.model.Cajero;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Windows Boo
 */
@Component
@Slf4j
public class Consolidacion implements Tasklet, StepExecutionListener {

    @Override
    public void beforeStep(StepExecution se) {
        log.info("Preparando........");
    }

    @Override
    //@Scheduled(fixedRate = 5000)
    public RepeatStatus execute(StepContribution sc, ChunkContext cc) throws Exception {
        Cajero[] cajero;
        cajero = restTemplate.getForObject("http://localhost:8080/v1/cajero/", Cajero.class);
        for(int i =0; i<cajero.length;i++){
            cajero[i].getIdCajero();
            cajero[i].getMonto();
            cajero[i].getBilleteDiez();
            cajero[i].getBilleteVeinte();
            log.info("Informacion {}",cajero[i]);
                    
        }
        return RepeatStatus.FINISHED;
    }

    @Override
    public ExitStatus afterStep(StepExecution se) {
        log.info("Finalizo la ejecucion");
        return ExitStatus.COMPLETED;
    }

}
