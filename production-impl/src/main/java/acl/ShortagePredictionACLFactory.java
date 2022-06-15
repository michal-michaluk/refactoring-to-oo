package acl;

import external.CurrentStock;
import shortage.forecasting.*;

import java.time.LocalDate;

public class ShortagePredictionACLFactory implements ShortagePredictionFactory {
    private LocalDate today;
    private int daysAhead;
    private CurrentStock externalStock;
    private ProductionPlanningMediator productions;
    private DemandForecastingMediator demands;

    public ShortagePredictionACLFactory(LocalDate today, int daysAhead, CurrentStock externalStock, ProductionPlanningMediator productions, DemandForecastingMediator demands) {
        this.today = today;
        this.daysAhead = daysAhead;
        this.externalStock = externalStock;
        this.productions = productions;
        this.demands = demands;
    }

    @Override
    public ShortagePrediction create() {
        DateRange dates = DateRange.from(today, daysAhead);
        WarehouseStock stock = createWarehouseStock();
        ProductionOutput outputs = productions.createOutputs();
        Demands demands = this.demands.createDemands();
        return new ShortagePrediction(dates, stock, outputs, demands);
    }

    private WarehouseStock createWarehouseStock() {
        return new WarehouseStock(externalStock.getLevel());
    }

}
