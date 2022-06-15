package shortage.forecasting;

public class ShortagesService {

    private ShortagePredictionFactory factory;
    private ShortagesRepository repository;
    private TaskPriorityIncreasePolicy policy;

    private Notifications notification;
    private Tasks tasks;

    public void predictShortagesForProduct(String productRefNo) {
        ShortagePrediction prediction = factory.create(productRefNo);
        Shortages shortages = prediction.predictShortages();
        Shortages previous = repository.get(productRefNo);
        if (shortages.differentThen(previous)) {
            notification.alertPlanner(shortages);
            // TODO REFACTOR: policy why to increase task priority
            if (policy.shouldIncreaseTaskPriority(shortages, prediction.getLockedParts())) {
                tasks.increasePriorityFor(productRefNo);
            }
            repository.save(shortages);
        }
        if (shortages.isSolved(previous)) {
            repository.delete(productRefNo);
        }
    }

}
