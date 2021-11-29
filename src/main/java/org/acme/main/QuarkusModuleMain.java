package org.acme.main;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;
import org.jboss.logging.Logger;

@QuarkusMain
public class QuarkusModuleMain {

    private static final Logger LOG = Logger.getLogger(QuarkusModuleMain.class);

    public static void main(String ... args) {
        LOG.info("Running main method");
        Quarkus.run(args);
    }
}
