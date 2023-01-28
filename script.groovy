/*def buildVersion(){
    echo "this method creates a new version"
    sh 'mvn build-helper:parse-version versions:set \
     -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} versions:commit'
    def matcher = readFile('pom.xml') =~ '<version>(.+)</version>'
    def version = matcher[0][1]
    env.IMAGE_NAME = "$version-$BUILD_NUMBER"
    echo "$IMAGE_NAME"

}*/
IMAGE_NAME = "2.1"

def buildJar(){
    echo "this is build stage"
    sh "mvn clean package"
}

def buildAndPushImage(){
    echo "this is push stage"
    withCredentials([usernamePassword('credentialsId':'nexus-repo-credentials','usernameVariable':'USER','passwordVariable':'PASS')]){
        sh "docker build -t 143.198.43.144:8083/maven-web-app:${IMAGE_NAME} ."
        sh "echo ${PASS} | docker login -u ${USER} --password-stdin 143.198.43.144:8083"
        sh "docker push 143.198.43.144:8083/maven-web-app:${IMAGE_NAME}"
    }
}

def deployJar(){
    echo "Deploying the Jar to server"
    container = "docker run -d -p 9090:9090 143.198.43.144:8083/maven-web-app:${IMAGE_NAME}"
    withCredentials([usernamePassword('credentialsId':'nexus-repo-credentials','usernameVariable':'USER','passwordVariable':'PASS')]){
        sshagent(['ec2-user-docker']) {
            docker_login = "echo ${PASS} | docker login -u ${USER} --password-stdin 143.198.43.144:8083"
            sh "ssh -o StrictHostKeyChecking=no ec2-user@18.234.80.161 ${docker_login} ${container}"
        }
    }
}

return this
