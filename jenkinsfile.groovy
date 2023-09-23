pipeline{

    agent any

    environment{

        DOCKERHUB_USERNAME = "sharukdoc"
        APP_NAME = "gitops-argo-app"
        IMAGE_TAG = "${BUILD_NUMBER}"
        IMAGE_NAME = "${DOCKERHUB_USERNAME}" + "/" + "${APP_NMAE}"
        REGISTRY_CREDS = "dockerhub"
    }

    stages{

        stage('Cleanup workspace'){

            steps{
                script{

                    cleanWs
                }
            }
        }
    }
}
stage