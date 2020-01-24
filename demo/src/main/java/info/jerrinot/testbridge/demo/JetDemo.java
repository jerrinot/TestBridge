package info.jerrinot.testbridge.demo;

import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.jet.Jet;
import com.hazelcast.jet.JetInstance;
import com.hazelcast.jet.pipeline.Pipeline;
import com.hazelcast.jet.pipeline.SinkBuilder;
import com.hazelcast.jet.pipeline.Sinks;
import com.hazelcast.jet.pipeline.StreamSource;
import com.hazelcast.map.EventJournalMapEvent;
import info.jerrinot.hazelcastbridge.jetsource.Hazelcast3Sources;

import static com.hazelcast.jet.pipeline.JournalInitialPosition.START_FROM_OLDEST;
import static com.hazelcast.jet.pipeline.Sinks.logger;

public class JetDemo {
    private static final String MAP_NAME = "myMap";

    public static void main(String[] args) {
        Pipeline pipeline = Pipeline.create();

        StreamSource<EventJournalMapEvent<Integer, String>> myMap = Hazelcast3Sources.mapJournal(MAP_NAME, "classpath:my-client-config.xml", START_FROM_OLDEST);
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.getNetworkConfig().addAddress("localhost:4000");

        SinkBuilder.sinkBuilder("foo", )
        pipeline.readFrom(myMap)
                .withIngestionTimestamps()
                .writeTo(Sinks.remoteMapWithUpdating(MAP_NAME, clientConfig, EventJournalMapEvent::getKey, (curValue, entry) -> entry.getNewValue()));

        JetInstance jetInstance = Jet.newJetInstance();
        jetInstance.newJob(pipeline).join();
    }
}
