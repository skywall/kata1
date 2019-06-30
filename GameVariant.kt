package cz.skywall.kata1

data class GameVariant(
        val leftBank: LeftBank,
        val boat: Boat,
        val rightBank: RightBank
) {
    fun generateVariants(): List<GameVariant> {
        val variants = mutableListOf<GameVariant>()

        if (leftBank.objects.contains(Me)) {
            // I'm taking some item to the boat
            val objectsLeftBank = leftBank.objects.filter { it != Me }
            objectsLeftBank.forEach {
                variants.add(GameVariant(
                        LeftBank(objectsLeftBank.toSet().minus(it)),
                        Boat(setOf(Me, it)),
                        RightBank(rightBank.objects)
                ))
            }

            // I'm moving alone in a boat
            variants.add(GameVariant(
                    LeftBank(leftBank.objects.minus(Me)),
                    Boat(setOf(Me)),
                    RightBank(rightBank.objects)
            ))
        }

        if (boat.objects.contains(Me)) {
            // I'm stepping out of boat (with possible item) to left bank
            variants.add(GameVariant(
                    LeftBank(leftBank.objects.plus(boat.objects)),
                    Boat(emptySet()),
                    RightBank(rightBank.objects)
            ))

            // I'm stepping out of boat (with possible item) to right bank
            variants.add(GameVariant(
                    LeftBank(leftBank.objects),
                    Boat(emptySet()),
                    RightBank(rightBank.objects.plus(boat.objects))
            ))
        }

        if (rightBank.objects.contains(Me)) {
            // I'm taking some item to the boat
            val objectsRightBank = rightBank.objects.filter { it != Me }
            objectsRightBank.forEach {
                variants.add(GameVariant(
                        LeftBank(leftBank.objects),
                        Boat(setOf(Me, it)),
                        RightBank(objectsRightBank.toSet().minus(it))
                ))
            }

            // I'm moving alone in a boat
            variants.add(GameVariant(
                    LeftBank(leftBank.objects),
                    Boat(setOf(Me)),
                    RightBank(rightBank.objects.minus(Me))
            ))
        }

        return variants
    }

    fun isFinalVariant(): Boolean {
        return leftBank.objects.isEmpty() &&
                boat.objects.isEmpty() &&
                rightBank.objects.containsAll(setOf(Goose, Fox, Corn, Me))
    }

    override fun toString(): String {
        return "[$leftBank $boat $rightBank]"
    }
}
