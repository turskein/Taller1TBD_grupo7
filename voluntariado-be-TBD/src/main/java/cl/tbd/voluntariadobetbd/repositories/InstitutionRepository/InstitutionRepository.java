package cl.tbd.voluntariadobetbd.repositories.InstitutionRepository;

import java.util.List;
import cl.tbd.voluntariadobetbd.models.Institution;

public interface InstitutionRepository {
    public List<Institution> getAll();
    public Institution getById(int id);
    public Institution post(Institution institution);
    public Institution put(int id, Institution institution);
    public int deleteAll();
    public int deleteById(int id);
}
