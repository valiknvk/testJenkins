class DockerChanges {
    static Boolean checkImageExists(steps, imageName) {
	boolean check = steps.docker.withRegistry('https://docker.valuesoft.site', 'docker') { 
		steps.sh(script: "docker manifest inspect docker.valuesoft.site/${imageName}", returnStatus: true);
	}
        return !check;
    }
}
