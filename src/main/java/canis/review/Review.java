package canis.review;

import java.time.Instant;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;

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
public class Review {

    @Id
    private @Getter String reviewId;
    private @Getter String subjectId;
    private @Getter String reviewerId;
    private @Getter Instant created;
    private @Getter Instant updated;

    @Enumerated(EnumType.STRING)
    private @Getter Status status;

    @Column(length=1024)
    private @Getter String header;

    @Column(length=1024)
    private @Getter String byline;

    @Column(length=65536)
    private @Getter String body;

    @Column(length = 4096)
    private @Getter String caption;
  
    @Column(length = 2048)
    private @Getter String link;    

    @OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name = "reviewId", nullable = true)
    private @Getter Set<Rating> rating;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="tag", joinColumns=@JoinColumn(name="reviewId"))
    private @Getter Set<String> tag;   
}