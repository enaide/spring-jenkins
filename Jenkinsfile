pipeline {
	agent none
	stages {
	    stage('Build & Test') {
            steps {
               sh 'mvn -Dmaven.test.failure.ignore clean package'
            }
	    }
    }
}