import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Zoo {


    private ArrayList<Animal> animalList;

    public Zoo() {
        this.animalList = new ArrayList<Animal>();
    }

    public ArrayList<Animal> addAnimal(Animal zooAnimal) {
        this.animalList.add(zooAnimal);
        return animalList;
    }

    public ArrayList<Animal> addAnimal(String XMLString) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(XMLString));

            Document doc = db.parse(is);
            NodeList nodes = doc.getElementsByTagName("Animal");
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

            for (int i = 0; i < nodes.getLength(); i++) {
                try {
                    Element elem = (Element) nodes.item(i);
                    String species = elem.getElementsByTagName("species").item(0).getTextContent();
                    Class cls = Class.forName(species);
                    Constructor c = cls.getConstructor(String.class, Date.class, Integer.class, String.class);
                    String name = elem.getElementsByTagName("name").item(0).getTextContent();
                    String birthdate = elem.getElementsByTagName("birthDate").item(0).getTextContent();
                    Date convertedDate = dateFormat.parse(birthdate);
                    int age = Integer.parseInt(elem.getElementsByTagName("age").item(0).getTextContent());
                    Animal animal = (Animal) c.newInstance(name, convertedDate, age, species);
                    this.animalList.add(animal);
                } catch(ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException ex) {
                    System.out.println(ex.toString());
                    throw new InvalidAnimalException("The animal you tried adding with XML has an invalid species");
                }


            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return animalList;
    }

    public ArrayList<Animal> deleteAnimal(Animal zooAnimal) {
        this.animalList.remove(zooAnimal);
        return animalList;
    }

    public ArrayList<Animal> updateAnimal(int id, Animal zooAnimal) {
        this.animalList.set(1, zooAnimal);
//        Loop through array list, check each item for name, return index of match
        return animalList;
    }

    public ArrayList<Animal> getAnimals() {
        return animalList;
    }

    public Animal getAnimal(int id) {
        return animalList.get(id);
    }

}
