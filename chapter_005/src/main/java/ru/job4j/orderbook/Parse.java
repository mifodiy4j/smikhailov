package ru.job4j.orderbook;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import java.util.TreeSet;

public class Parse {

    private final TreeSet<Order> orders;

    public Parse() {
    orders = new TreeSet<>();
    }

    public Parse(TreeSet<Order> orders) {
        this.orders = orders;
    }

    public TreeSet<Order> par(String body) {
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(body);

            // Получаем корневой элемент - это Orders
            Node root = document.getDocumentElement();
            // Просматриваем все подэлементы корневого - это AddOrders и DeleteOrders
            NodeList nodeList = root.getChildNodes();

            for (int x = 0; x < nodeList.getLength(); x++) {
                Node node = nodeList.item(x);
                //Если нода не текст, то заходим внутрь
                if (node.getNodeType() != Node.TEXT_NODE) {

                    String nodeName = node.getNodeName();

                    Order order;
                    String book = node.getAttributes().getNamedItem("book").getNodeValue();
                    int orderId = Integer.parseInt(node.getAttributes().getNamedItem("orderId").getNodeValue());

                    if (nodeName.equals("AddOrder")) {
                        String operation = node.getAttributes().getNamedItem("operation").getNodeValue();
                        float price = Float.parseFloat(node.getAttributes().getNamedItem("price").getNodeValue());
                        int volume = Integer.parseInt(node.getAttributes().getNamedItem("volume").getNodeValue());
                        order = new AddOrder(book, operation, price, volume, orderId);
                        orders.add(order);
                    } else if (nodeName.equals("DeleteOrder")) {
                        order = new DeleteOrder(book, orderId);
                        orders.remove(order);
                    }
                }
            }
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return orders;
    }

}
