/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.springbatch.examen.config;

//import ec.edu.espe.distribuidas.banquito.generador.personas.tasks.GeneracionPersona;
import ec.edu.espe.distribuidas.springbatch.examen.tasks.Consolidacion;
import ec.edu.espe.distribuidas.springbatch.examen.tasks.LeerCondiciones;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 *
 * @author Hendrix
 */
@Configuration
@EnableAutoConfiguration
@EnableBatchProcessing
public class JobConfig {

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private ApplicationValues applicationValues;

    @Bean
    protected Step leerCondiciones() {
        return steps
                .get("leerCondiciones")
                .tasklet(new LeerCondiciones(this.applicationValues))
                .build();
    }

    @Bean
    protected Step consolidacion() {
        return steps
                .get("consolidacion")
                .tasklet(new Consolidacion())
                .build();
    }

    @Bean
    public Job generarPersonas() {
        return jobs
                .get("generarPersonas")
                .start(leerCondiciones())
                .next(consolidacion())
                .build();
    }

}
