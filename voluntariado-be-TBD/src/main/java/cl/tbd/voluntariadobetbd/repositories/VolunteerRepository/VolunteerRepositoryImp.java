package cl.tbd.voluntariadobetbd.repositories.VolunteerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.sql2o.Connection;
import org.springframework.stereotype.Repository;
import org.sql2o.Sql2o;

import cl.tbd.voluntariadobetbd.models.Volunteer;

@Repository
public class VolunteerRepositoryImp implements VolunteerRepository{

    @Autowired
    private Sql2o sql2o;

    @Override
    public Volunteer getByName(String name){
        String query = "SELECT * FROM volunteer WHERE volunteer.name = :name";
        try(Connection conn = sql2o.open()){
            return conn.createQuery(query)
            .addParameter("name", name)
            .executeAndFetchFirst(Volunteer.class);
        }catch(Exception e){
            System.out.print(e.getMessage());
            return null;
        }
    }

    @Override
    public Volunteer getById(int id){
        String query = "SELECT * FROM volunteer WHERE volunteer.id_volunteer = :id";
        try(Connection conn = sql2o.open()){
            return conn.createQuery(query)
            .addParameter("id", id)
            .executeAndFetchFirst(Volunteer.class);
        }catch(Exception e){
            System.out.print(e.getMessage());
            return null;
        }
    }


}
