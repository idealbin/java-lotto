package lotto.domain;

import lotto.exception.DuplicateNumberException;
import lotto.exception.NumberCountException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Lotto {
	public static final int PICK_COUNT = 6;
	public static final int PRICE = 1000;
	public static final String SEPARATOR = ",";

	private List<LottoNumber> numbers;
	private int bonusNumber;

	public Lotto(List<LottoNumber> numbers) {
		checkDistinctNumber(numbers);
		checkNumberCount(numbers);
		this.numbers = numbers;
	}

	public Lotto(String lastWinningNumber, int lastBonusNumber) {
		String[] numbers = lastWinningNumber.split(SEPARATOR);
		List<LottoNumber> lastWeekNumber = Arrays.stream(numbers)
				.map(Integer::parseInt)
				.map(LottoNumber::new)
				.collect(Collectors.toList());

		checkDistinctNumber(lastWeekNumber);
		checkNumberCount(lastWeekNumber);
		this.numbers = lastWeekNumber;
		this.bonusNumber = lastBonusNumber;
	}

	public int askMatchCount(Lotto lastWinningNumbers) {
		int matchCount = 0;
		for (LottoNumber winningNumber : lastWinningNumbers.numbers) {
			matchCount = compareMatchCount(winningNumber, matchCount);
		}
		return matchCount;
	}

	public boolean isMatchingBonusNumber(int bonusNumber) {
		return numbers.contains(new LottoNumber(bonusNumber));
	}

	public String print() {
		return numbers.toString();
	}

	public int getBonusNumber() {
		return bonusNumber;
	}

	private int compareMatchCount(LottoNumber winningNumber, int matchCount) {
		if (numbers.contains(winningNumber)) {
			++matchCount;
		}
		return matchCount;
	}

	private void checkNumberCount(List<LottoNumber> numbers) {
		if (numbers.size() != PICK_COUNT) {
			throw new NumberCountException();
		}
	}

	private void checkDistinctNumber(List<LottoNumber> lastWeekNumber) {
		Set<LottoNumber> number = new HashSet<>(lastWeekNumber);
		if(number.size() != lastWeekNumber.size()) {
			throw new DuplicateNumberException();
		}
	}
}
