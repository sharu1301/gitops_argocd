pipeline {
    agent any

    environment {
        DOCKERHUB_USERNAME = "sharukdoc"
        APP_NAME = "gitops-argo-app"
        IMAGE_TAG = "${BUILD_NUMBER}"
        IMAGE_NAME = "${DOCKERHUB_USERNAME}/${APP_NAME}" // Fix the typo here
        REGISTRY_CREDS = "dockerhub"
    }

    stages {
        stage('Cleanup workspace') {
            steps {
                script {
                    cleanWs()
                }
            }
        }

        stage('checkout SCM') {
            steps {
                script {
                    git credentialsId: 'github', // Fix the credentialId attribute
                        url: 'https://github.com/sharu1301/gitops_argocd.git',
                        branch: 'main'
                }
            }
        }

        // Add more stages as needed
    }
}
stage('Build Docker Image'){
    steps{
        script{

            docker_image = docker.build "${IMAGE_NAME}"
        }
    }
}
stage('Push Docker Image'){
     steps{
        script{

            docker.withRegistry('',REGISTRY_CREDS){
                docker_image.Push("$BUILD_NUMBER")
                docker_image.Push('latest')
            }
        }
     }
}