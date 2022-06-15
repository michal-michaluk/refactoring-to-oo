package acl;

import entities.DemandEntity;
import entities.ProductionEntity;
import entities.ShortageEntity;
import external.CurrentStock;
import shortage.forecasting.Shortages;
import shortage.forecasting.ShortagesService;
import tools.ShortageFinder;

import java.time.LocalDate;
import java.util.List;

public class ShortageFinderACL {

    private static boolean TOGGLE_NEW_MODEL = true;

    private ShortageFinderACL() {
    }

    /**
     * Production at day of expected delivery is quite complex:
     * We are able to produce and deliver just in time at same day
     * but depending on delivery time or scheme of multiple deliveries,
     * we need to plan properly to have right amount of parts ready before delivery time.
     * <p/>
     * Typical schemas are:
     * <li>Delivery at prod day start</li>
     * <li>Delivery till prod day end</li>
     * <li>Delivery during specified shift</li>
     * <li>Multiple deliveries at specified times</li>
     * Schema changes the way how we calculate shortages.
     * Pick of schema depends on customer demand on daily basis and for each product differently.
     * Some customers includes that information in callof document,
     * other stick to single schema per product. By manual adjustments of demand,
     * customer always specifies desired delivery schema
     * (increase amount in scheduled transport or organize extra transport at given time)
     */
    public static List<ShortageEntity> findShortages(LocalDate today, int daysAhead, CurrentStock externalStock,
                                                     List<ProductionEntity> productions, List<DemandEntity> demands) {
        if (TOGGLE_NEW_MODEL) {
            ShortagePredictionFactory factory = new ShortagePredictionFactory(today, daysAhead, externalStock, new ProductionPlanningMediator(productions), demands);
            ShortagesService service = new ShortagesService(factory);
            Shortages shortages = service.predict();
            return shortages.toList();
        } else {
            return ShortageFinder.findShortages(today, daysAhead, externalStock, productions, demands);
        }
    }

}
