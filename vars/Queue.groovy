import jenkins.model.Jenkins;
import hudson.model.Queue;

class QueueHandler {
    static void clean() {
        Queue queue = Jenkins.getInstance().getQueue();
        queue.getItems().stream()
            .filter(item -> item.getTask().getName().contains("part"))
            .forEach(item -> queue.cancel(item.getTask()));
    }
}
