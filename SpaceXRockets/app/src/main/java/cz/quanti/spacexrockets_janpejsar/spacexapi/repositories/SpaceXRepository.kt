package cz.quanti.spacexrockets_janpejsar.spacexapi.repositories

import cz.quanti.spacexrockets_janpejsar.spacexapi.entities.Rocket

interface SpaceXRepository {
    fun getRockets(success: (rockets: List<Rocket>?) -> Unit, failure: (t: Throwable?) -> Unit)
}