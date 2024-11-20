class DockerChanges {
    public static String registryUrl = "https://docker.valuesoft.site";
    static String getRegistry() {
    	return registryUrl;
    }
    static Boolean checkImageExists(steps, imageName) {
	boolean check = steps.docker.withRegistry(registryUrl, 'docker') { 
		steps.sh(script: "docker manifest inspect docker.valuesoft.site/${imageName}", returnStatus: true);
	}
        return !check;
    }
    def queue = Jenkins.get().queue
	sh ("echo $queue")
	queue.items.each{ item ->
    	task = item.task 
      	if (task instanceof org.jenkinsci.plugins.workflow.support.steps.ExecutorStepExecution.PlaceholderTask) {
      	    println("Job name: " + task.getOwnerExecutable().getParent().getFullName())
    	        } else {
      	    println("Job name: " + task.getFullName())
    	    }
        }	
}
