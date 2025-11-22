package sopt.server.web3.domain.openai.strategy;

import java.util.Map;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import sopt.server.web3.domain.openai.domain.Theme;

@Component
@RequiredArgsConstructor
public class PromptStrategyFactory {

	private final FaithPromptStrategy faithPromptStrategy;
	private final LovePromptStrategy lovePromptStrategy;
	private final HopePromptStrategy hopePromptStrategy;

	public PromptStrategy getStrategy(Theme theme) {
		return switch (theme) {
			case FAITH -> faithPromptStrategy;
			case LOVE -> lovePromptStrategy;
			case HOPE -> hopePromptStrategy;
		};
	}
}
