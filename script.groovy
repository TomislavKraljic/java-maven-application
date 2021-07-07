def buildJar() {
    echo "building the application..."
    sh 'mvn package'
}

def buildDockerImage() {
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId:'nexus-credentials', passwordVariable:'PASS',
            usernameVariable: 'USER')]) {
        sh 'docker build -t 134.122.20.106:8083/java-maven-app:1.4 .'
        sh "echo $PASS | docker login -u $USER --password-stdin 134.122.20.106:8083"
        sh 'docker push 134.122.20.106:8083/java-maven-app:1.4'
    }
}

def deployDockerImage() {
    echo 'deploying the application...'
}

return this
