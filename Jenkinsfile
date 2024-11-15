@Library('testLib') _

def name = "testName"
class Example {
    def DisplayName(name) {
        return name
   } 
}
def testName(name) {
    return name
}
def checkExitCode() {
    Boolean check = sh(script: "ls -lsa > /dev/null 2>&1", returnStatus: true);
    return !check
}

pipeline {
    agent any
    environment {
	    DOCKER_IMAGE_NAME = "caddy"
    }

    stages {
        stage ('check') {
    
            steps {
                    echo "${name}"
            }        
        }
        stage ('callClass') {
            environment {
	           TEST_NAME = "surebetapp:git-98999bc40ac1014ec8176959d295ce43598f86b8"
            }
            when {
                expression { testName(env.DOCKER_IMAGE_NAME) == "caddy"}
            }
            steps {
                script {
                    properties([disableConcurrentBuilds(abortPrevious: true)])
                    if ( checkExitCode() ) {
                            sh ("echo $name")
                            test = testName("123145")
                            echo "$test"
                            CheckImage.checkImageExists(this)
                            //docker.withRegistry('https://docker.valuesoft.site', 'docker') {
                            //    test1 = DockerChanges.checkImageExists(this, env.TEST_NAME)
                            //}
                            echo "START CHECK FROM DOCKERCHANGES"
                            registry = DockerChanges.getRegistry()
                            echo "$registry"
                            test1 = DockerChanges.checkImageExists(this, env.TEST_NAME)
                            if (!test1){
                                echo "Image not found"
                            } else {
                                echo "Image exists"
                            }
                            sh 'printenv'
                            sh ("sleep 10")
                    }
                //echo "${env.IMAGE_EXISTS}"
                //echo "${test}"
                }   
            }   
        }
        
    }
}
