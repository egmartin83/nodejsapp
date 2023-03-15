job('Aplicacion Node.js Docker DSL EMITO') {
    description('Aplicación Node JS Docker DSL para el curso de Jenkins')
    scm {
        git('https://github.com/egmartin83/nodejsapp.git', 'master') { node ->
            node / gitConfigName('egmartin83')
            node / gitConfigEmail('egmartin83@gmail.com')
        }
    }
    wrappers {
        nodejs('nodejs')
    }
    steps {
        dockerBuildAndPublish {
            repositoryName('egmartin83/nodejsapp')
	    labelExpression('jenkins-agent')
	    dockerHostURI('tcp://192.168.49.2:2376')
	    serverCredentials('3c1cb828-0a01-48b0-8522-9cde5b1cfb00')
            tag('${GIT_REVISION,length=7}')
            registryCredentials('dockerhub')
            forcePull(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
    publishers {
	slackNotifier {
            notifyAborted(true)
            notifyEveryFailure(true)
            notifyNotBuilt(false)
            notifyUnstable(false)
            notifyBackToNormal(true)
            notifySuccess(true)
            notifyRepeatedFailure(false)
            startNotification(false)
            includeTestSummary(false)
            includeCustomMessage(false)
            customMessage(null)
            sendAs(null)
            commitInfoChoice('NONE')
            teamDomain(null)
            authToken(null)
        }
    }
}
