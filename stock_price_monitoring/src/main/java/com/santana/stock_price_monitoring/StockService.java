package com.santana.stock_price_monitoring;

import io.reactivex.rxjava3.core.Observable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import org.json.JSONObject;

@Service
public class StockService {

    private final WebClient webClient;

    public StockService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://www.alphavantage.co").build();
    }


    public Mono<String> getStockPrice(String stockName) {
        return this.webClient.get()
                .uri("/query?function=TIME_SERIES_INTRADAY&symbol={symbol}&interval=1min&apikey={apiKey}",
                        stockName, System.getenv("API_KEY"))
                .retrieve()
                .bodyToMono(String.class);
    }

    public Observable<String> getFilteredStockPrice(String stockName) {
        return Observable.create(emitter -> getStockPrice(stockName).subscribe(data -> {
            System.out.println("I have data: " + data);
            JSONObject json = new JSONObject(data);
            String price = json.getJSONObject("Time Series (1min)")
                    .keys().next();
            String closingPrice = json.getJSONObject("Time Series (1min)")
                    .getJSONObject(price)
                    .getString("4. close");
            emitter.onNext(closingPrice);
            emitter.onComplete();
        }, emitter::onError));
    }
}
