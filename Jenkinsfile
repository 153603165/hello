
node {

    withMaven(maven:'maven') {

        stage('Checkout') {
            git url: 'https://github.com/153603165/hello.git', branch: 'master'
        }

        stage('Build') {
            sh 'mvn clean package docker:build -DskipTests'

            def pom = readMavenPom file: 'pom.xml'
            print pom.version
            env.version = pom.version
        }

        stage('Image And Push') {
     	   withDockerRegistry(registry: [credentialsId: '3e855734-cbe7-4430-811d-c9216df0769f', url: 'http://192.168.146.133']) {
	            def app = docker.build ("192.168.146.133/hello/hello:${env.BUILD_NUMBER}")
	            app.push()
	        }
		}
     	stage ('Delete Build Images') {
     		sh "docker rm -f hello-${env.BUILD_NUMBER}"
     		sh "docker rmi -f 192.168.146.133/hello/hello:${env.BUILD_NUMBER}"
        }
		

   

    }

}