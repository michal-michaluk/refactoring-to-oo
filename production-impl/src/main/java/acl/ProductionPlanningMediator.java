package acl;

import entities.ProductionEntity;
import shortage.forecasting.ProductionOutput;

import java.util.List;
import java.util.stream.Collectors;

public class ProductionPlanningMediator {
    private List<ProductionEntity> productions;

    public ProductionPlanningMediator(List<ProductionEntity> productions) {
        this.productions = productions;
    }

    public ProductionOutput createOutputs() {
        //var productions = productionDao.findFromTime(productRefNo, today.atStartOfDay()),
        return new ProductionOutput(
                productions.stream()
                        .map(production -> production.getForm().getRefNo())
                        .findFirst()
                        .orElse(null),
                productions.stream()
                        .collect(Collectors.groupingBy(
                                p -> p.getStart().toLocalDate(),
                                Collectors.summingLong(ProductionEntity::getOutput)
                        )));
    }
}
