import jenkins.model.Jenkins
class QueueCustom {
	static void cleanQueueCustom(String taskName) {
    		def queue = Jenkins.instance.queue
		def testvar = "test"
    		queue.items.findAll { testvar == taskName }.each {
			
			queue.cancel(it.task) 
			
    		}
	}
}
