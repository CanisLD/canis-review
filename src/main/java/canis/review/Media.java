package canis.review;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

// JPA
@Entity

// lombok builder
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Media {

  public static enum MediaType {
    IMAGE,
    EMBED_HTML
  }
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private @Getter Long mediaId;
  
  private @Getter MediaType type;
 
  @Column(length=66536) 
  private @Getter String reference;

  @Column(length=4096)
  private @Getter String caption;
}
