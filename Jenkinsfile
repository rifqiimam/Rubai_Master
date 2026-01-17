pipeline {
    agent any
    
    parameters {
        string(name: 'VERSION', defaultValue: 'latest', description: 'Docker image version to deploy')
    }
    
    environment {
        DOCKER_IMAGE = 'rifqiimamr/rubai-master'
        CONTAINER_NAME = 'rubai-master'
        CONFIG_PATH = '/home/deploy/ci-cd/rubai-master/config'
        LOGS_PATH = '/home/deploy/ci-cd/rubai-master/logs'
        IMAGES_PATH = '/home/deploy/ci-cd/images/rubai-master'
        PORT = '8301'
    }

    stages {
        stage('Deploy') {
            steps {
                sh """
                    # Pull latest image
                    docker pull ${DOCKER_IMAGE}:${params.VERSION}

                    # Stop and remove old container
                    docker stop ${CONTAINER_NAME} || true
                    docker rm ${CONTAINER_NAME} || true

                    # Run new container
                    docker run -d --name ${CONTAINER_NAME} \
                        -p ${PORT}:${PORT} \
                        -v /home/deploy/ci-cd/rubai-master/config:/app/config:ro \
                        -v ${LOGS_PATH}:/app/logs \
                        -v ${IMAGES_PATH}:/app/images \
                        -e SPRING_PROFILES_ACTIVE=prod \
                        --restart unless-stopped \
                        ${DOCKER_IMAGE}:${params.VERSION}

                    # Wait and check container status
                    echo "Waiting for application to start..."
                    sleep 10

                    # Check if container is still running
                    if ! docker ps | grep -q ${CONTAINER_NAME}; then
                        echo "❌ Container stopped unexpectedly!"
                        docker logs ${CONTAINER_NAME} --tail 50
                        exit 1
                    fi

                    # Wait more for app to fully start
                    sleep 20

                    # Health check with better error handling
                    echo "Running health check..."
                    for i in {1..5}; do
                        if curl -f http://localhost:${PORT}/actuator/health; then
                            echo "✅ Health check passed!"
                            break
                        else
                            echo "Attempt \$i failed, retrying in 5 seconds..."
                            if [ \$i -eq 5 ]; then
                                echo "❌ Health check failed after 5 attempts"
                                docker logs ${CONTAINER_NAME} --tail 100
                                exit 1
                            fi
                            sleep 5
                        fi
                    done

                    # Clean old images
                    docker image prune -f
                """
            }
        }
    }

    post {
        success {
            echo "✅ Deployment successful! Version: ${params.VERSION}"
        }
        failure {
            echo "❌ Deployment failed!"
            sh "docker logs ${CONTAINER_NAME} --tail 100 || true"
        }
    }
}