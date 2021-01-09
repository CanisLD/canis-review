package canis.review;

import org.springframework.data.repository.CrudRepository;

public interface RatingRepository extends CrudRepository<Rating, String> {
  
// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

}
