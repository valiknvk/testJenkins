import jenkins.model.*

class BazelCleaner {

    // Paths to Bazel cache directories
    static final List<String> BAZEL_CACHE_PATHS = [
        '~/home/.bzl_cache',
        '~/home/.cache/bazel'
    ]

    // Get all agents (nodes)
    static List getAgents() {
        return Jenkins.instance.nodes
    }

    // Check if an agent is online
    static boolean isAgentOnline(def agent) {
        if (agent?.toComputer()) {
            return agent.toComputer().isOnline()
        } else {
            throw new IllegalArgumentException("Invalid agent provided: ${agent}")
        }
    }

    // Get the computer object of an agent
    static def getComputer(def agent) {
        def computer = agent?.toComputer()
        if (computer) {
            return computer
        } else {
            throw new IllegalArgumentException("Could not get computer for agent: ${agent.nodeName}")
        }
    }

    // Get the name of the agent (node)
    static String getNodeName(def agent) {
        if (agent?.nodeName) {
            return agent.nodeName
        } else {
            throw new IllegalArgumentException("Invalid agent provided: ${agent}")
        }
    }

    // Check if the agent's computer is idle
    static boolean isComputerIdle(def computer) {
        if (computer) {
            return computer.countBusy() == 0
        } else {
            throw new IllegalArgumentException("Invalid computer provided")
        }
    }

    // Set an agent temporarily offline
    static void setTempOffline(def computer, String message = "Cleanup in progress") {
        if (computer) {
            computer.setTemporarilyOffline(true, new hudson.slaves.OfflineCause.ByCLI(message))
        } else {
            throw new IllegalArgumentException("Invalid computer provided")
        }
    }

    // Bring an agent back online
    static void returnOnline(def computer) {
        if (computer) {
            computer.setTemporarilyOffline(false, null)
        } else {
            throw new IllegalArgumentException("Invalid computer provided")
        }
    }

    // Clean Bazel cache directories
    static void cleanBazelCache() {
        BAZEL_CACHE_PATHS.each { path ->
            def expandedPath = path.replace('~', System.getProperty("user.home")) 
            sh """
                if [ -d "${expandedPath}" ]; then
                    echo "Cleaning cache at ${expandedPath}..."
                    find ${expandedPath} -type d -mtime +7 -exec rm -rf {} +
                    echo "Cache cleaned at ${expandedPath}."
                else
                    echo "Cache directory ${expandedPath} does not exist. Skipping."
                fi
            """
        }
    }
}
