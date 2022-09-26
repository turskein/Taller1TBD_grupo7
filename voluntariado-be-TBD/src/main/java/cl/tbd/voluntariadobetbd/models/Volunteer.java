package cl.tbd.voluntariadobetbd.models;

public class Volunteer {
    private Integer id_volunteer;
    private String name;

    public Integer getId_Volunteer(){
        return this.id_volunteer;
    }

    public String getName(){
        return this.name;
    }

    public void setId_Volunteer(Integer newId){
        this.id_volunteer = newId;
    }

    public void setName(String newName){
        this.name = newName;
    }

}
