
node {

    withMaven(maven:'maven') {

        stage('Checkout') {
            git url: 'https://github.com/153603165/hello.git', branch: 'master', credentialsId: '6438e28b-a416-4c9c-8398-3ef55ddc9f80'
        }

        stage('Build') {
            sh 'mvn clean package docker:build -DskipTests'
        }

        stage('Image And Push') {
     	   withDockerRegistry(registry: [credentialsId: '3e855734-cbe7-4430-811d-c9216df0769f', url: 'http://192.168.0.191']) {
	            def app = docker.build ("192.168.0.191/hello/hello:${env.BUILD_NUMBER}")
	            app.push()
	        }
		}
    	 
		stage('Change Yaml'){
		    sh "sed -i 's/<BUILD_TAG>/${env.BUILD_NUMBER}/' k8s.yaml"
		}
		stage('Deploy'){
	   	 	sh "kubectl apply -f k8s.yaml"
		}

    }

}