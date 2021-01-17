pipeline {
    agent any

    environment {
        COMPOSE_FILE = "docker-compose.yml"
    }

    stages {
        stage ('Build Jar') {
            steps {
                withEnv(["JAVA_HOME=${ tool "java-8" }", "PATH+MAVEN=${ tool "maven" }/bin:${env.JAVA_HOME}/bin"]) {
                    sh "mvn clean package -Dmaven.test.skip=true"
                }
            }
        }

        stage("Docker build") {
            steps {
                sh "docker build -t nutritious-food:latest ."
            }
        }

        stage("Docker Deploy") {
            steps {
                sh "docker-compose build"
                sh "docker-compose build"
            }
        }
    }
}
