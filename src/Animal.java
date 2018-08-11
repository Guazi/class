import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.*;

abstract public class Animal {

    public String name;
    public Date birthDate;
    public Integer age;
    public String species;

    public Animal(String name, Date birthDate, Integer age, String species) {
        this.name = name;
        this.birthDate = birthDate;
        this.age = age;
        this.species = species;
    }

    public Animal(String XMLString) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(XMLString));

            Document doc = db.parse(is);
            NodeList nodes = doc.getElementsByTagName("Animal");
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

            // iterate the employees
            for (int i = 0; i < nodes.getLength(); i++) {
                Element elem = (Element) nodes.item(i);
                this.name = elem.getElementsByTagName("name").item(0).getTextContent();
                String birthdate = elem.getElementsByTagName("birthDate").item(0).getTextContent();
                Date convertedDate = dateFormat.parse(birthdate);
                this.birthDate = convertedDate;
                this.age = Integer.parseInt(elem.getElementsByTagName("age").item(0).getTextContent());
                this.species = elem.getElementsByTagName("species").item(0).getTextContent();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    abstract public Integer calculateLifeExpectancy();

}
