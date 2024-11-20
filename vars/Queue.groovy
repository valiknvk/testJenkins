class Queue {
  queue = Jenkins.get().queue
	queue.items.each{ item ->
    	task = item.task 
      	if (task instanceof org.jenkinsci.plugins.workflow.support.steps.ExecutorStepExecution.PlaceholderTask) {
      	    println("Job name: " + task.getOwnerExecutable().getParent().getFullName())
    	  } else {
      	    println("Job name: " + task.getFullName())
    	  }
  }	
}
