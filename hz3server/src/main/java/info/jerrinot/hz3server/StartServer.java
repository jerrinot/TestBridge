package info.jerrinot.hz3server;

import com.hazelcast.config.Config;
import com.hazelcast.config.EventJournalConfig;
import com.hazelcast.core.Hazelcast;

public class StartServer {
    public static void main(String[] args) {
        Config config = new Config();
        config.addEventJournalConfig(new EventJournalConfig().setMapName("myMap").setEnabled(true));
        config.getNetworkConfig().setPort(3120);
        Hazelcast.newHazelcastInstance(config);
    }
}
