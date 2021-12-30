package studio.thamessia;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Random;

public class ProxyGenerator {
    protected InetAddress generateProxy() throws IOException {
        for (;;) {
            InetAddress address = InetAddress.getByName(String.valueOf(new Random().nextInt(255)) + "." +
                    new Random().nextInt(255) + "." + new Random().nextInt(255) + "." + new Random().nextInt(255));
            if (address.isReachable(5000)) return address;
        }
    }
}
