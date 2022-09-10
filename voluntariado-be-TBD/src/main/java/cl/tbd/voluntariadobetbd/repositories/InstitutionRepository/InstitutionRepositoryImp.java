package cl.tbd.voluntariadobetbd.repositories.InstitutionRepository;

import cl.tbd.voluntariadobetbd.models.Institution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class InstitutionRepositoryImp implements InstitutionRepository{

    @Autowired
    private Sql2o sql2o;

    @Override
    public List<Institution> getAll(){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("SELECT * FROM institution").executeAndFetch(Institution.class);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Institution getById(int id){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("SELECT * FROM institution WHERE institution.id_institution = :id")
                    .addParameter("id",id)
                    .executeAndFetchFirst(Institution.class);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }


    @Override
    public Institution post(Institution institution){
        try(Connection conn = sql2o.open()){
            int insertId = (int) conn.createQuery(
                    "INSERT INTO institution (institution) values  (:institutionName)",true)
                    .addParameter("institutionName",institution.getInstitution())
                    .executeUpdate()
                    .getKey();
            institution.setId_institution(insertId);
            return institution;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    @Override
    public Institution put(int id, Institution institution){

        final String query = "UPDATE institution SET institution = :name WHERE institution.id_institution = :id";

        try(Connection conn = sql2o.open()){
            int insertId = (int) conn.createQuery(query)
                    .addParameter("name",institution.getInstitution())
                    .addParameter("id",id)
                    .executeUpdate()
                    .getKey();
            institution.setId_institution(insertId);
            return institution;

        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    /*
     * if the institution is deleted then the return is 1, otherwise 0
     * */
    @Override
    public int deleteAll(){
        try(Connection conn = sql2o.open()){
            conn.createQuery("DELETE FROM institution")
                    .executeUpdate();
            return 1;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return 0;
        }
    }

    /*
    * if the institution is deleted then the return is 1,
    * if the institution isn't exists then the return is 0,
    * otherwise -1
    * */
    @Override
    public int deleteById(int id){
        try(Connection conn = sql2o.open()){
            int result = conn.createQuery("DELETE FROM institution WHERE id_institution = :id")
                    .addParameter("id",id)
                    .executeUpdate()
                    .getResult();
            return result;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return -1;
        }
    };
}
