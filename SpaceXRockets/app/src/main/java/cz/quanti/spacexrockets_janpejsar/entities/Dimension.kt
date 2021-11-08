package cz.quanti.spacexrockets_janpejsar.entities

data class Dimension(
    val meters: Float,
    val feet: Float
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Dimension

        if (meters != other.meters) return false
        if (feet != other.feet) return false

        return true
    }

    override fun hashCode(): Int {
        var result = meters.hashCode()
        result = 31 * result + feet.hashCode()
        return result
    }
}