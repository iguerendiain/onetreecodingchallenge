package onetree.ignacio.guerendiain.core.domain.model

import kotlinx.datetime.Instant

data class Task(
    val id: Long,
    val name: String,
    val description: String,
    val done: Boolean,
    val creationTime: Instant,
    val updateTime: Instant,
    val location: Pair<Double, Double>?
)