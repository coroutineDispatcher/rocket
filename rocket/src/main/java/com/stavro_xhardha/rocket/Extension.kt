package com.stavro_xhardha.rocket

/**
 * Extension function for Rocket
 * @param rocket: Your Rocket Instance
 * @return true if the read String exists, false if not
 */
suspend fun String.isDefaultString(rocket: Rocket): Boolean? = rocket.readString(this).isEmpty()

/**
 * Extension function for Rocket
 * @param rocket: Your Rocket Instance
 * @return true if the read Int exists, false if not
 */
suspend fun String.isDefaultInt(rocket: Rocket): Boolean = rocket.readInt(this) == 0

/**
 * Extension function for Rocket
 * @param rocket: Your Rocket Instance
 * @return true if the read Float exists, false if not
k */
suspend fun String.isDefaultFloat(rocket: Rocket): Boolean = rocket.readFloat(this) == 0f

/**
 * Extension function for Rocket
 * @param rocket: Your Rocket Instance
 * @return true if the read Boolean exists, false if not
 */
suspend fun String.isDefaultBoolean(rocket: Rocket): Boolean = rocket.readBoolean(this)

/**
 * Extension function for Rocket
 * @param rocket: Your Rocket Instance
 * @return true if the read Long exists, false if not
 */
suspend fun String.isDefaultLong(rocket: Rocket): Boolean = rocket.readLong(this) == 0L