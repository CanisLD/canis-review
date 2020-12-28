package canis.review;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ReviewDataAccess {

  @Autowired
  private ReviewRepository reviewRepository;

  public Collection<Review> findBySubject(String subject) {
    return reviewRepository.findBySubject(subject);
  }

  public void save(Review review) {
    reviewRepository.save(review);
  }
  
}
