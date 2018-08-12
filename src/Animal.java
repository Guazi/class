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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getXMLString() {
        return XMLString;
    }

    public void setXMLString(String XMLString) {
        this.XMLString = XMLString;
    }

    private String name;
    private Date birthDate;
    private Integer age;
    private String species;
    private String XMLString;

    public Animal(String name, Date birthDate, Integer age, String species) {
        this.name = name;
        this.birthDate = birthDate;
        this.age = age;
        this.species = species;
    }

    public Animal(String XMLString) {
        this.XMLString = XMLString;
    }

    abstract public Integer calculateLifeExpectancy();

}
