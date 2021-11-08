package cz.quanti.spacexrockets_janpejsar.entities

data class Mass(
    val kg: Int,
    val lb: Int
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Mass

        if (kg != other.kg) return false
        if (lb != other.lb) return false

        return true
    }

    override fun hashCode(): Int {
        var result = kg
        result = 31 * result + lb
        return result
    }
}