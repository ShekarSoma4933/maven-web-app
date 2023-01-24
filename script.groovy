def buildJar(){
    echo "this is build stage"
    sh "mvn package"
}

def buildAndPushImage(){
    echo "this is push stage"
    withCredentials([usernamePassword('credentialsId':'nexus-repo-credentials','usernameVariable':'USER','passwordVariable':'PASS')]){
        sh "docker build -t 143.198.43.144:8083/maven-web-app:2.1 ."
        sh "echo ${PASS} | docker login -u ${USER} --password-std-in 143.198.43.144:8083"
        sh "docker push 143.198.43.144:8083/maven-web-app:2.1"
    }
}

def deployJar(){
    echo "Deploying the Jar to server"
}

return this
