package cz.skywall.kata1

sealed class Spot(val objects: Set<GameObject>) {

    override fun toString(): String {
        return "[" + objects.joinToString(" ") + "]"
    }

    override fun equals(other: Any?): Boolean {
        return objects == other
    }

    override fun hashCode(): Int {
        return objects.hashCode()
    }
}

class LeftBank(objects: Set<GameObject>) : Spot(objects)

class Boat(objects: Set<GameObject>) : Spot(objects) {
    override fun toString(): String {
        return if (objects.isEmpty()) {
            "[:clun]"
        } else {
            "[:clun " + objects.joinToString(" ") + "]"
        }
    }
}

class RightBank(objects: Set<GameObject>) : Spot(objects)
