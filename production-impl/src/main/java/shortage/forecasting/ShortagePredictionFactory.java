package shortage.forecasting;

public interface ShortagePredictionFactory {
    ShortagePrediction create(String productRefNo);
}
