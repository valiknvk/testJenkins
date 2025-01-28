import jenkins.model.*

class BazelCleaner {

    // Get all agents (nodes)
    static List getAgents() {
        def agents = Jenkins.instance.nodes
        println "Found agents: ${agents*.nodeName}"
        return agents
    }

    // Check if an agent is online
    static boolean isAgentOnline(def agent) {
        def isOnline = agent?.toComputer()?.isOnline() ?: false
        println "Agent ${agent.nodeName} is online: ${isOnline}"
        return isOnline
    }

    // Get the computer object of an agent
    static def getComputer(def agent) {
        def computer = agent?.toComputer()
        if (computer) {
            println "Computer for agent ${agent.nodeName} obtained."
            return computer
        } else {
            throw new IllegalArgumentException("Could not get computer for agent: ${agent.nodeName}")
        }
    }

    // Get the name of the agent (node)
    static String getNodeName(def agent) {
        if (agent?.nodeName) {
            println "Agent name: ${agent.nodeName}"
            return agent.nodeName
        } else {
            throw new IllegalArgumentException("Invalid agent provided")
        }
    }

    // Check if the agent's computer is idle
    static boolean isComputerIdle(def computer) {
        def isIdle = computer?.countBusy() == 0
        println "Computer is idle: ${isIdle}"
        return isIdle
    }

    // Set an agent temporarily offline
    static void setTempOffline(def computer, String message = "Cleanup in progress") {
        if (computer) {
            computer.setTemporarilyOffline(true, new hudson.slaves.OfflineCause.ByCLI(message))
            println "Computer set temporarily offline with message: ${message}"
        } else {
            throw new IllegalArgumentException("Invalid computer provided")
        }
    }

    // Bring an agent back online
    static void returnOnline(def computer) {
        if (computer) {
            computer.setTemporarilyOffline(false, null)
            println "Computer brought back online."
        } else {
            throw new IllegalArgumentException("Invalid computer provided")
        }
    }

    // Clean Bazel cache directories
    static void cleanBazelCache() {
        //def cachePaths = ['$HOME/.bzl_cache', '$HOME/.cache/bazel']
        def cachePaths = ['/var/lib/jenkins/.cache']        
        cachePaths.each { path ->
            sh """
                if [ -d "${path}" ]; then
                    echo "Cleaning cache at ${path}..."
                    find ${path} -type d -mtime +7 -exec rm -rf {} +
                    echo "Cache cleaned at ${path}."
                else
                    echo "Cache directory ${path} does not exist. Skipping."
                fi
            """
        }
    }
}
