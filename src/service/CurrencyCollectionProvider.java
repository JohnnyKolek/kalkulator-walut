package service;

import model.Currency;
import model.CurrencyCollection;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.List;

public class CurrencyCollectionProvider implements ICurrencyCollectionProvider{
    @Override
    public CurrencyCollection generateCurrencyCollection(String input) throws ParserConfigurationException, IOException, SAXException {


        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(new InputSource(new StringReader(input)));

        doc.getDocumentElement().normalize();

        NodeList currencyList = doc.getElementsByTagName("pozycja");

        CurrencyCollection currencyCollection = new CurrencyCollection();
        List<Currency> currencies = currencyCollection.getCurrencyList();

        for (int i = 0; i < currencyList.getLength(); i++) {
            Element currencyElement = (Element) currencyList.item(i);
            String currencyName = currencyElement.getElementsByTagName("nazwa_waluty").item(0).getTextContent();
            String factor = currencyElement.getElementsByTagName("przelicznik").item(0).getTextContent();
            String currencyCode = currencyElement.getElementsByTagName("kod_waluty").item(0).getTextContent();
            String exchangeRate = currencyElement.getElementsByTagName("kurs_sredni").item(0).getTextContent();

            String exchangeRateCorrect = exchangeRate.replace(',', '.');

            Currency currency = new Currency(currencyName, currencyCode, Integer.parseInt(factor), new BigDecimal(exchangeRateCorrect));
            currencies.add(currency);
        }

        addPln(currencies);


        return currencyCollection;
    }

    private void addPln(List<Currency> currencies) {
        Currency currency = new Currency("polski zloty", "PLN", 1, BigDecimal.valueOf(1.0));
        currencies.add(currency);
    }
}
