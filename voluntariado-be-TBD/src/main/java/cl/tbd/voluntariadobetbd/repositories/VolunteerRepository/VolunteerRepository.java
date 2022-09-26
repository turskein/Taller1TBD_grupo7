package cl.tbd.voluntariadobetbd.repositories.VolunteerRepository;

import cl.tbd.voluntariadobetbd.models.Volunteer;

public interface VolunteerRepository {
    public Volunteer getByName(String name);
    public Volunteer getById(int id);
}
