package cl.tbd.voluntariadobetbd.models;
import java.sql.Date;

public class Habilidad {
    private Integer id;
    private String descrip;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }
}
