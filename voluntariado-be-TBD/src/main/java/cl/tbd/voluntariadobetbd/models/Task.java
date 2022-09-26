package cl.tbd.voluntariadobetbd.models;

public class Task {
    private int id_task;
    private int id_volunteer;
    private String status;

    private String name;

    public int getId_task(){
        return this.id_task;
    }

    public int getId_volunteer(){
        return this.id_volunteer;
    }

    public String getStatus(){
        return this.status;
    }

    public String getName() {
        return this.name;
    }

    public void setId_task(int newId){
        this.id_task = newId;
    }

    public void setId_volunteer(int newIdVolunteer){
        this.id_volunteer = newIdVolunteer;
    }

    public void setStatus(String newStatus){
        this.status = newStatus;
    }

    public void setName(String name) {
        this.name = name;
    }
}
