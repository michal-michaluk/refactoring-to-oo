package acl;

import dao.ProductionDao;
import entities.ProductionEntity;
import shortage.forecasting.ProductionOutput;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ProductionPlanningMediator {
    private ProductionDao productionDao;

    public ProductionOutput createOutputs(String productRefNo, LocalDate today) {
        List<ProductionEntity> productions = productionDao.findFromTime(productRefNo, today.atStartOfDay());
        return new ProductionOutput(
                productions.stream()
                        .collect(Collectors.groupingBy(
                                p -> p.getStart().toLocalDate(),
                                Collectors.summingLong(ProductionEntity::getOutput)
                        )));
    }
}
