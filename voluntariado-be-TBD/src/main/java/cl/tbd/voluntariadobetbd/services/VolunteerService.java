package cl.tbd.voluntariadobetbd.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import cl.tbd.voluntariadobetbd.models.Volunteer;
import cl.tbd.voluntariadobetbd.repositories.VolunteerRepository.VolunteerRepository;

@CrossOrigin
@RestController
@RequestMapping("/volunteers")
public class VolunteerService {
    private final VolunteerRepository volunteerRepository;
    VolunteerService(VolunteerRepository volunteerRepository){
        this.volunteerRepository = volunteerRepository;
    }

    @GetMapping()
    public Volunteer getByName(@RequestParam String name){
        return volunteerRepository.getByName(name);
    }

    @GetMapping("/{id}")
    public Volunteer getById(@PathVariable("id") int id){
        return volunteerRepository.getById(id);
    }
}
