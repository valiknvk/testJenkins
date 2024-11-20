import jenkins.model.Jenkins;
import hudson.model.Queue;
class Queue {
    static void clean() {
        Queue queue = Jenkins.getInstance().getQueue();
        queue.getItems().stream()
            .filter(item -> item.getTask().getName().contains("part"))
            .forEach(item -> queue.cancel(item.getTask()));
    }
}
