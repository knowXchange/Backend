/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KnowXchange.Backend.Application.Repository;
import org.springframework.data.repository.CrudRepository;
import KnowXchange.Backend.Application.Model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    
}
