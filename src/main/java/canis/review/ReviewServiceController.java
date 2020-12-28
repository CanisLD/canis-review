package canis.review;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/review")
public class ReviewServiceController {

  @Autowired
  private ReviewDataAccess reviewDataAccess;

  @GetMapping("/subject/{subject}")
  public Collection<Review> getReviewBySubject(@PathVariable(value = "subject") String subject) {
    return reviewDataAccess.findBySubject(subject);
  }

  @GetMapping("/{review}")
  public Review getReviewById(@PathVariable(value = "review") String review) {
    final Optional<Review> reviewOptional = reviewDataAccess.findById(review);

    return reviewOptional.orElse(null);
  }

  @PostMapping("/add")
  public String writeReview(@RequestBody Review candidate) {
    return reviewDataAccess.save(candidate);
  }
}
