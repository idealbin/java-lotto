package lotto.view;

import java.util.Scanner;

public class InputView {
	private Scanner scanner = new Scanner(System.in);

	public int purchasingPrice() {
		System.out.println("구입금액을 입력해 주세요.");
		return scanner.nextInt();
	}

	public String lastWeekNumbers() {
		System.out.println("\n지난 주 당첨 번호를 입력해 주세요.");
		return scanner.next();
	}

	public int bonusNumber() {
		System.out.println("보너스 볼을 입력해 주세요.");
		return scanner.nextInt();
	}

	public void close() {
		scanner.close();
	}
}
