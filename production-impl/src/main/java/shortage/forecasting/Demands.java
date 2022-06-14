package shortage.forecasting;

import entities.DemandEntity;
import tools.Util;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Demands {
    private final Map<LocalDate, DemandEntity> demands;

    public Demands(List<DemandEntity> demands) {
        Map<LocalDate, DemandEntity> demandsPerDay = new HashMap<>();
        for (DemandEntity demand : demands) {
            demandsPerDay.put(demand.getDay(), demand);
        }
        this.demands = Collections.unmodifiableMap(demandsPerDay);
    }

    public boolean noDemand(LocalDate day) {
        return !demands.containsKey(day);
    }

    public DailyDemand get(LocalDate day) {
        if (!demands.containsKey(day)) {
            return null;
        }
        return new DailyDemand(demands.get(day));
    }

    public static class DailyDemand {
        private final DemandEntity demand;
        private final LevelOnDelivery strategy;

        public DailyDemand(DemandEntity demand) {
            this.demand = demand;
            strategy = LevelOnDeliveryVariant.pick(Util.getDeliverySchema(demand));
        }

        public long getLevel() {
            return Util.getLevel(demand);
        }

        public long levelOnDelivery(long level, long produced) {
            return strategy.calculate(level, produced, getLevel());
        }

        public long endOfDayLevel(long level, long produced) {
            return level + produced - getLevel();
        }
    }
}
