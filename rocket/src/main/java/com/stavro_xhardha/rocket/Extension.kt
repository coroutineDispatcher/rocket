package com.stavro_xhardha.rocket

/**
 * Extension function for Rocket
 * @param rocket: Your Rocket Instance
 * @return true if the read String exists, false if not
 * @throws KotlinNullPointerException if rocket instance is not provided
 */
suspend fun String.isDefaultString(rocket: Rocket?): Boolean? =
    if (rocket == null) throw KotlinNullPointerException("Have your initialized your Rocket instance?")
    else rocket.readString(this)?.isEmpty()

/**
 * Extension function for Rocket
 * @param rocket: Your Rocket Instance
 * @return true if the read Int exists, false if not
 * @throws KotlinNullPointerException if rocket instance is not provided
 */
suspend fun String.isDefaultInt(rocket: Rocket?): Boolean =
    if (rocket == null) throw KotlinNullPointerException("Have your initialized your Rocket instance?")
    else rocket.readInt(this) == 0

/**
 * Extension function for Rocket
 * @param rocket: Your Rocket Instance
 * @return true if the read Float exists, false if not
 * @throws KotlinNullPointerException if rocket instance is not provided
 */
suspend fun String.isDefaultFloat(rocket: Rocket?): Boolean =
    if (rocket == null) throw KotlinNullPointerException("Have your initialized your Rocket instance?")
    else rocket.readFloat(this) == 0f

/**
 * Extension function for Rocket
 * @param rocket: Your Rocket Instance
 * @return true if the read Boolean exists, false if not
 * @throws KotlinNullPointerException if rocket instance is not provided
 */
suspend fun String.isDefaultBoolean(rocket: Rocket?): Boolean =
    rocket?.readBoolean(this) ?: throw KotlinNullPointerException("Have your initialized your Rocket instance?")

/**
 * Extension function for Rocket
 * @param rocket: Your Rocket Instance
 * @return true if the read Long exists, false if not
 * @throws KotlinNullPointerException if rocket instance is not provided
 */
suspend fun String.isDefaultLong(rocket: Rocket?): Boolean =
    if (rocket == null) throw KotlinNullPointerException("Have your initialized your Rocket instance?") else rocket.readLong(
        this
    ) == 0L