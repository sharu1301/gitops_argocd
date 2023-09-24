pipeline {
    agent any

    environment {
        DOCKERHUB_USERNAME = "sharukdoc"
        APP_NAME = "gitops-argo-app"
        IMAGE_TAG = "${BUILD_NUMBER}"
        IMAGE_NAME = "${DOCKERHUB_USERNAME}/${APP_NAME}" // Fix the typo here
        REGISTRY_CREDS = "Dockerhub"
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

        stage('Build Docker Image') {
            steps {
                script {
                    docker_image = docker.build "${IMAGE_NAME}:${IMAGE_TAG}" // Use the IMAGE_TAG
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    docker.withRegistry('', REGISTRY_CREDS) {
                        docker_image.push("${IMAGE_TAG}") // Push the specific image tag
                        docker_image.push('latest') // Push the 'latest' tag if needed
                    }
                }
            }
        }

        stage('Delete Docker Images') {

    steps{
        script{

            sh "docker rmi ${IMAGE_NAME}:${IMAGE_TAG}"
            sh "docker rmi ${IMAGE_NAME}:latest"
        }
    }
        }

        stage('updating kubernetes deployment file'){
            steps{
                script{

                    sh """
                    cat deployment.yaml
                    sed -i 's/${APP_NAME}.*/${APP_NAME}:${IMAGE_TAG}/g' deployment.yaml
                    cat deployment.yaml
                    """
                }
            }
        }

        stage('Push the changed deployment file to Git'){
            steps{
                script{
                    sh """
                        git config global --user.name "sharu1301"
                        git config global --user.email "reachtosharuk@gmail.com"
                        git add deployment.yaml 
                        git commit -m "updated"
                        """
                    withCredentials([gitUsernamePassword(credentialsId: 'github', gitToolName: 'Default')]) {

                   sh "git push https://github.com/sharu1301/gitops_argocd.git main"
                    }
                }
            }      
    }
}
