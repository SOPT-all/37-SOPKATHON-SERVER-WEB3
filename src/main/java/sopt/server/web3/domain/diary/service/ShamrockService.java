package sopt.server.web3.domain.diary.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sopt.server.web3.domain.diary.dto.ShamrockCountResponseDto;
import sopt.server.web3.domain.user.repository.UserSagaCountRepository;
import sopt.server.web3.domain.user.entity.UserSagaCount;
import sopt.server.web3.global.exception.BaseException;
import sopt.server.web3.global.response.error.ErrorCode;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ShamrockService {

  private final UserSagaCountRepository userSagaCountRepository;

  public ShamrockCountResponseDto getShamrockCount(Long userId) {
    UserSagaCount userSagaCount = userSagaCountRepository.findByUser_UserId(userId)
        .orElseThrow(() -> new BaseException(ErrorCode.USER_SAGA_COUNT_NOT_FOUND));

    return ShamrockCountResponseDto.from(userSagaCount);
  }
}