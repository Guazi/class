import com.sun.org.apache.xml.internal.utils.XMLString;
import org.omg.CORBA.DynAnyPackage.Invalid;

import java.util.Date;

public class ZooManager {
    public static void main(String[] args) {

        ZooOrganizer myZoo = new ZooOrganizer();


        try {
            myZoo.openFile("animals.xml");
        } catch (InvalidAnimalException ex) {
            System.out.println("The file you tried to load has invalid class" + ex);
            System.exit(0);
        }

        myZoo.saveFile("animalsout.xml");


        // System will exit if InvalidAnimalException per the domain requirements.
//        try {
//            myZoo.openFile("animalsbad.xml");
//        } catch (InvalidAnimalException ex) {
//            System.out.println("The file you tried to load has invalid class" + ex);
//            System.exit(0);
//        }
//
//
//        myZoo.saveFile("animalsout.xml");


////
        Animal carl = new Lion("carls", new Date("08/10/2013"), 5, "Lion");
        myZoo.addAnimal(carl);

        Animal jackie = new Pig("jackie", new Date("08/10/2006"), 12, "Pig");
        myZoo.addAnimal(jackie);

        // ZooOrganizer line 64, we skip loading an animal with a bad species
        Animal bad_species = new Pig("jackie", new Date("08/10/2006"), 12, "Dog");
        myZoo.addAnimal(bad_species);


        String xml =    " <Animal>" +
                        "   <name>John</name>" +
                        "   <birthDate>08/10/1985</birthDate>" +
                        "   <age>32</age>" +
                        "   <species>Turtle</species>" +
                        " </Animal>";


        myZoo.addAnimal(xml);



//        myZoo.deleteAnimal(carl);
//

        // Here we can add a new animal and replace the existing animal at index 1
        Animal newcarl = new Eagle("carl", new Date("06/18/1986"), 44, "Eagle");
        myZoo.updateAnimal(1, newcarl);


        // Here we an get an existing animal and modify its properties with getters/setters
        Animal newanimal = myZoo.getAnimal(1);
        newanimal.setAge(1);

        System.out.println("Jackie will live this much longer :" + jackie.calculateLifeExpectancy());
//
        try {
            myZoo.saveFile("animalsout.xml");
        } catch (InvalidAnimalException ex) {
            System.out.println("The file you tried to load has invalid class" + ex);
        }


    }
}
