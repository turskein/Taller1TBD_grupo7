package cl.tbd.voluntariadobetbd.services;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import cl.tbd.voluntariadobetbd.models.Task;
import cl.tbd.voluntariadobetbd.repositories.TaskRepository.TaskRepository;

@CrossOrigin
@RestController
@RequestMapping("/tasks")
public class TaskService {
    private final TaskRepository taskRepository;

    TaskService(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    @GetMapping()
    public List<Task> getTasksByIdVolunteer(@RequestParam(name = "id_volunteer") int id_volunteer){
        return taskRepository.getTasksByIdVolunteer(id_volunteer);
    }

    @GetMapping("/{id}")

    @PutMapping("/{idtask}")
    @ResponseBody
    public int putNewStatus(@PathVariable("idtask") int idtask, @RequestParam String newStatus){
        return taskRepository.updateTaskById(idtask, newStatus);
    }

}
