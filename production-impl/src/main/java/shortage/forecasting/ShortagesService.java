package shortage.forecasting;

import acl.ShortagePredictionFactory;

public class ShortagesService {
    private ShortagePredictionFactory factory;

    public ShortagesService(ShortagePredictionFactory factory) {
        this.factory = factory;
    }

    public Shortages predict() {
        ShortagePrediction prediction = factory.create();
        return prediction.predictShortages();
    }
}
