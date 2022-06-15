package acl;

import entities.DemandEntity;
import shortage.forecasting.Demands;
import tools.Util;

import java.util.List;
import java.util.stream.Collectors;

public class DemandForecastingMediator {
    private List<DemandEntity> demands;

    public DemandForecastingMediator(List<DemandEntity> demands) {
        this.demands = demands;
    }

    public Demands createDemands() {
        return new Demands(demands.stream()
                .collect(Collectors.toUnmodifiableMap(
                        DemandEntity::getDay,
                        demand -> new Demands.DailyDemand(
                                Util.getLevel(demand),
                                LevelOnDeliveryVariant.pick(Util.getDeliverySchema(demand))
                        )))
        );
    }
}
