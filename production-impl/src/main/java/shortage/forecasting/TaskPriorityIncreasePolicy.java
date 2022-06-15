package shortage.forecasting;

import java.time.Clock;
import java.time.LocalDate;

public class TaskPriorityIncreasePolicy {
    private Clock clock;
    private long confIncreaseQATaskPriorityInDays;

    public boolean shouldIncreaseTaskPriority(Shortages shortages, long lockedParts) {
        LocalDate date = LocalDate.now(clock).plusDays(confIncreaseQATaskPriorityInDays);
        return lockedParts > 0 && shortages.firstBefore(date);
    }
}
