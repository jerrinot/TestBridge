package hz3server;

import com.hazelcast.config.Config;
import com.hazelcast.config.EventJournalConfig;
import com.hazelcast.core.Hazelcast;

public class StartServer {
    public static void main(String[] args) {
        Config config = new Config();
        config.getNetworkConfig().setPort(4000);
        Hazelcast.newHazelcastInstance(config);
    }
}
