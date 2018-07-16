pipeline {
    agent {
        docker {
            image 'maven:3-alpine'
            args '-v D:/docker/jenkins/maven_home:/root/.m2'
        }
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }
    }
}