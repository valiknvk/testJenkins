class Queue {
	void cleanQueue() {
    		def queue = Jenkins.instance.queue
    		queue.items.findAll { it.task.name.contains('booker/master') }.each {
			queue.cancel(it.task) 
    		}
	}
}
