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
stage('checkout SCM') {

    steps{
        script{
            git credentialId: 'github',
            url: 'https://github.com/sharu1301/gitops_argocd.git',
            branch: 'main'

        }
    }
}
