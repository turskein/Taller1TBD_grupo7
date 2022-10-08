package cl.tbd.voluntariadobetbd.repositories.EmergenciaRepository;

import cl.tbd.voluntariadobetbd.models.Emergencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class EmergenciaRepositoryImp implements EmergenciaRepository{

    @Autowired
    private Sql2o sql2o;

    @Override
    public List<Emergencia> getAll(){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("SELECT * FROM emergencia").executeAndFetch(Emergencia.class);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Emergencia getById(int id){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("SELECT * FROM emergencia WHERE emergencia.id = :id")
                    .addParameter("id",id)
                    .executeAndFetchFirst(Emergencia.class);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }


    @Override
    public Emergencia post(Emergencia Emergencia){
        try(Connection conn = sql2o.open()){
            //cada vex que haces uno de repositorioimp cambia los parametros que estan en la tabla y luego los pones como esta aqui
            int insertId = (int) conn.createQuery(
                            "INSERT INTO emergencia (nombre, descrip, finicio, ffin, id_institucion) values  (:EmergenciaNombre,:EmergenciaDescrip, :EmergenciaFinicio, :EmergenciaFfin, :EmergenciaId_institucion)",true)
                    //Agregar cada de los parametros que pusiste anteriormente
                    .addParameter("EmergenciaNombre", Emergencia.getNombre())
                    .addParameter("EmergenciaDescrip", Emergencia.getDescrip())
                    .addParameter("EmergenciaFinicio", Emergencia.getFinicio())
                    .addParameter("EmergenciaFfin", Emergencia.getFfin())
                    .addParameter("EmergenciaId_institucion", Emergencia.getId_institucion())
                    .executeUpdate()
                    .getKey();
            Emergencia.setId_Emergencia(insertId);
            return Emergencia;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    @Override
    public Emergencia put(int id, Emergencia Emergencia){
        // despues del set agregar por cada parametro algo como esto "nombre = :nombre"
        final String query = "UPDATE Emergencia SET nombre = :nombre , descrip = :descrip , finicio = :finicio , ffin = :ffin , id_institucion = :id_institucion WHERE Emergencia.id = :id";

        try(Connection conn = sql2o.open()){
            int insertId = (int) conn.createQuery(query)
                    //agregar parametro con los nombres que se puso anteriormente con el gent adecuado
                    .addParameter("nombre",Emergencia.getNombre())
                    .addParameter("descrip",Emergencia.getDescrip())
                    .addParameter("finicio",Emergencia.getFinicio())
                    .addParameter("ffin",Emergencia.getFfin())
                    .addParameter("id_institucion",Emergencia.getId_institucion())
                    .addParameter("id",id)
                    .executeUpdate()
                    .getKey();
            Emergencia.setId_Emergencia(insertId);
            return Emergencia;

        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    /*
     * if the Emergencia is deleted then the return is 1, otherwise 0
     * */
    @Override
    public int deleteAll(){
        try(Connection conn = sql2o.open()){
            //tiene que ser igual para todos pero con el nombre de la tabla
            conn.createQuery("DELETE FROM emergencia")
                    .executeUpdate();
            return 1;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return 0;
        }
    }

    /*
     * if the Emergencia is deleted then the return is 1,
     * if the Emergencia isn't exists then the return is 0,
     * otherwise -1
     * */
    @Override
    public int deleteById(int id){
        try(Connection conn = sql2o.open()){
            //nombre de la tabla con el nombre del id como esta puesto en la tabla
            int result = conn.createQuery("DELETE FROM emergencia WHERE id = :id")
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
