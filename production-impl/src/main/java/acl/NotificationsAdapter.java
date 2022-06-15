package acl;

import external.NotificationsService;
import shortage.forecasting.Notifications;
import shortage.forecasting.Shortages;

public class NotificationsAdapter implements Notifications {

    NotificationsService notificationsService;

    @Override
    public void alertPlanner(Shortages shortages) {
        notificationsService.alertPlanner(shortages.toList());
    }

    @Override
    public void softNotifyPlanner(Shortages shortages) {
        notificationsService.softNotifyPlanner(shortages.toList());
    }

    @Override
    public void markOnPlan(Shortages shortages) {
        notificationsService.markOnPlan(shortages.toList());
    }
}
