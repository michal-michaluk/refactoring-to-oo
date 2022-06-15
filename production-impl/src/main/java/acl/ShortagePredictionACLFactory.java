package acl;

import shortage.forecasting.*;

import java.time.Clock;
import java.time.LocalDate;

public class ShortagePredictionACLFactory implements ShortagePredictionFactory {

    private Clock clock;
    private WarehouseStockMediator warehouse;
    private ProductionPlanningMediator productions;
    private DemandForecastingMediator demands;
    private int confShortagePredictionDaysAhead;

    @Override
    public ShortagePrediction create(String productRefNo) {
        LocalDate today = LocalDate.now(clock);
        DateRange dates = DateRange.from(today, confShortagePredictionDaysAhead);
        WarehouseStock stock = warehouse.getCurrentStock(productRefNo);
        ProductionOutput outputs = productions.createOutputs(productRefNo, today);
        Demands demands = this.demands.createDemands(productRefNo, today);
        return new ShortagePrediction(productRefNo, dates, stock, outputs, demands);
    }

}
