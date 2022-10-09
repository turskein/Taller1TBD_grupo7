package cl.tbd.voluntariadobetbd.repositories.VoluntarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.sql2o.Connection;
import org.springframework.stereotype.Repository;
import org.sql2o.Sql2o;

import cl.tbd.voluntariadobetbd.models.Voluntario;

import java.util.List;

@Repository
public class VoluntarioRepositoryImp implements VoluntarioRepository {

    @Autowired
    private Sql2o sql2o;

    @Override
    public Voluntario getByName(String name){
        String query = "SELECT * FROM voluntario WHERE voluntario.nombre = :name";
        try(Connection conn = sql2o.open()){
            return conn.createQuery(query)
            .addParameter("name", name)
            .executeAndFetchFirst(Voluntario.class);
        }catch(Exception e){
            System.out.print(e.getMessage());
            return null;
        }
    }

    @Override
    public Voluntario getById(int id){
        String query = "SELECT * FROM volunteer WHERE voluntario.id = :id";
        try(Connection conn = sql2o.open()){
            return conn.createQuery(query)
            .addParameter("id", id)
            .executeAndFetchFirst(Voluntario.class);
        }catch(Exception e){
            System.out.print(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Voluntario> getAll(){
        String query = "SELECT * FROM voluntario";
        try(Connection conn = sql2o.open()){
            return conn.createQuery(query)
                    .executeAndFetch(Voluntario.class);
        }catch(Exception e){
            System.out.print(e.getMessage());
            return null;
        }
    }

    @Override
    public Voluntario post(Voluntario voluntario){
        String query = "INSERT INTO voluntario (nombre, fnacimiento) values  (:nombre,:fnacimiento)";
        try(Connection conn = sql2o.open()){
            int newId = (int)conn.createQuery(query,true)
                    .addParameter("nombre",voluntario.getNombre())
                    .addParameter("fnacimiento",voluntario.getFnacimiento())
                    .executeUpdate()
                    .getKey();
            voluntario.setId(newId);
            return voluntario;
        }catch(Exception e){
            System.out.print(e.getMessage());
            return null;
        }
    }
    @Override
    public Voluntario put(int id, Voluntario voluntario){
        final String query = "UPDATE voluntario SET nombre = :nombre, fnacimiento = :fnacimiento WHERE voluntario.id = :id";

        try(Connection conn = sql2o.open()){
            int insertId = (int) conn.createQuery(query)
                    //agregar parametro con los nombres que se puso anteriormente con el gent adecuado
                    .addParameter("nombre",voluntario.getNombre())
                    .addParameter("fnacimiento",voluntario.getFnacimiento())
                    .executeUpdate()
                    .getKey();
            voluntario.setId(insertId);
            return voluntario;

        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    /*
     * if the Eme_habilidad is deleted then the return is 1, otherwise 0
     * */
    @Override
    public int deleteAll(){
        try(Connection conn = sql2o.open()){
            //tiene que ser igual para todos pero con el nombre de la tabla
            conn.createQuery("DELETE FROM voluntario")
                    .executeUpdate();
            return 1;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return 0;
        }
    }

    /*
     * if the Eme_habilidad is deleted then the return is 1,
     * if the Eme_habilidad isn't exists then the return is 0,
     * otherwise -1
     * */
    @Override
    public int deleteById(int id){
        try(Connection conn = sql2o.open()){
            //nombre de la tabla con el nombre del id como esta puesto en la tabla
            int result = conn.createQuery("DELETE FROM voluntario WHERE id = :id")
                    .addParameter("id",id)
                    .executeUpdate()
                    .getResult();
            return 1;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return -1;
        }
    }

}
