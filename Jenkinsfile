
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
     	
	     	try{
	     	    sh "docker rm -f hello"
	     	}catch(e){
	     	    echo '容器不存在'
	     	}
	     	
	     	def cleanImage1="\$(docker images -f 'dangling=true' -q)"
	     	if("! -n '$cleanImage1'"){
	     	    echo 'no need none image to clean up images. '
	     	}else{
	     	    sh "docker rmi \$(docker images -f 'dangling=true' -q)"
	     	    echo 'clean none images success'
	     	}

			def cleanImage2="\$(docker images | grep hello | awk '{print \$3}')"
	     	if("! -n '$cleanImage2'"){
	     	    echo 'no need hello image to clean up images.'
	     	}else{
	     	    sh "docker rmi \$(docker images -f 'dangling=true' -q)"
	     	    echo 'clean hello images success'
	     	}
	     	
        }
		stage ('Run Images'){
			docker.image("192.168.146.133/hello/hello:${env.BUILD_NUMBER}").run('-p 9090:8080 --name hello')           
		}
    }

}