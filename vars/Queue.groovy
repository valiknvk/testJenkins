//import jenkins.model.Jenkins;
//import org.jenkinsci.plugins.workflow.support.steps.ExecutorStepExecution;

//import java.util.List;

class Queue {
    static List<?> printQueue() {
        var queue = Jenkins.get().getQueue();
        //List<?> items = queue.getItems();
        return queue.getItems();
        //for (Object item : items) {
        //    var task = item.getTask();
        //    if (task instanceof ExecutorStepExecution.PlaceholderTask) {
        //        System.out.println("Job name: " + task.getOwnerExecutable().getParent().getFullName());
        //    } else {
        //        System.out.println("Job name: " + task.getFullName());
        //    }
        }
    }
}
