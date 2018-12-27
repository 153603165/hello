
node {

    withMaven(maven:'maven') {

        stage('Checkout') {
            git url: 'https://github.com/153603165/hello.git', credentialsId: '153603165@qq.com', branch: 'master'
        }

        stage('Build') {
            sh 'mvn clean install'

            def pom = readMavenPom file: 'pom.xml'
            print pom.version
            env.version = pom.version
        }

        stage('Image') {
            def app = docker.build "192.168.146.133/project/hello:${env.version} "
            app.push()
        }

        stage ('k8sRcRun') {
           sh 'kubectl create -f /home/fengfan/hello-rc.yml'
        }
        
        stage('k8sServiceRun'){
			sh 'kubectl create -f /home/fengfan/hello-svc.yml'            
        }

   

    }

}