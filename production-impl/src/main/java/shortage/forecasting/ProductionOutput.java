package shortage.forecasting;

import java.time.LocalDate;
import java.util.Map;

public class ProductionOutput {

    private final String productRefNo;
    private final Map<LocalDate, Long> outputs;

    public ProductionOutput(String productRefNo, Map<LocalDate, Long> outputs) {
        this.productRefNo = productRefNo;
        this.outputs = outputs;
    }

    public long getOutputs(LocalDate day) {
        return outputs.getOrDefault(day, 0L);
    }

    public String getProductRefNo() {
        return productRefNo;
    }
}
