package com.postack.util

enum class Environment {
    DEVELOPMENT, PRODUCTION
}
fun getProjectRoot(env: Environment): String {
    return when (env) {
        Environment.DEVELOPMENT -> "./"
        Environment.PRODUCTION -> "/root/Postack-Server/"
    }
}