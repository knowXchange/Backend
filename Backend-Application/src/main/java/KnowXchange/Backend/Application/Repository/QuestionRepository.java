package KnowXchange.Backend.Application.Repository;

import org.springframework.data.repository.CrudRepository;
import KnowXchange.Backend.Application.Model.Question;

public interface QuestionRepository extends CrudRepository <Question, Integer>{

}
