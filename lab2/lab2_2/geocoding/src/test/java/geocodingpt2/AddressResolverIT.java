package geocodingpt2;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import geocoding.Address;
import geocoding.AddressResolver;
import geocoding.TqsBasicHttpClient;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AddressResolverIT {

    private TqsBasicHttpClient tqsBasicHttpClient;

    private AddressResolver addressResolver;

    @BeforeEach
    public void init(){
        tqsBasicHttpClient = new TqsBasicHttpClient();
        addressResolver = new AddressResolver(tqsBasicHttpClient);
    }

    @Test
    void whenResolveAlboiGps_returnCaisAlboiAddress() throws ParseException, IOException, URISyntaxException {

       
        Optional<Address> result = addressResolver.findAddressForLocation(40.640661, -8.656688);

        assertEquals(result.get(), new Address( "Cais do Alboi", "Glória e Vera Cruz", "Centro", "3800-246", null));
    }

    @Test
    public void whenBadCoordidates_thenReturnNoValidAddress() throws IOException, URISyntaxException, ParseException {

        Optional<Address> result = addressResolver.findAddressForLocation(-300.000000, -810.000000);

        assertThrows(NoSuchElementException.class, ()->{result.get();});
    }
}
