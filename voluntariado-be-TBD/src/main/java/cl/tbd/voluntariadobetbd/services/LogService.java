package cl.tbd.voluntariadobetbd.services;

import cl.tbd.voluntariadobetbd.models.Log;
import cl.tbd.voluntariadobetbd.repositories.LogRepository.LogRepository;

import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

//usa control+R para cambiar altiro los nombres pero recuerda usar al "Cc" para cambiarlo apropiadamente

@CrossOrigin
@RestController
//cambiar el nombre de aca con el nombre de la tabla
@RequestMapping("/log")
public class LogService {

    //no hay que cambiar nada mas alla que los nombres de con Control R
    private final LogRepository logRepository;
    LogService(LogRepository logRepository){
        this.logRepository = logRepository;
    }

    @GetMapping()
    public List<Log> getAll(){
        return this.logRepository.getAll();
    }

    @GetMapping("/{id}")
    public Log getById(@PathVariable("id") int id){
        return this.logRepository.getById(id);
    }


    @PostMapping()
    @ResponseBody
    public Log postById(@RequestBody Log log) {
        return this.logRepository.post(log);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public Log putById(@PathVariable("id") int id, @RequestBody Log log ) {
        return this.logRepository.put(id, log);
    }

    @DeleteMapping()
    public Map<String, String> deleteAll(){
        int code = this.logRepository.deleteAll();
        HashMap<String, String> response = new HashMap<>();
        if(code == 1){
            response.put("status", "200");
            response.put("response", "every log was deleted");
        }else{
            response.put("status", "400");
            response.put("error", "something went wrong");
        }

        return response;
    }

    @DeleteMapping("/{id}")
    public Map<String, String> deleteById(@PathVariable("id") int id){
        int code = this.logRepository.deleteById(id);
        HashMap<String, String> response = new HashMap<>();
        if(code == 1){
            response.put("status", "200");
            response.put("response", "the log with id_log equal to "
                    + Integer.toString(id)
                    + " was deleted");
        } else if (code == 0) {
            response.put("status", "409");
            response.put("error", "there arent a logs with id_log equal to "
                    + Integer.toString(id));
        } else{
            response.put("status", "400");
            response.put("response", "something went wrong");
        }

        return response;
    }
}