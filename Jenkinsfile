pipeline {
    agent any
    tools{
        maven 'Maven'
    }
    stages {
        stage('CompileTest') {
               steps {
                echo "running from jenkinsFile"
                bat "mvn clean compile test"
            }
        }
    }
}
