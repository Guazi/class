import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.lang.*;

class InvalidAnimalException extends RuntimeException
{
    // Parameterless Constructor
    public InvalidAnimalException() {}

    // Constructor that accepts a message
    public InvalidAnimalException(String message)
    {
        super(message);
    }
}


public class ZooOrganizer {
    public Zoo zoo;

    public ZooOrganizer() {
        this.zoo = new Zoo();
    }

    public void saveFile(String filename) {
        // Check if file exists, dont need to create again if does. Read existing file into memory
        // If not exist, create new file
        // https://examples.javacodegeeks.com/core-java/nio/java-nio-append-file-example/\

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();
            //add elements to Document
            Element rootElement = doc.createElementNS("https://www.danielavery.net", "Animals");
            //append root element to document
            doc.appendChild(rootElement);

            for(Animal a : this.zoo.getAnimals()) {
//                TODO: Make a validator for species here, if a.species not validated, pop it from the list and throw InvalidAnimalException
                rootElement.appendChild(getAnimal(doc, a));
            }


            //for output to file, console
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            //for pretty print
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);

            //write to console or file
            StreamResult file = new StreamResult(new File(filename));

            //write data
            transformer.transform(source, file);
            System.out.println("DONE");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openFile(String filename) throws InvalidAnimalException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new File(filename));
            doc.getDocumentElement().normalize();
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nodeList = doc.getElementsByTagName("Animal");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node nNode = nodeList.item(i);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) nNode;

                    String name = elem.getElementsByTagName("name").item(0).getTextContent();

                    String birthdate = elem.getElementsByTagName("birthDate").item(0).getTextContent();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                    Date convertedDate = dateFormat.parse(birthdate);

                    Integer age = Integer.parseInt(elem.getElementsByTagName("age").item(0).getTextContent());

                    String species = elem.getElementsByTagName("species").item(0).getTextContent();

                    // https://stackoverflow.com/questions/5658182/initializing-a-class-with-class-forname-and-which-have-a-constructor-which-tak
                    try {
                        // returns the Class object for the class with the specified name
                        Class cls = Class.forName("AAA");
                        Constructor c = cls.getConstructor(String.class, Date.class, Integer.class, String.class);
                        Animal animal = (Animal) c.newInstance(name, convertedDate, age, species);
                        this.zoo.addAnimal(animal);
                    } catch(ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException ex) {
                        System.out.println(ex.toString());
                        throw new InvalidAnimalException("ASDSDSA");
//                        TODO: How to catch InvalidAnimalException here in the ZooManager.java method
//                        throw new InvalidAnimalException("Bad Animal");
                    }
                }
            }
        } catch (ParserConfigurationException | IOException e1) {
            e1.printStackTrace();
        } catch (org.xml.sax.SAXException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void addAnimal(Animal myAnimal) {
        this.zoo.addAnimal(myAnimal);
    }

    public void deleteAnimal(Animal myAnimal) {
        this.zoo.deleteAnimal(myAnimal);
    }

    public void updateAnimal(int id, Animal myAnimal) {
        this.zoo.updateAnimal(id, myAnimal);
    }

    public Animal getAnimal(int id) {
        Animal animal = this.zoo.getAnimal(id);
        return animal;
    }


    private static Node getAnimal(Document doc, Animal a) {
        Element animal_element = doc.createElement("Animal");

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

        animal_element.appendChild(getAnimalElements(doc, animal_element, "name", a.name));
        animal_element.appendChild(getAnimalElements(doc, animal_element, "birthDate", sdf.format(a.birthDate)));
        animal_element.appendChild(getAnimalElements(doc, animal_element, "age", String.valueOf(a.age)));
        animal_element.appendChild(getAnimalElements(doc, animal_element, "species", a.species));

        return animal_element;
    }

    //utility method to create text node
    private static Node getAnimalElements(Document doc, Element element, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }



}
