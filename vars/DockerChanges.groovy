registryUrl = 'https://docker.valuesoft.site'
class DockerChanges {
    static Binding context
    static String getRegistry() {
    	return context.registryUrl;
    }
    static Boolean checkImageExists(steps, imageName) {
	boolean check = steps.docker.withRegistry(registryUrl, 'docker') { 
		steps.sh(script: "docker manifest inspect docker.valuesoft.site/${imageName}", returnStatus: true);
	}
        return !check;
    }
}
