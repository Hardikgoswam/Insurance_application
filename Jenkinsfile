pipeline {
    agent any

    environment {
        DOCKERHUB_USER = 'hardik121'
        K8S_CLUSTER_NAME = 'EKS-1'
        K8S_NAMESPACE = 'webapps'
        K8S_SERVER_URL = 'https://A30681710B592A1E6E970C49FD6627B6.gr7.ap-south-1.eks.amazonaws.com'
    }

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'master', url: 'https://github.com/Hardikgoswam/Insurance_application.git'
            }
        }

        stage('Build & Push Docker Image') {
            steps {
                script {
                    sh """
                        docker build -t ${DOCKERHUB_USER}/insurance-app:latest .
                        docker push ${DOCKERHUB_USER}/insurance-app:latest
                    """
                }
            }
        }

        stage('Deploy To Kubernetes') {
            steps {
                withKubeCredentials(kubectlCredentials: [[
                    clusterName: "${K8S_CLUSTER_NAME}",
                    credentialsId: 'k8-token',
                    namespace: "${K8S_NAMESPACE}",
                    serverUrl: "${K8S_SERVER_URL}"
                ]]) {
                    sh "kubectl apply -f deployment-service.yml"
                }
            }
        }
        
        stage('Verify Deployment') {
            steps {
                withKubeCredentials(kubectlCredentials: [[
                    clusterName: "${K8S_CLUSTER_NAME}",
                    credentialsId: 'k8-token',
                    namespace: "${K8S_NAMESPACE}",
                    serverUrl: "${K8S_SERVER_URL}"
                ]]) {
                    sh "kubectl get pods -n ${K8S_NAMESPACE}"
                    sh "kubectl get svc -n ${K8S_NAMESPACE}"
                }
            }
        }
    }
}
