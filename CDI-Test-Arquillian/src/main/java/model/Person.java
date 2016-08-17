package model;

/**
 * Created by gsantos on 14/09/15.
 */
public class Person extends BaseEntity {

    private int id;

    public Person(int id){
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }
}
