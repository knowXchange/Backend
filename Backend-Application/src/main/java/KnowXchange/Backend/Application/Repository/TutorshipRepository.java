package KnowXchange.Backend.Application.Repository;

import org.springframework.data.repository.CrudRepository;
import KnowXchange.Backend.Application.Model.Tutorship;

public interface TutorshipRepository extends CrudRepository<Tutorship, Integer> {
    
}