package canis.review;

import java.time.Instant;

import org.springframework.stereotype.Component;

@Component
public class MockReview {

  public static String REVIEW_ID = "MockedReviewId";
  public static String SUBJECT_ID = "MockedSubjectId";

  public Review mock() {
    final Instant now = Instant.now();

    final Review submission = Review.builder()
      .reviewId(REVIEW_ID)
      .subjectId(SUBJECT_ID)
      .reviewerId("MockedReviewerId")
      .created(now)
      .updated(now)
      .status(Review.Status.PUBLISHED)
      .header("Sample Review")
      .byline("by copywriter")
      .copy("This is a mock copy for the review.")
      .referenceType(Review.ReferenceType.EMBED_HTML)
      .reference("<div>Mocked Embedded Reference</div>")
      .rating(0.5)
      .build();
    
    return submission;
  }
}
