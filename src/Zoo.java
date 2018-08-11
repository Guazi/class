import java.util.*;

public class Zoo {


    private ArrayList<Animal> animalList;

    public Zoo() {
        this.animalList = new ArrayList<Animal>();
    }

    public int addAnimal(Animal zooAnimal) {
        this.animalList.add(zooAnimal);
        return animalList.size() - 1;
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
