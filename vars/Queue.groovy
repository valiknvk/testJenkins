import jenkins.model.Jenkins;
import hudson.model.Queue;

class Queue {
    public void cleanQueue() {
	Queue queue = Jenkins.getInstance().getQueue();
	queue.getItems().stream()
        .filter(item -> item.getTask().getName().contains("booker/master"))
        .forEach(item -> queue.cancel(item.getTask()));
    }
}
