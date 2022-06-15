package acl;

import external.JiraService;
import shortage.forecasting.Tasks;

public class JiraTasksMediator implements Tasks {

    JiraService jiraService;

    @Override
    public void increasePriorityFor(String productRefNo) {
        jiraService.increasePriorityFor(productRefNo);
    }
}
