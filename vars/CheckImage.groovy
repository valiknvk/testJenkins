class CheckImage {
    static void checkImageExists(steps) {
        String imageName = steps.env.DOCKER_IMAGE_NAME;
      	boolean check = steps.sh(script: "docker image ls | grep -w ${imageName}", returnStatus: true);
        steps.env.IMAGE_EXISTS = check;
    }
}
