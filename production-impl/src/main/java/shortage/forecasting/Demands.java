package shortage.forecasting;

import java.time.LocalDate;
import java.util.Map;

public class Demands {
    private final Map<LocalDate, DailyDemand> demands;

    public Demands(Map<LocalDate, DailyDemand> demands) {
        this.demands = demands;
    }

    public DailyDemand get(LocalDate day) {
        return demands.getOrDefault(day, DailyDemand.NO_DEMAND);
    }

    public static class DailyDemand {
        public static final DailyDemand NO_DEMAND = new DailyDemand(0, LevelOnDelivery.TillEndOfDay);

        private final long demand;
        private final LevelOnDelivery strategy;

        public DailyDemand(long demand, LevelOnDelivery strategy) {
            this.demand = demand;
            this.strategy = strategy;
        }

        public long getLevel() {
            return demand;
        }

        public long levelOnDelivery(long level, long produced) {
            return strategy.calculate(level, produced, getLevel());
        }

        public long endOfDayLevel(long level, long produced) {
            return level + produced - getLevel();
        }
    }
}
