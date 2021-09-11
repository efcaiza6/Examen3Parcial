/*
 *  Creation Date: 2020-06-12
 *  Company: Hadessoft
 *  Project: receipts-analyzer
 *  
 *  Copyright 2020 Hadessoft.
 */
package ec.edu.espe.distribuidas.springbatch.examen.config;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Componente encargado de leer los valores de las propiedades especificas del sistema.
 *
 * @author Hendrix
 */
@Component
@Slf4j
@ToString
public class ApplicationValues {

    private final String mongoHost;
    private final String mongoDB;
    private final String mongoAut;
    private final String mongoUsr;
    private final String mongoPwd;
    private final String configFile;
    private final String dataPath;


    @Autowired
    public ApplicationValues(@Value("${regciv.mongo.host}") String mongoHost,
            @Value("${regciv.mongo.db}") String mongoDB,
            @Value("${regciv.mongo.aut}") String mongoAut,
            @Value("${regciv.mongo.usr}") String mongoUsr,
            @Value("${regciv.mongo.pwd}") String mongoPwd,
            @Value("${regciv.config.file}") String configFile,
            @Value("${regciv.config.dataPath}") String dataPath
    ) {
        this.mongoHost = mongoHost;
        this.mongoDB = mongoDB;
        this.mongoAut = mongoAut;
        this.mongoUsr = mongoUsr;
        this.mongoPwd = mongoPwd;
        this.configFile = configFile;
        this.dataPath = dataPath;
        log.info("Propiedades Cargadas: "+this.toString());
    }

    public String getConfigFile() {
        return configFile;
    }

    public String getMongoHost() {
        return mongoHost;
    }

    public String getMongoDB() {
        return mongoDB;
    }

    public String getMongoUsr() {
        return mongoUsr;
    }

    public String getMongoPwd() {
        return mongoPwd;
    }

    public String getMongoAut() {
        return mongoAut;
    }

    public String getDataPath() {
        return dataPath;
    }

}
