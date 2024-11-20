import jenkins.model.Jenkins;
import org.jenkinsci.plugins.workflow.support.steps.ExecutorStepExecution;

import java.util.List;

public class printQueue {
    public static void main(String[] args) {
        var queue = Jenkins.get().getQueue();
        List<?> items = queue.getItems();
        for (Object item : items) {
            var task = item.getTask();
            if (task instanceof ExecutorStepExecution.PlaceholderTask) {
                System.out.println("Job name: " + task.getOwnerExecutable().getParent().getFullName());
            } else {
                System.out.println("Job name: " + task.getFullName());
            }
        }
    }
}
