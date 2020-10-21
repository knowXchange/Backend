package KnowXchange.Backend.Application.Repository;

import org.springframework.data.repository.CrudRepository;
import KnowXchange.Backend.Application.Model.KnowledgeField;

public interface KnowledgeFieldRepository extends CrudRepository<KnowledgeField, Integer> {

}
