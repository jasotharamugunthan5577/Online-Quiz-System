package model;

public class Domain {

    public int id;
    public String name;

    public Domain(int id, String name){
        this.id = id;
        this.name = name;
    }

    public String toString(){
        return name;
    }
}