package cz.quanti.spacexrockets_janpejsar.repositories

import cz.quanti.spacexrockets_janpejsar.entities.Rocket

interface SpaceXRepository {
    fun getRockets(success: (rockets: List<Rocket>?) -> Unit, failure: (t: Throwable?) -> Unit)
}