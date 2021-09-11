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

import ec.edu.espe.distribuidas.springbatch.examen.config.ApplicationValues;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;

/**
 *
 * @author Windows Boo
 */
@Slf4j
public class LeerCondiciones implements Tasklet, StepExecutionListener {

    private final ApplicationValues applicationValues;

    public LeerCondiciones(ApplicationValues applicationValues) {
        this.applicationValues = applicationValues;
    }

    @Override
    public void beforeStep(StepExecution se) {
        log.info("Preparando........");
    }

    @Override
    public RepeatStatus execute(StepContribution sc, ChunkContext cc) throws Exception {
        log.info("Va a ejecutar la tarea leer condiciones");
        log.info("El archivo con condiciones es: {}", this.applicationValues.getConfigFile());
        Properties props = new Properties();
        try {
            props.load(new FileInputStream(this.applicationValues.getConfigFile()));
            Integer usuarios;
            Integer porcentaje;
            try {
                usuarios = Integer.parseInt(props.getProperty("usuarios"));
                porcentaje = Integer.parseInt(props.getProperty("porcentaje"));
                log.info("Va a generar {} usuarios", usuarios);
                log.info("{} porcentaje a generar", porcentaje);
                ExecutionContext jobContext = sc.getStepExecution().getJobExecution().getExecutionContext();
                jobContext.put("usuarios", usuarios);
                jobContext.put("porcentaje", porcentaje);
            } catch (NumberFormatException e) {
                log.error("Invalid value for persons");
            }
        } catch (IOException e) {
            log.error("Propertie file does not exists");
        }
        return RepeatStatus.FINISHED;
    }

    @Override
    public ExitStatus afterStep(StepExecution se) {
        log.info("Finalizo la ejecucion");
        return ExitStatus.COMPLETED;
    }

}
