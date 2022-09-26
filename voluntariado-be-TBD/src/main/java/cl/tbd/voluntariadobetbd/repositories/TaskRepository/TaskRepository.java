package cl.tbd.voluntariadobetbd.repositories.TaskRepository;

import java.util.List;

import cl.tbd.voluntariadobetbd.models.Task;

public interface TaskRepository {
    public List<Task> getTasksByIdVolunteer(int idVolunteer);
    public int updateTaskById(int idTask, String newStatus);
}
