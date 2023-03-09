package blackjack.view;

import blackjack.controller.BlackjackCommand;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;

import java.util.Arrays;
import java.util.Scanner;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class InputView {
    private static final String INPUT_PLAYER_NAMES_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String DELIMITER = ",";
    private static final int LIMIT = -1;
    private static final String INPUT_PLAYER_COMMAND_MESSAGE = "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    private static final String NEW_LINE = System.lineSeparator();

    private final Scanner scanner;

    public InputView(final Scanner scanner) {
        this.scanner = scanner;
    }

    public Players readPlayers() {
        System.out.println(INPUT_PLAYER_NAMES_MESSAGE);
        final String names = scanner.nextLine();

        return Arrays.stream(names.split(DELIMITER, LIMIT))
                .map(String::trim)
                .collect(collectingAndThen(toList(), Players::from));
    }

    public int readBetMoney(final Player player) {
        System.out.println(NEW_LINE + player.getName() + "의 배팅 금액은?");

        final String betMoney = scanner.nextLine();

        return toInt(betMoney);
    }

    private int toInt(final String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자를 입력해야 합니다. 입력 값 :" + value);
        }
    }

    public BlackjackCommand readCommand(final Player player) {
        System.out.println(player.getName() + INPUT_PLAYER_COMMAND_MESSAGE);

        final String command = scanner.nextLine();

        return BlackjackCommand.from(command);
    }
}
