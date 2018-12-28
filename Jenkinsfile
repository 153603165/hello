
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
     		//sh "docker rmi -f hello"
     		sh "docker rmi --force `docker images | grep hello | awk '{print \$3}'`"
        }
		stage ('Run Images'){
			docker.image("192.168.146.133/hello/hello:${env.BUILD_NUMBER}").run('-p -d 9090:8080 --name hello')           
		}
    }

}