package shortage.forecasting;

import entities.DemandEntity;
import enums.DeliverySchema;
import tools.Util;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Demands {
    private final Map<LocalDate, DemandEntity> demands;

    public Demands(List<DemandEntity> demands) {
        HashMap<LocalDate, DemandEntity> demandsPerDay = new HashMap<>();
        for (DemandEntity demand1 : demands) {
            demandsPerDay.put(demand1.getDay(), demand1);
        }
        this.demands = Collections.unmodifiableMap(demandsPerDay);
    }

    public boolean noDemand(LocalDate day) {
        return !demands.containsKey(day);
    }

    public DeliverySchema getDeliverySchema(LocalDate day) {
        if (!demands.containsKey(day)) {
            // ??
        }
        return Util.getDeliverySchema(demands.get(day));
    }

    public long getLevel(LocalDate day) {
        if (!demands.containsKey(day)) {
            // ??
        }
        return Util.getLevel(demands.get(day));
    }
}
