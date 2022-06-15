package acl;

import dao.DemandDao;
import entities.DemandEntity;
import shortage.forecasting.Demands;
import tools.Util;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class DemandForecastingMediator {
    private DemandDao demandDao;

    public Demands createDemands(String productRefNo, LocalDate today) {
        List<DemandEntity> demands = demandDao.findFrom(today.atStartOfDay(), productRefNo);
        return new Demands(
                demands.stream()
                        .collect(Collectors.toUnmodifiableMap(
                                DemandEntity::getDay,
                                demand -> new Demands.DailyDemand(
                                        Util.getLevel(demand),
                                        LevelOnDeliveryVariant.pick(Util.getDeliverySchema(demand))
                                )))
        );
    }
}
