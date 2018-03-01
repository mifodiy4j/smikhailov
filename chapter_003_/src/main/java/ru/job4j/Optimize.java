package ru.job4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.sql.*;
import java.util.List;

/**
 * @author  Sergey Mikhailov
 * @since   1.0
 */
public class Optimize {

    private static final Logger Log = LoggerFactory.getLogger(Optimize.class);

    private String url;
    private String username;
    private String password;
    private int size;

    private Connection conn;

    private int sumOfValuesFields = 0;

    public Optimize(String url, String username, String password, int size) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.size = size;
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

    public void setSize(int size) {
        this.size = size;
    }

    /**
     * Подключение к базе данных <code>url</code> используя
     * логин <code>username</code> и пароль <code>password</code>
     * @return объект класса Connection - сеанс работы с БД
     * @throws SQLException
     */
    private Connection getConn() throws SQLException {
        Connection result;

        if (conn != null) {
            result = conn;
        } else {
            result = DriverManager.getConnection(url, username, password);
        }

        return result;
    }

    /**
     * Создается новая таблица TEST с колонкой <code>field</code> типа integer
     */
    public void createTable() {

        try {
            conn = getConn();
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        try (Statement st = conn.createStatement()) {
            st.execute("create table if NOT EXISTS TEST(FIELD integer);");
            conn.commit();
        } catch (Exception e) {
            Log.error(e.getMessage(), e);
            try {
                conn.rollback();
            } catch (SQLException e1) {
                Log.error(e1.getMessage(), e1);
            }
        }
    }

    /**
     * Вводятся новые последовательные записи в таблицу TEST в колонку <code>field</code>
     * с 1 по <code>size</code>
     */
    public void enterRecordsInTable() {
        try {
            conn = getConn();
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        try (PreparedStatement st = conn.prepareStatement("insert into TEST(field) values(?)")) {

            for (int i = 1; i <= size; i++) {
                st.setInt(1, i);
                st.executeUpdate();
            }

            conn.commit();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
            try {
                conn.rollback();
            } catch (SQLException e1) {
                Log.error(e1.getMessage(), e1);
            }
        }
    }

    /**
     * Очистка всех строк в таблице TEST
     */
    public void deleteRecordsInTable() {
        try {
            conn = getConn();
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        try (Statement st = conn.createStatement()) {
            st.execute("TRUNCATE TEST;");
            conn.commit();
        } catch (Exception e) {
            Log.error(e.getMessage(), e);
            try {
                conn.rollback();
            } catch (SQLException e1) {
                Log.error(e1.getMessage(), e1);
            }
        }
    }

    /**
     * Создание файла <code>1.xml</code> с заданной структурой
     */
    public void createXML() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = null;

        try {
            documentBuilder = factory.newDocumentBuilder();
            Document doc = documentBuilder.newDocument();
            factory.setNamespaceAware(true);

            Element root = doc.createElement("entries");
            doc.appendChild(root);

            for (int i = 0; i < size; i++) {
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

    /**
     * Вывод всех значений колонки <code>field</code> таблицы TEST в консоль
     */
    public void outRecordsFromTable() {
        try {
            conn = getConn();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        try (PreparedStatement st = conn.prepareStatement("select t.field from TEST as t");
             ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                System.out.println(rs.getInt("field"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Создание файла <code>1.xml</code> с заданной структурой и
     * запись в него значений из таблицы TEST
     */
    public void enterRecordsInXML() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = null;

        try {
            documentBuilder = factory.newDocumentBuilder();
            Document doc = documentBuilder.newDocument();
            factory.setNamespaceAware(true);

            Element root = doc.createElement("entries");
            doc.appendChild(root);

            try {
                conn = getConn();
                conn.setAutoCommit(false);
            } catch (SQLException e) {
                Log.error(e.getMessage(), e);
            }
            try (PreparedStatement st = conn.prepareStatement("select t.field from TEST as t");
                 ResultSet rs = st.executeQuery()) {

                while (rs.next()) {

                    Element node1 = doc.createElement("entry");
                    root.appendChild(node1);

                    Element node2 = doc.createElement("field");
                    node2.appendChild(doc.createTextNode(String.valueOf(rs.getInt("field"))));
                    node1.appendChild(node2);


                }

                conn.commit();
            } catch (SQLException e) {
                Log.error(e.getMessage(), e);
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    Log.error(e1.getMessage(), e1);
                }
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

    /**
     * Преобразование содержимого файла <code>1.xml</code> к структуре заданного
     * типа в файл <code>2.xml</code>. Преобразование выполняется посредством XSLT,
     * структура нового файла определена в файле <code>1.xsl</code>
     */
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

    /**
     * Парсинг файла <code>2.xml</code>, подсчет суммы всех атрибутов
     * <code>field</code> и вывод в консоль
     */
    public void parsingXML() {

        try {
            File file = new File("2.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Entries.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Entries entries = (Entries) jaxbUnmarshaller.unmarshal(file);

            System.out.println("Field ");

            List<Entry> entryList = entries.getEntry();

            for (Entry entry : entryList) {
                sumOfValuesFields += entry.getField();
            }

            System.out.println(sumOfValuesFields);

        } catch (JAXBException e) {
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

@XmlRootElement
class Entries {

    List<Entry> entryList;

    @XmlElement
    public List<Entry> getEntry() {
        return entryList;
    }

    public void setEntry(List<Entry> entriesList) {
        this.entryList = entriesList;
    }

}

class Entry {
    private int field;

    @XmlAttribute
    public int getField() {
        return field;
    }

    public void setField(int field) {
        this.field = field;
    }
}