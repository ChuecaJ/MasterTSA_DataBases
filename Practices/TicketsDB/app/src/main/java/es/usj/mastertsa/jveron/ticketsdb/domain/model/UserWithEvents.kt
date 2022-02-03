package es.usj.mastertsa.jveron.ticketsdb.domain.model

data class UserWithEvents (
    val user: User,
    val events: List<Event>
    )