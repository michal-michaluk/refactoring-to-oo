package shortage.forecasting;

import entities.ProductionEntity;

import java.time.LocalDate;
import java.util.*;

public class ProductionOutput {

    private final String productRefNo;
    private final Map<LocalDate, List<ProductionEntity>> outputs;

    public ProductionOutput(List<ProductionEntity> productions) {
        String productRefNo = null;
        HashMap<LocalDate, List<ProductionEntity>> outputs = new HashMap<>();
        for (ProductionEntity production : productions) {
            if (!outputs.containsKey(production.getStart().toLocalDate())) {
                outputs.put(production.getStart().toLocalDate(), new ArrayList<>());
            }
            outputs.get(production.getStart().toLocalDate()).add(production);
            productRefNo = production.getForm().getRefNo();
        }
        this.productRefNo = productRefNo;
        this.outputs = Collections.unmodifiableMap(outputs);
    }

    public long getOutputs(LocalDate day) {
        long level = 0;
        for (ProductionEntity production : outputs.get(day)) {
            level += production.getOutput();
        }
        return level;
    }

    public String getProductRefNo() {
        return productRefNo;
    }
}
