package org.acme.main;

import com.microsoft.azure.sdk.iot.device.*;
import com.microsoft.azure.sdk.iot.device.exceptions.ModuleClientException;
import com.microsoft.azure.sdk.iot.provisioning.security.exceptions.SecurityProviderException;
import io.quarkus.runtime.StartupEvent;
import org.jboss.logging.Logger;

import javax.enterprise.event.Observes;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;

public class ModuleReciever {
    public static final Logger LOG = Logger.getLogger(ModuleReciever.class);

    void startup(@Observes StartupEvent event) throws ExecutionException, InterruptedException, URISyntaxException, IOException, SecurityProviderException, ModuleClientException {
        recieveMessagesFromEdgeHub();
    }

    public void recieveMessagesFromEdgeHub() throws ModuleClientException, IOException {
        Object lockObject = new Object();
        ModuleClient moduleSenderClient = ModuleClient.createFromEnvironment(IotHubClientProtocol.AMQPS);
        moduleSenderClient.open();
        LOG.info("Module client opened successfully");
        moduleSenderClient.setMessageCallback((message, object) -> {
            LOG.debug("Device Message Recieved");
            return IotHubMessageResult.COMPLETE;
        }, lockObject);
    }


}
