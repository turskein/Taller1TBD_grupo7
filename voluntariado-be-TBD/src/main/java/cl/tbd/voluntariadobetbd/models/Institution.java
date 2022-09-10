package cl.tbd.voluntariadobetbd.models;

public class Institution {
    private Integer id_institution;
    private String institution;

    public void setId_institution(Integer id_institution) {
        this.id_institution = id_institution;
    }

    public Integer getId_institution() {
        return id_institution;
    }

    public String getInstitution() {
        return institution;
    }

    public void setIntitution(String intitution) {
        this.institution = intitution;
    }
}
