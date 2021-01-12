pipelineJob('fakeresponseDeployToDocker') {
    definition {
        cpsScm {
            scm {
                git {
                    remote {
                        url 'https://github.com/ricardovianahub/sandbox.git'
                    }
                    branch 'aalatest'
                    scriptPath('docker/qaautomation/fakeresponse/Jenkinsfile')
                }
            }
        }
    }
}
