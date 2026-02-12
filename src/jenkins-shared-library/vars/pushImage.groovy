def call(String imageName, String imageTag) {
    withCredentials([usernamePassword(
        credentialsId: 'dockerhub-creds',
        usernameVariable: 'DOCKER_USER',
        passwordVariable: 'DOCKER_PASS'
    )]){
      sh """
        docker push ${imageName}:${imageTag}
      """
    }
}
