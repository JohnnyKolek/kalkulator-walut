import model.CurrencyCollection;
import org.xml.sax.SAXException;
import service.*;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        IDataProvider dataProvider = new DataProvider();
        String acquiredData = dataProvider.acquireData("https://www.nbp.pl/kursy/xml/lasta.xml");

        ICurrencyCollectionProvider currencyCollectionProvider = new CurrencyCollectionProvider();
        CurrencyCollection currencyCollection = currencyCollectionProvider.generateCurrencyCollection(acquiredData);
        ICurrencyExchangeService currencyExchangeService = CurrencyExchangeService.getInstance();

        ConsoleRunner consoleRunner = new ConsoleRunner(currencyExchangeService,currencyCollection);
        consoleRunner.run();
    }
}