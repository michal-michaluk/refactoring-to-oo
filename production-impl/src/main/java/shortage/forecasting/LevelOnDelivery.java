package shortage.forecasting;

public interface LevelOnDelivery {
    LevelOnDelivery AtDayStart = (level, produced, demand) -> level - demand;
    LevelOnDelivery TillEndOfDay = (level, produced, demand) -> level - demand + produced;

    LevelOnDelivery exceptionally = (level, produced, demand) -> {
        throw new UnsupportedOperationException();
    };

    long calculate(long level, long produced, long demand);
}
