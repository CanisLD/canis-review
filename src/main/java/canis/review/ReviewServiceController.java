package canis.review;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
  public ResponseEntity<Page<Review>> getReviewBySubjectId(
    @PathVariable(value = "subject") String subject,
    @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
    @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
    Page<Review> pageReview = reviewDataAccess.findBySubjectId(subject, page, size);
    return new ResponseEntity<>(pageReview, HttpStatus.OK);
  }

  @GetMapping("/{review}")
  public ResponseEntity<Review> getReviewById(@PathVariable(value = "review") String review) {
    final Optional<Review> reviewOptional = reviewDataAccess.findById(review);

    return reviewOptional
      .map(r -> new ResponseEntity<>(r, HttpStatus.OK))
      .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @PostMapping("/save")
  public ResponseEntity<Review> postReview(@RequestBody Review candidate) {
    final Review submission = reviewDataAccess.save(candidate);
    return new ResponseEntity<>(submission, HttpStatus.CREATED);
  }
}
