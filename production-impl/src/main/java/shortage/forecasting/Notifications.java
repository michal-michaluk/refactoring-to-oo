package shortage.forecasting;

public interface Notifications {
    void alertPlanner(Shortages shortages);

    void softNotifyPlanner(Shortages shortages);

    void markOnPlan(Shortages shortages);
}
