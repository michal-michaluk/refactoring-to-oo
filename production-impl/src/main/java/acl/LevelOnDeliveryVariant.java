package acl;

import enums.DeliverySchema;
import shortage.forecasting.LevelOnDelivery;

public class LevelOnDeliveryVariant {
    public static LevelOnDelivery pick(DeliverySchema schema) {
        return switch (schema) {
            case atDayStart -> LevelOnDelivery.AtDayStart;
            case tillEndOfDay -> LevelOnDelivery.TillEndOfDay;
            case null, default -> LevelOnDelivery.exceptionally;
        };
    }
}
