import jenkins.model.Jenkins
class QueueCustom {
	static void cleanQueueCustom(String taskName) {
    		def queue = Jenkins.instance.queue
    		queue.items.findAll { it.task.name.contains(taskName) }.each {
			queue.cancel(it.task) 
    		}
	}
}
