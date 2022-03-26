package stocksportfolio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

@ExtendWith(MockitoExtension.class)
public class StocksPortfolioTest {
    
    @Mock
    private IStockmarketService iStockmarketService;

    @InjectMocks
    private StocksPortfolio stocksPortfolio;


    @Before
    public void setUp() throws Exception {

         MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getTotalValueTest() {

        when(iStockmarketService.lookUpPrice("AAPL")).thenReturn(1.0);
        when(iStockmarketService.lookUpPrice("TSLA")).thenReturn(2.0);

        stocksPortfolio.addStock(new Stock("AAPL", 4));
        stocksPortfolio.addStock(new Stock("TSLA", 3));
        double total = stocksPortfolio.getTotalValue();


        //assertEquals(10.0, total);
        assertThat(total, is(10.0));
        verify(iStockmarketService, Mockito.times(2)).lookUpPrice(Mockito.anyString());
    }
}





