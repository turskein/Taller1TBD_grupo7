package cl.tbd.voluntariadobetbd.repositories.TaskRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.sql2o.Connection;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.sql2o.Sql2o;

import cl.tbd.voluntariadobetbd.models.Task;

@Repository
public class TaskRepositoryImp implements TaskRepository{

    @Autowired
    private Sql2o sql2o;

    public List<Task> getTasksByIdVolunteer(int idVolunteer){
        String query = "SELECT * FROM task WHERE task.id_volunteer = :id_volunteer";
        try(Connection conn = sql2o.open()){
            List<Task> retorno = conn.createQuery(query)
            .addParameter("id_volunteer", idVolunteer)
            .executeAndFetch(Task.class);
            return retorno;
        }catch(Exception e){
            System.out.print(e.getMessage());
            return null;
        }
    }

    public int updateTaskById(int idTask, String newStatus){
        String query = "UPDATE task SET task.status = :status WHERE task.id_task = :id";
        try(Connection conn = sql2o.open()){
            return (int)conn.createQuery(query)
            .addParameter("status", newStatus)
            .addParameter("id", idTask)
            .executeScalar();
        }catch(Exception e){
            System.out.print(e.getMessage());
            return -1;
        }
    }
}
