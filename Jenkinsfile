node {

    git branch: "master", url: "https://github.com/duongtrong/nutritious-food.git" 

    stage ('Build Jar') {
        withEnv(["JAVA_HOME=${ tool "java-8" }", "PATH+MAVEN=${ tool "maven" }/bin:${env.JAVA_HOME}/bin"]) {
            sh "mvn clean package -Dmaven.test.skip=true"
        }
    }

    stage("Docker build") {
        sh "docker build -t nutritious-food:latest ."
    }
}
