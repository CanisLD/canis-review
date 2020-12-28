package canis.review;

import java.time.Instant;
import java.util.Collection;
// import java.util.List;
import java.util.UUID;

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

  @GetMapping("/{subject}")
  public Collection<Review> getReview(
    @PathVariable(value = "subject") String subject) {
    return reviewDataAccess.findBySubject(subject);
    // return List.of(new Review.ReviewBuilder()
    //   .instant(Instant.now())
    //   .author("Smith")
    //   .subject(subject)
    //   .score(0.7f)
    //   .header("header")
    //   .body("Ipsum Lorem")
    //   .tag(List.of("English","Test"))
    //   .build());    
  }

  @PostMapping("/add")
  public String writeReview(@RequestBody Review candidate) {
    final Review review = new Review.ReviewBuilder()
      .instant(Instant.now())
      .review(UUID.randomUUID().toString())
      .author(candidate.getAuthor())
      .subject(candidate.getSubject())
      .header(candidate.getHeader())
      .byline(candidate.getByline())
      .body(candidate.getBody())
      .link(candidate.getLink())
      .score(candidate.getScore())
      .tag(candidate.getTag())
      .build();
    reviewDataAccess.save(review);
    return review.getReview();
  }
}
