package service;

import model.CurrencyCollection;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public interface ICurrencyCollectionProvider {
    public CurrencyCollection generateCurrencyCollection(String input) throws ParserConfigurationException, IOException, SAXException;
}
