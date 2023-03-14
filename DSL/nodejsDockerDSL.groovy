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
        DockerBuilderPublisher {
            image ('egmartin83/nodejsapp')
            cloud ('docker')
            pushCredentialsId ('dockerhub')
            pushOnSuccess (true)
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
