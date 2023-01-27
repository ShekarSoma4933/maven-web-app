def buildVersion(){
    echo "this method creates a new version"
    sh 'mvn build-helper:parse-version versions:set \
     -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} versions.commit'
    def matcher = readFile('pom.xml') =~ '<version>(.+)</version>'
    def version = matcher[0][1]
    env.IMAGE_NAME = "$version-$BUILD_NUMBER"
    echo "$IMAGE_NAME"

}


def buildJar(){
    echo "this is build stage"
    sh "mvn package"
}

def buildAndPushImage(){
    echo "this is push stage"
    withCredentials([usernamePassword('credentialsId':'nexus-repo-credentials','usernameVariable':'USER','passwordVariable':'PASS')]){
        sh "docker build -t 143.198.43.144:8083/maven-web-app:$IMAGE_NAME ."
        sh "echo ${PASS} | docker login -u ${USER} --password-stdin 143.198.43.144:8083"
        sh "docker push 143.198.43.144:8083/maven-web-app:$IMAGE_NAME"
    }
}

def deployJar(){
    echo "Deploying the Jar to server"
}

return this
