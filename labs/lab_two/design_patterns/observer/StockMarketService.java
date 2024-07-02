package labs.lab_two.design_patterns.observer;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class StockData implements Subject {
    private List<Observer> observers;
    private Map<String, Float> stockPrices;

    public StockData() {
        observers = new ArrayList<>();
        stockPrices = new HashMap<>();
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void unregisterObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }

    public void setStockPrice(String stock, float price) {
        stockPrices.put(stock, price);
        notifyObservers();
    }

    public Float getStockPrice(String stock) {
        return stockPrices.get(stock);
    }
}

class StockObserver implements Observer {
    private String stock;
    private Float price;

    public StockObserver(Subject stockData, String stock) {
        this.stock = stock;
        stockData.registerObserver(this);
    }

    @Override
    public void update(Subject subject) {
        if (subject instanceof StockData) {
            StockData stockData = (StockData) subject;
            Float newPrice = stockData.getStockPrice(stock);
            if (newPrice != null && !newPrice.equals(price)) {
                price = newPrice;
                display();
            }
        }
    }

    public void display() {
        System.out.println("Stock: " + stock + " | New Price: " + price);
    }
}

public class StockMarketService {
    public static void main(String[] args) {
        StockData stockData = new StockData();

        StockObserver googleObserver = new StockObserver(stockData, "GOOG");
        StockObserver appleObserver = new StockObserver(stockData, "AAPL");

        stockData.setStockPrice("GOOG", 1500.0f);
        stockData.setStockPrice("AAPL", 200.0f);
        stockData.setStockPrice("GOOG", 1510.0f);
        stockData.setStockPrice("AAPL", 202.0f);
    }
}
