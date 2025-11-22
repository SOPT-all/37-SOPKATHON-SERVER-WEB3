package sopt.server.web3.domain.openai.strategy;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import sopt.server.web3.domain.diary.entity.LeafType;

@Component
@RequiredArgsConstructor
public class PromptStrategyFactory {

	private final FaithPromptStrategy faithPromptStrategy;
	private final LovePromptStrategy lovePromptStrategy;
	private final HopePromptStrategy hopePromptStrategy;

	public PromptStrategy getStrategy(LeafType theme) {
		return switch (theme) {
			case FAITH -> faithPromptStrategy;
			case LOVE -> lovePromptStrategy;
			case HOPE -> hopePromptStrategy;
		};
	}
}
