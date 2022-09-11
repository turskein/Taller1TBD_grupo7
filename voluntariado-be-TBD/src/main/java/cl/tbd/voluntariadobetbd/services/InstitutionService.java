package cl.tbd.voluntariadobetbd.services;

import cl.tbd.voluntariadobetbd.models.Institution;
import cl.tbd.voluntariadobetbd.repositories.InstitutionRepository.InstitutionRepository;

import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/institutions")
public class InstitutionService {

    private final InstitutionRepository institutionRepository;
    InstitutionService(InstitutionRepository institutionRepository){
        this.institutionRepository = institutionRepository;
    }

    @GetMapping()
    public List<Institution> getAll(){
        return this.institutionRepository.getAll();
    }

    @GetMapping("/{id}")
    public Institution getById(@PathVariable("id") int id){
        return this.institutionRepository.getById(id);
    }


    @PostMapping()
    @ResponseBody
    public Institution postById(@RequestBody Institution institution){
        return this.institutionRepository.post(institution);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public Institution putById(@PathVariable("id") int id, @RequestBody Institution institution ) {
        return this.institutionRepository.put(id, institution);
    }

    @DeleteMapping()
    public Map<String, String> deleteAll(){
        int code = this.institutionRepository.deleteAll();
        HashMap<String, String> response = new HashMap<>();
        if(code == 1){
            response.put("status", "200");
            response.put("response", "every institution was deleted");
        }else{
            response.put("status", "400");
            response.put("error", "something went wrong");
        }

        return response;
    }

    @DeleteMapping("/{id}")
    public Map<String, String> deleteById(@PathVariable("id") int id){
        int code = this.institutionRepository.deleteById(id);
        HashMap<String, String> response = new HashMap<>();
        if(code == 1){
            response.put("status", "200");
            response.put("response", "the institution with id_institution equal to "
                    + Integer.toString(id)
                    + " was deleted");
        } else if (code == 0) {
            response.put("status", "409");
            response.put("error", "there arent a institutions with id_institution equal to "
                        + Integer.toString(id));
        } else{
            response.put("status", "400");
            response.put("response", "something went wrong");
        }

        return response;
    }
}
