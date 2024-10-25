class CheckImage {
    static Boolean checkImageExists(imageName) {
  	def check = sh(script: "docker image ls | grep -w ${imageName}", returnStatus: true);
        return !check;
    }
}
