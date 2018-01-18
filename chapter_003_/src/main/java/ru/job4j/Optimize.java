package ru.job4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.sql.*;

public class Optimize {

    private static final Logger Log = LoggerFactory.getLogger(SQL_Storage.class);

    private String url;
    private String username;
    private String password;
    private int N;

    private int sumOfValuesFields = 0;

    public Optimize(String url, String username, String password, int n) {
        this.url = url;
        this.username = username;
        this.password = password;
        N = n;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setN(int n) {
        N = n;
    }

    public void createTable() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
            Statement st = conn.createStatement();
            st.execute("create table if NOT EXISTS TEST(FIELD integer);");
        } catch (Exception e) {
            Log.error(e.getMessage(), e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    Log.error(e.getMessage(), e);
                }
            }
        }
    }

    public void enterRecordsInTable() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
            PreparedStatement st = conn.prepareStatement("insert into TEST(field) values(?)");
            for (int i = 1; i <= N; i++) {
                st.setInt(1, i);
                st.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteRecordsInTable() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
            Statement st = conn.createStatement();
            st.execute("TRUNCATE TEST;");
        } catch (Exception e) {
            Log.error(e.getMessage(), e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    Log.error(e.getMessage(), e);
                }
            }
        }
    }

    public void createXML() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = null;

        try {
            documentBuilder = factory.newDocumentBuilder();
            Document doc = documentBuilder.newDocument();
            factory.setNamespaceAware(true);

            Element root = doc.createElement("entries");
            doc.appendChild(root);

            for (int i = 0; i < N; i++) {
                Element node1 = doc.createElement("entry");
                root.appendChild(node1);

                Element node2 = doc.createElement("field");
                node2.appendChild(doc.createTextNode("value field" + i));
                node1.appendChild(node2);
            }

            File file = new File("1.xml");
            StreamResult fileResult = new StreamResult(file);

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            transformer.transform(source, fileResult);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace(System.out);
        }
    }

    public void outRecordsFromTable() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
            PreparedStatement st = conn.prepareStatement("select t.field from TEST as t");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt("field"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void enterRecordsInXML() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = null;

        try {
            documentBuilder = factory.newDocumentBuilder();
            Document doc = documentBuilder.newDocument();
            factory.setNamespaceAware(true);

            Element root = doc.createElement("entries");
            doc.appendChild(root);

            Connection conn = null;
            try {
                conn = DriverManager.getConnection(url, username, password);
                PreparedStatement st = conn.prepareStatement("select t.field from TEST as t");
                ResultSet rs = st.executeQuery();
                while (rs.next()) {

                    Element node1 = doc.createElement("entry");
                    root.appendChild(node1);

                    Element node2 = doc.createElement("field");
                    node2.appendChild(doc.createTextNode(String.valueOf(rs.getInt("field"))));
                    node1.appendChild(node2);


                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            File file = new File("1.xml");
            StreamResult fileResult = new StreamResult(file);

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            transformer.transform(source, fileResult);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace(System.out);
        }
    }

    public void modernXML() {
        try {
            File xmlfile = new File("1.xml");
            StreamSource xmlsource = new StreamSource(xmlfile);

            File stylesheet = new File("1.xsl");
            StreamSource stylesource = new StreamSource(stylesheet);

            Transformer transformer = TransformerFactory.newInstance().newTransformer(stylesource);

            transformer.transform(xmlsource, new StreamResult(new File("2.xml")));
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public void parsingXML() {

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            DefaultHandler handler = new DefaultHandler() {

                public void startElement(String uri, String localName,
                                         String qName, Attributes attributes) throws SAXException {
                    if ("entry".equalsIgnoreCase(qName)) {
                        if ("field".equals(attributes.getLocalName(0))) {
                            sumOfValuesFields += Integer.parseInt(attributes.getValue(0));
                        }
                    }
                }
            };

            saxParser.parse(new File("2.xml"), handler);
            System.out.println(sumOfValuesFields);
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Optimize optimize = new Optimize(
                args[0],
                args[1],
                args[2],
                Integer.parseInt(args[3])
        );
        //Optimize optimize = new Optimize("jdbc:postgresql://localhost:5432/SQLite", "postgres", "postgres", 10);

        optimize.createTable();
        optimize.deleteRecordsInTable();
        optimize.enterRecordsInTable();

        optimize.enterRecordsInXML();

        optimize.modernXML();

        optimize.parsingXML();
    }
}
