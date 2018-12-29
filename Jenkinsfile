
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
    	 /**stage ('Delete Build Images') {
	     	try{
	     	    sh "docker rm -f hello"
	     	}catch(e){
	     	    echo '容器不存在'
	     	}

	     	try{
	     	   sh "docker rmi --force `docker images | grep hello | awk '{print \$3}'`"
	     	}catch(e){
	     	    echo '镜像不存在'
	     	    
	     	}
	     	try{
	     	   sh "docker rmi \$(docker images -f 'dangling=true' -q)"
	     	}catch(e){
	     	    echo 'none镜像不存在'
	     	}
        }
		stage ('Run Images'){
			docker.image("192.168.146.133/hello/hello:${env.BUILD_NUMBER}").run('-p 9090:8080 --name hello')           
		}*/	
		stage('Change Yaml'){
		    sh "sed -i 's/<BUILD_TAG>/${env.BUILD_NUMBER}/' k8s.yaml"
		}
		stage('Deploy'){
			try{
				sh " kubectl rolling-update hello-v0.1 --update-period=10s -f k8s.yaml"
			}catch(e){
		   	 	sh "kubectl create -f k8s.yaml"
			}
		}


    }

}