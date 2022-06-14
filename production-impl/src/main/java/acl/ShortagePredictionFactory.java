package acl;

import entities.DemandEntity;
import external.CurrentStock;
import shortage.forecasting.*;

import java.time.LocalDate;
import java.util.List;

public class ShortagePredictionFactory {
    private LocalDate today;
    private int daysAhead;
    private CurrentStock externalStock;
    private ProductionPlanningMediator productions;
    private List<DemandEntity> demands;

    public ShortagePredictionFactory(LocalDate today, int daysAhead, CurrentStock externalStock, ProductionPlanningMediator productions, List<DemandEntity> demands) {
        this.today = today;
        this.daysAhead = daysAhead;
        this.externalStock = externalStock;
        this.productions = productions;
        this.demands = demands;
    }

    public ShortagePrediction create() {
        DateRange dates = DateRange.from(today, daysAhead);
        WarehouseStock stock = createWarehouseStock();
        ProductionOutput outputs = productions.createOutputs();
        Demands demands = createDemands();
        return new ShortagePrediction(dates, stock, outputs, demands);
    }

    private WarehouseStock createWarehouseStock() {
        return new WarehouseStock(externalStock.getLevel());
    }

    private Demands createDemands() {
        return new Demands(demands);
    }

}
