package labs.lab_two.design_patterns.proxy;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

interface RemoteService {
    String fetchData(String request);
}

class RealRemoteService implements RemoteService {

    @Override
    public String fetchData(String request) {
        // Simulate a network request
        try {
            System.out.println("Fetching data from remote service...");
            TimeUnit.SECONDS.sleep(2); // Simulate delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Response for " + request;
    }
}


class CachingProxyService implements RemoteService {

    private final RealRemoteService realRemoteService;
    private final Map<String, String> cache;

    public CachingProxyService() {
        this.realRemoteService = new RealRemoteService();
        this.cache = new HashMap<>();
    }

    @Override
    public String fetchData(String request) {
        if (cache.containsKey(request)) {
            System.out.println("Returning cached response for request: " + request);
            return cache.get(request);
        } else {
            String response = realRemoteService.fetchData(request);
            cache.put(request, response);
            return response;
        }
    }
}


public class CachingServiceClient {

    public static void main(String[] args) {
        RemoteService service = new CachingProxyService();

        // First request, data will be fetched from remote service
        String response1 = service.fetchData("request1");
        System.out.println("Response: " + response1);

        // Second request, same as first, data will be returned from cache
        String response2 = service.fetchData("request1");
        System.out.println("Response: " + response2);

        // New request, data will be fetched from remote service
        String response3 = service.fetchData("request2");
        System.out.println("Response: " + response3);
    }
}
