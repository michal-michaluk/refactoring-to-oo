package acl;

import external.CurrentStock;
import external.StockService;
import shortage.forecasting.WarehouseStock;

public class WarehouseStockMediator {
    StockService stockService;

    public WarehouseStock getCurrentStock(String productRefNo) {
        CurrentStock currentStock = stockService.getCurrentStock(productRefNo);

        return new WarehouseStock(currentStock.getLevel(), currentStock.getLocked());
    }
}
