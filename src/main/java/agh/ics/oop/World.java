package agh.ics.oop;

public class World {
    public static void main(String[] args) {
        Animal pet = new Animal();

/*  Z ćwiczeń 3 i 5:
        System.out.println(pet.getPosition().toString());
        pet.move(MoveDirection.RIGHT);
        pet.move(MoveDirection.FORWARD);
        pet.move(MoveDirection.FORWARD);
        pet.move(MoveDirection.FORWARD);
*/
        
        OptionsParser parser = new OptionsParser();
        for (MoveDirection direction : parser.parse(args)) {
            pet.move(direction);
        }

        System.out.println(pet.toString());
    }
}
