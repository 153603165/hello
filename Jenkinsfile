
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

        stage('Image') {
            def app = docker.build ("192.168.146.133/hello/hello:${env.BUILD_NUMBER}")
            app.push()
        }

     	 //stage ('Run') {
          //  docker.image("192.168.146.133/hello:${env.BUILD_NUMBER}").run('-p 2222:8080 -h hello -name hello ')
        //}


   

    }

}