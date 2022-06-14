package shortage.forecasting;

import entities.DemandEntity;
import entities.ProductionEntity;
import external.CurrentStock;

import java.time.LocalDate;
import java.util.List;

public class ShortagePredictionFactory {
    private LocalDate today;
    private int daysAhead;
    private CurrentStock externalStock;
    private List<ProductionEntity> productions;
    private List<DemandEntity> demands;

    public ShortagePredictionFactory(LocalDate today, int daysAhead, CurrentStock externalStock, List<ProductionEntity> productions, List<DemandEntity> demands) {
        this.today = today;
        this.daysAhead = daysAhead;
        this.externalStock = externalStock;
        this.productions = productions;
        this.demands = demands;
    }

    public ShortagePrediction create() {
        DateRange dates = DateRange.from(today, daysAhead);
        WarehouseStock stock = createWarehouseStock();
        ProductionOutput outputs = createOutputs();
        Demands demands = createDemands();
        return new ShortagePrediction(dates, stock, outputs, demands);
    }

    private WarehouseStock createWarehouseStock() {
        return new WarehouseStock(externalStock.getLevel());
    }

    private Demands createDemands() {
        return new Demands(demands);
    }

    private ProductionOutput createOutputs() {
        return new ProductionOutput(productions);
    }
}
