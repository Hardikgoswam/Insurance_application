pipeline {
    agent any

    environment {
        DOCKERHUB_USER = 'hardik121'
        K8S_CLUSTER_NAME = 'EKS-1'
        K8S_NAMESPACE = 'webapps'
        K8S_SERVER_URL = 'https://A30681710B592A1E6E970C49FD6627B6.gr7.ap-south-1.eks.amazonaws.com'
        MICRO_SERVICES = "Admin-Server,api-gateway,application-registrations,config-server,data-collection,eligibility-service,eureka-server,admin-api,benefit-insurance-api,correspondence-api,user-mgmt-api,ssn-service"
    }

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'master', url: 'https://github.com/Hardikgoswam/Insurance_application.git'
            }
        }

        stage('Build & Push Docker Images') {
            steps {
                script {
                    def services = MICRO_SERVICES.split(',')

                    for (service in services) {
                        def dockerImagePath = "${WORKSPACE}/${service}"
                        def imageName = "${DOCKERHUB_USER}/${service}:latest"
                        echo "Building Docker image for ${service}..."

                        sh """
                            if [ -d "${dockerImagePath}" ]; then
                                docker build -t ${imageName} -f ${dockerImagePath}/Dockerfile ${dockerImagePath}
                                docker push ${imageName}
                            else
                                echo "Directory ${dockerImagePath} not found. Skipping..."
                            fi
                        """
                    }
                }
            }
        }

        stage('Deploy To Kubernetes') {
            steps {
                withKubeCredentials(kubectlCredentials: [[
                    caCertificate: '',
                    clusterName: "${K8S_CLUSTER_NAME}",
                    contextName: '',
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
                    caCertificate: '',
                    clusterName: "${K8S_CLUSTER_NAME}",
                    contextName: '',
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
