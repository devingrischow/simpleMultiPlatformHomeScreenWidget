package org.devg.app

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform