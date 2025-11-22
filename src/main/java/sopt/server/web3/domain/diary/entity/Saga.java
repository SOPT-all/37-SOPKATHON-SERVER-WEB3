package sopt.server.web3.domain.diary.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sopt.server.web3.global.entity.BaseTimeEntity;
import sopt.server.web3.domain.user.entity.User;

@Entity
@Getter
@Table(name = "sagas")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Saga extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "saga_id")
    private Long sagaId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diary_id", nullable = false)
    private Diary diary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "saga_content", nullable = false, columnDefinition = "TEXT")
    private String sagaContent;

    @Builder
    private Saga(Diary diary, User user, String sagaContent) {
        this.diary = diary;
        this.user = user;
        this.sagaContent = sagaContent;
    }
}
