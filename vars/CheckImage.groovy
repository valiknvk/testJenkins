class CheckImage {
    static void checkImageExists(steps) {
  	boolean check = steps.sh(script: "docker image ls | grep -w ${imageName}", returnStatus: true);
        steps.env.IMAGE_EXISTS = !check;
    }
}
