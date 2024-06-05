pipeline {
	agent none
        stages {
          stage('Get Build Files') {
             steps {
                echo 'Getting Private Repo'
                git(
                   url: 'git@github.com:enaide/spring-jenkins.git',
                   credentialsId: 'jenkins',
                   branch: 'main'
                )

                script {
                   mvnHome = tool 'M3'
                }
             }
          }
	      stage('Build & Test') {
            steps {
               sh 'mvn -Dmaven.test.failure.ignore clean package'
          }
	    }
    }
}