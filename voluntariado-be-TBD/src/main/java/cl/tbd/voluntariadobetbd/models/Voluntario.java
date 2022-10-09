package cl.tbd.voluntariadobetbd.models;

import java.sql.Date;

public class Voluntario {
    private Integer id;
    private String nombre;
    private Date fnacimiento;

    public Integer getId(){
        return this.id;
    }

    public String getNombre(){
        return this.nombre;
    }

    public void setId(Integer newId){
        this.id = newId;
    }

    public void setNombre(String newName){
        this.nombre = newName;
    }

    public Date getFnacimiento() {
        return fnacimiento;
    }

    public void setFnacimiento(Date fnacimiento) {
        this.fnacimiento = fnacimiento;
    }
}
