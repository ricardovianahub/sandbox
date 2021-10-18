pipelineJob('fakeresponseDeployToDocker') {
    definition {
        cpsScm {
            scm {
                git {
                    remote {
                        url 'https://github.com/ricardovianahub/sandbox.git'
                    }
                    branch 'aalatest'
                    scriptPath('docker/mod11qaautomation/fakeresponse/Jenkinsfile')
                }
            }
        }
    }
}
