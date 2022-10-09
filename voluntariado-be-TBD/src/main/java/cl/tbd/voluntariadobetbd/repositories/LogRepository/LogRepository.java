package cl.tbd.voluntariadobetbd.repositories.LogRepository;

import java.util.List;
import java.sql.Date;
import cl.tbd.voluntariadobetbd.models.Log;

public interface LogRepository {
    public List<Log> getAll();
    public Log getById(int id);
    public Log post(Log Log);
    public Log put(int id, Log Log);
    public int deleteAll();
    public int deleteById(int id);
}
