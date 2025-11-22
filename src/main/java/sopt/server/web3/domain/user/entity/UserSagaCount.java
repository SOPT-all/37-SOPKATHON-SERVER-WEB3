package sopt.server.web3.domain.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "user_saga_counts")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserSagaCount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "static_id")
    private Long staticId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "faith_count", nullable = false)
    private Integer faithCount = 0;

    @Column(name = "hope_count", nullable = false)
    private Integer hopeCount = 0;

    @Column(name = "love_count", nullable = false)
    private Integer loveCount = 0;

    @Builder
    private UserSagaCount(User user, Integer faithCount, Integer hopeCount, Integer loveCount) {
        this.user = user;
        this.faithCount = faithCount != null ? faithCount : 0;
        this.hopeCount = hopeCount != null ? hopeCount : 0;
        this.loveCount = loveCount != null ? loveCount : 0;
    }

    public void incrementFaithCount() {
        this.faithCount++;
    }

    public void incrementHopeCount() {
        this.hopeCount++;
    }

    public void incrementLoveCount() {
        this.loveCount++;
    }
}
