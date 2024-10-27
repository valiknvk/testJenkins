class boolImageCheck {
    static Boolean checkImageExists(steps, imageName) {
  	    def check = steps.sh(script: "docker image ls | grep -w ${imageName}", returnStatus: true);
        return !check;
    }
}
