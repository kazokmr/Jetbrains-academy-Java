package animals.game.message;

import java.time.LocalTime;

import static animals.game.i18n.AppResource.getMessage;

public class Greeting {

    private final LocalTime morningAfter = LocalTime.parse(getMessage("time.morning.start"));
    private final LocalTime morningBefore = LocalTime.parse(getMessage("time.morning.end"));
    private final LocalTime afternoonAfter = LocalTime.parse(getMessage("time.afternoon.start"));
    private final LocalTime afternoonBefore = LocalTime.parse(getMessage("time.afternoon.end"));
    private final LocalTime eveningAfter = LocalTime.parse(getMessage("time.evening.start"));
    private final LocalTime eveningBefore = LocalTime.of(23, 59, 59);
    private final LocalTime nightAfter = LocalTime.of(0, 0, 0);
    private final LocalTime nightBefore = LocalTime.parse(getMessage("time.night.end"));

    public String say() {

        LocalTime localTime = LocalTime.now();
        if (localTime.isAfter(morningAfter) && localTime.isBefore(morningBefore)) {
            return getMessage("greeting.morning");
        }
        if (localTime.isBefore(afternoonAfter) && localTime.isBefore(afternoonBefore)) {
            return getMessage("greeting.afternoon");
        }
        if (localTime.isAfter(eveningAfter) && localTime.isBefore(eveningBefore)) {
            return getMessage("greeting.evening");
        }
        if (localTime.isBefore(nightAfter) && localTime.isBefore(nightBefore)) {
            return getMessage("greeting.night");
        }
        return getMessage("greeting.early");
    }
}
