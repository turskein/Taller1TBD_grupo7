package cl.tbd.voluntariadobetbd.repositories.LogRepository;

import cl.tbd.voluntariadobetbd.models.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import java.sql.Date;
import java.util.List;

@Repository
public class LogRepositoryImp implements LogRepository{

    @Autowired
    private Sql2o sql2o;

    @Override
    public List<Log> getAll(){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("SELECT * FROM log").executeAndFetch(Log.class);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Log getById(int id){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("SELECT * FROM log WHERE log.id = :id")
                    .addParameter("id",id)
                    .executeAndFetchFirst(Log.class);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }


    @Override
    public Log post(Log Log){
        try(Connection conn = sql2o.open()){
            //cada vex que haces uno de repositorioimp cambia los parametros que estan en la tabla y luego los pones como esta aqui
            int insertId = (int) conn.createQuery(
                            "INSERT INTO log (id_voluntario, id_tarea, accion, fecha) values  (:LogId_voluntario,:LogId_tarea, :LogAccion, :LogFecha)",true)
                    //Agregar cada de los parametros que pusiste anteriormente
                    .addParameter("LogId_voluntario", Log.getId_voluntario())
                    .addParameter("LogId_tarea", Log.getId_tarea())
                    .addParameter("LogAccion", Log.getAccion())
                    .addParameter("LogFecha", Log.getFecha())
                    .executeUpdate()
                    .getKey();
            Log.setId_Log(insertId);
            return Log;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    @Override
    public Log put(int id, Log Log){
        // despues del set agregar por cada parametro algo como esto "nombre = :nombre"
        final String query = "UPDATE Log SET id_voluntario = :id_voluntario , id_tarea = :id_tarea , accion = :accion , fecha = :fecha WHERE Log.id = :id";

        try(Connection conn = sql2o.open()){
            int insertId = (int) conn.createQuery(query)
                    //agregar parametro con los nombres que se puso anteriormente con el gent adecuado
                    .addParameter("id_voluntario",Log.getId_voluntario())
                    .addParameter("id_tarea",Log.getId_tarea())
                    .addParameter("accion",Log.getAccion())
                    .addParameter("fecha",Log.getFecha())
                    .addParameter("id",id)
                    .executeUpdate()
                    .getKey();
            Log.setId_Log(insertId);
            return Log;

        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    /*
     * if the Log is deleted then the return is 1, otherwise 0
     * */
    @Override
    public int deleteAll(){
        try(Connection conn = sql2o.open()){
            //tiene que ser igual para todos pero con el nombre de la tabla
            conn.createQuery("DELETE FROM log")
                    .executeUpdate();
            return 1;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return 0;
        }
    }

    /*
     * if the Log is deleted then the return is 1,
     * if the Log isn't exists then the return is 0,
     * otherwise -1
     * */
    @Override
    public int deleteById(int id){
        try(Connection conn = sql2o.open()){
            //nombre de la tabla con el nombre del id como esta puesto en la tabla
            int result = conn.createQuery("DELETE FROM log WHERE id = :id")
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
