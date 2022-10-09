package cl.tbd.voluntariadobetbd.repositories.TareaRepository;

import cl.tbd.voluntariadobetbd.models.Voluntario;
import org.springframework.beans.factory.annotation.Autowired;
import org.sql2o.Connection;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.sql2o.Sql2o;

import cl.tbd.voluntariadobetbd.models.Tarea;

@Repository
public class TareaRepositoryImp implements TareaRepository {

    @Autowired
    private Sql2o sql2o;

    @Override
    public Tarea getTareaById(int id){
        String query = "SELECT * FROM tarea WHERE tarea.id = :idTask";
        try(Connection conn = sql2o.open()){
            return conn.createQuery(query)
                    .addParameter("idTask",id)
                    .executeAndFetchFirst(Tarea.class);
        }catch(Exception e){
            System.out.print(e.getMessage());
            return null;
        }
    }
    @Override
    public List<Tarea> getAllTareas(){
        String query = "SELECT * FROM tarea";
        try(Connection conn = sql2o.open()){
            return conn.createQuery(query)
                    .executeAndFetch(Tarea.class);
        }catch(Exception e){
            System.out.print(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Tarea> getTareasByIdVoluntario(int idVoluntario){
        String query = "SELECT tarea.id, " +
                        "tarea.nombre, " +
                        "tarea.descrip, " +
                        "tarea.cant_vol_requeridos, " +
                        "tarea.cant_vol_inscritos, " +
                        "tarea.id_emergencia, " +
                        "tarea.finicio, " +
                        "tarea.ffin, " +
                        "tarea.id_estado "+
                        "FROM  tarea, ranking WHERE :id_voluntario = ranking.id_voluntario AND ranking.id_tarea = tarea.id";
        try(Connection conn = sql2o.open()){
            return conn.createQuery(query)
            .addParameter("id_voluntario", idVoluntario)
            .executeAndFetch(Tarea.class);
        }catch(Exception e){
            System.out.print(e.getMessage());
            return null;
        }
    }

    @Override
    public int updateTareaById(int idTarea, String newStatus){
        String query = "UPDATE estado_tarea SET estado_tarea.descrip = :new_status WHERE estado_tarea.id_task = :id";
        try(Connection conn = sql2o.open()){
            conn.createQuery(query,true)
            .addParameter("new_status", newStatus)
            .addParameter("id", idTarea)
            .executeScalar();
            return 1;
        }catch(Exception e){
            System.out.print(e.getMessage());
            return 0;
        }
    }

    @Override
    public int deleteById(int id){
        try(Connection conn = sql2o.open()){
            conn.createQuery("DELETE FROM tarea WHERE tarea.id = :id")
                    .addParameter("id",id)
                    .executeUpdate()
                    .getResult();
            return 1;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return 0;
        }
    }
    @Override
    public int deleteAll(){
        try(Connection conn = sql2o.open()){
            conn.createQuery("DELETE FROM tarea")
                    .executeUpdate();
            return 1;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return 0;
        }
    }
    @Override
    public Tarea post(Tarea newTarea){
        String query = "INSERT INTO tarea (nombre," +
                                        "descrip, " +
                                        "cant_vol_requeridos," +
                                        "cant_vol_inscritos," +
                                        "id_emergencia," +
                                        "finicio," +
                                        "ffin)" +
                                        "VALUES" +
                                        "(:nombre," +
                                        ":descrip, " +
                                        ":cant_vol_requeridos," +
                                        ":cant_vol_inscritos," +
                                        ":id_emergencia," +
                                        ":finicio," +
                                        ":ffin)";
        try(Connection conn = sql2o.open()){
            int newId=(int)conn.createQuery(query,true)
                    .addParameter("nombre", newTarea.getNombre())
                    .addParameter("descrip", newTarea.getDescrip())
                    .addParameter("cant_vol_requeridos", newTarea.getCant_vol_inscritos())
                    .addParameter("cant_vol_inscritos", newTarea.getCant_vol_inscritos())
                    .addParameter("id_emergencia_numeric", newTarea.getId_emergencia())
                    .addParameter("finicio", newTarea.getFinicio())
                    .addParameter("ffin", newTarea.getFfin())
                    .executeUpdate()
                    .getKey();
            newTarea.setId(newId);
            return newTarea;
        }catch(Exception e){
            System.out.print(e.getMessage());
            return null;
        }
    }

    @Override
    public Tarea put(int id, Tarea newTarea){
        final String query = "UPDATE Ranking SET nombre = :nombre, descrip = :descrip, cant_vol_requeridos= :cant_vol_requeridos" +
                "cant_vol_inscritos=:cant_vol_inscritos,id_emergencia=:id_emergencia, finicio=:finicio, ffin=:ffin WHERE Ranking.id = :id";
        try(Connection conn = sql2o.open()){
            conn.createQuery(query,true)
                    .addParameter("nombre", newTarea.getNombre())
                    .addParameter("descrip", newTarea.getDescrip())
                    .addParameter("cant_vol_requeridos", newTarea.getCant_vol_inscritos())
                    .addParameter("cant_vol_inscritos", newTarea.getCant_vol_inscritos())
                    .addParameter("id_emergencia_numeric", newTarea.getId_emergencia())
                    .addParameter("finicio", newTarea.getFinicio())
                    .addParameter("ffin", newTarea.getFfin())
                    .addParameter(":id",id)
                    .executeUpdate()
                    .getKey();
            return newTarea;
        }catch(Exception e){
            System.out.print(e.getMessage());
            return null;
        }
    }
}
