class DockerChanges {
    String registryUrl = 'https://docker.valuesoft.site';
    String getRegistry() {
    	return registryUrl;
    }
    static Boolean checkImageExists(steps, imageName) {
	boolean check = steps.docker.withRegistry(registryUrl, 'docker') { 
		steps.sh(script: "docker manifest inspect docker.valuesoft.site/${imageName}", returnStatus: true);
	}
        return !check;
    }
}
