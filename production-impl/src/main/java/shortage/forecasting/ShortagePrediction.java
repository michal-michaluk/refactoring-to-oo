package shortage.forecasting;

import java.time.LocalDate;

import static java.lang.Math.max;

public class ShortagePrediction {
    private final String productRefNo;
    private final DateRange dates;
    private final WarehouseStock warehouse;
    private final ProductionOutput outputs;
    private final Demands demands;

    public ShortagePrediction(String productRefNo, DateRange dates, WarehouseStock warehouse, ProductionOutput outputs, Demands demands) {
        this.productRefNo = productRefNo;
        this.dates = dates;
        this.warehouse = warehouse;
        this.outputs = outputs;
        this.demands = demands;
    }

    public Shortages predictShortages() {
        long level = warehouse.level();

        Shortages shortages = new Shortages(productRefNo);
        for (LocalDate day : dates) {
            long produced = outputs.getOutputs(day);
            Demands.DailyDemand demand = demands.get(day);
            long levelOnDelivery = demand.levelOnDelivery(level, produced);

            if (levelOnDelivery < 0) {
                shortages.add(day, levelOnDelivery);
            }
            long endOfDayLevel = demand.endOfDayLevel(level, produced);
            level = max(0, endOfDayLevel);
        }
        return shortages;
    }

    public long getLockedParts() {
        return warehouse.locked();
    }
}
