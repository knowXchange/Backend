package KnowXchange.Backend.Application.Repository;

import org.springframework.data.repository.CrudRepository;
import KnowXchange.Backend.Application.Model.Review;

public interface ReviewRepository extends CrudRepository <Review, Integer>{

}