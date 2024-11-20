class Queue {
  static String printQueue(){
    def queue = Jenkins.get().queue
	for ( def item queue.items) { 
    	  def task = item.task 
      	  if (task instanceof org.jenkinsci.plugins.workflow.support.steps.ExecutorStepExecution.PlaceholderTask) {
      	      System.out.println("Job name: " + task.getOwnerExecutable().getParent().getFullName())
    	    } else {
      	      System.out.println("Job name: " + task.getFullName())
    	    }
    }	
  }
}
