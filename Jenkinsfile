pipeline {
    agent any
    tools {
        maven 'my-maven'
    }
    environment {
        MY_SQL_LOGIN = credentials('mysql-root-login')

    }
    stages {
        stage('build with maven'){
            steps {
                sh 'mvn --version'
                sh 'java -version'
                sh 'mvn clean package -Dmaven.test.failure.ignore=true' 
            }
        }
        stage('packaging/pushing image'){
            steps{
                withDockerRegistry(credentialsId: 'dockerhub', url: 'https://index.docker.io/v1/') {
                    sh 'docker build -t vohoangminhsang/springboot .'
                    sh 'docker push vohoangminhsang/springboot:latest'
                }
            }
        }
        stage('deploy MySQL to dev'){
            steps{
                echo 'Deploying and Cleaning'
                sh 'docker image pull mysql:latest'
                sh 'docker network create dev || echo "this network exists"'
                sh 'docker container stop mysql || echo "this container does not exist"'
                sh 'docker container prune'
                sh 'docker volume rm mysql-data || echo "no volume"'
        
                sh 'docker run --name mysql --rm --network dev -v mysql-data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=${MYSQL_ROOT_LOGIN_PSW} -d mysql:latest'
                sh 'sleep 15'
                sh 'docker exec -i mysql mysql --user=root --password=${MYSQL_ROOT_LOGIN_PSW} < script'
                
            }
        }
        stage('Deploy Spring Boot to DEV') {
            steps {
                echo 'Deploying and cleaning'
                sh 'docker image pull vohoangminhsang/springboot'
                sh 'docker container stop vosang-springboot || echo "this container does not exist" '
                sh 'docker network create dev || echo "this network exists"'
                sh 'echo y | docker container prune '

                sh 'docker container run -d --rm --name vosang-springboot -p 8081:8080 --network dev vohoangminhsang/springboot'
            }
        }
    }
    post {
        // Clean after build
        always {
            cleanWs()
        }
    }
}
