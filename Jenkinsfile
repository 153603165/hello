
node {

    withMaven(maven:'maven') {

        stage('Checkout') {
            git url: 'https://github.com/153603165/hello.git', branch: 'master'
        }

        stage('Build') {
            sh 'mvn clean install'

            def pom = readMavenPom file: 'pom.xml'
            print pom.version
            env.version = pom.version
        }

    	def imagesName = '192.168.146.133:80/hello/${env.JOB_NAME}:${env.version}.${env.BUILD_NUMBER}' 
        stage('Image') {
          	docker.withRegistry('http://192.168.146.13:80', 'admin') {
		      	docker.build(imagesName).push()
	    	}
        	//def dockerfile = 'Dockerfile'
          //  def app = docker.build ("192.168.146.133/hello/${env.JOB_NAME}:${env.version}.${env.BUILD_NUMBER} ","-f ${dockerfile} .")
           // app.push()
        }

		//stage ('delete build images') {
      //      sh "docker rmi 192.168.146.133/hello/${env.JOB_NAME}:${env.BUILD_NUMBER}"
       // }
     	 
     	stage ('Run') {
     		 // 需要删除旧版本的容器，否则会导致端口占用而无法启动。
		    try{
		      sh 'docker rm -f hello'
		    }catch(e){
		        // err message
		    }
		    docker.image(imagesName).run('-p 2222:8080 --name hello') 
        }


   

    }

}