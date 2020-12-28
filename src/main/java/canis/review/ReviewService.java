package canis.review;

import java.util.List;

public interface ReviewService {

  List<Review> searchReview(List<String> tag);

  List<Review> getReview(String subject);

  List<Review> writeReview(List<Review> review);
}