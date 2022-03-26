package stocksportfolio;

import java.util.List;
import java.util.ArrayList;


public class StocksPortfolio {
    public List<Stock> stocks;
    public IStockmarketService stockmarket;

    public StocksPortfolio(IStockmarketService stockmarket) {
        this.stockmarket = stockmarket;
        stocks = new ArrayList<Stock>();
    }

    public void addStock(Stock stock) {
        stocks.add(stock);
    }

    public double getTotalValue() {
        double totalValue = 0;

        for (Stock stock : stocks) {
            totalValue += stock.quantity*stockmarket.lookUpPrice(stock.label);
        }
        return totalValue;
    }
}
