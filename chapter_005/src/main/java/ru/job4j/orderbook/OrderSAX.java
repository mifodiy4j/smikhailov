package ru.job4j.orderbook;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class OrderSAX {
    String orderBookName;
    String pathName;
    OrderBook orderBook;

    public OrderSAX(String orderBookName, String pathName) {
        this.orderBookName = orderBookName;
        this.pathName = pathName;
        orderBook = new OrderBook(orderBookName);
    }

    DefaultHandler handler = new DefaultHandler() {
        @Override
        public void startElement(String uri, String localName, String qName,
                                 Attributes attributes) throws SAXException {
            if(localName.equals("AddOrder") || localName.equals("DeleteOrder")) {
                if (attributes.getLocalName(0).equals("book") &&
                        attributes.getValue(0).equals(orderBookName)) {

                    Order order = null;
                    if (localName.equals("AddOrder")) {
                        order = new AddOrder(
                                orderBookName,
                                attributes.getValue(1),
                                Float.parseFloat(attributes.getValue(2)),
                                Integer.parseInt(attributes.getValue(3)),
                                Integer.parseInt(attributes.getValue(4))
                        );
                    } else if (localName.equals("DeleteOrder")) {
                        order = new DeleteOrder(
                                orderBookName,
                                Integer.parseInt(attributes.getValue(1))
                        );
                    }

                    orderBook.newOrder(order);
                }
            }
        }
    };

    public void order() throws ParserConfigurationException, SAXException, IOException {

        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setNamespaceAware(true);

        SAXParser saxParser = factory.newSAXParser();

        saxParser.parse(new File(pathName), handler);
        System.out.println(orderBook.toString());
    }
}
