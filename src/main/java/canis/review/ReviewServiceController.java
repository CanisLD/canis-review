package canis.review;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/review")
public class ReviewServiceController {

  @Autowired
  private ReviewDataAccess reviewDataAccess;

  @GetMapping("/subject/{subject}")
  public Page<Review> getReviewBySubject(
    @PathVariable(value = "subject") String subject,
    @RequestParam(name = "page", required = true, defaultValue = "0") Integer page,
    @RequestParam(name = "size", required = true, defaultValue = "10") Integer size) {
    return reviewDataAccess.findBySubject(subject, page, size);
  }

  @GetMapping("/{review}")
  public Review getReviewById(@PathVariable(value = "review") String review) {
    final Optional<Review> reviewOptional = reviewDataAccess.findById(review);

    return reviewOptional.orElse(null);
  }

  @PostMapping("/save")
  public String writeReview(@RequestBody Review candidate) {
    return reviewDataAccess.save(candidate);
  }
}
