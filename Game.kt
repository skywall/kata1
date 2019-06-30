package cz.skywall.kata1

class Game {
    companion object {
        val INITIAL_STEP = GameStep(
                GameVariant(
                        LeftBank(setOf(Goose, Fox, Corn, Me)),
                        Boat(emptySet()),
                        RightBank(emptySet())),
                previousStep = null
        )
    }

    private val processedVariants = mutableSetOf<GameVariant>()
    private val availableSteps = mutableSetOf<GameStep>()
    private val validators: GameValidators = GameValidators()

    private fun computeFinalStep(): GameStep {
        var currentStep: GameStep? = INITIAL_STEP

        while (true) {
            // generate all next possible variants from current variant
            val variants = currentStep?.gameVariant?.generateVariants() ?: emptySet<GameVariant>()

            for (variant in variants) {
                // we have reached end
                if (variant.isFinalVariant()) {
                    return GameStep(variant, currentStep)
                }

                // variant already processed
                if (processedVariants.contains(variant)) {
                    continue
                }

                // variant invalid
                if (!validators.isValid(variant)) {
                    processedVariants.add(variant)
                    continue
                }

                availableSteps.add(GameStep(variant, currentStep))
            }

            // take first step in set of possible steps
            currentStep = availableSteps.first()

            availableSteps.remove(currentStep)
            processedVariants.add(currentStep.gameVariant)
        }
    }

    fun start() {
        val finalStep = computeFinalStep()
        printResult(finalStep)
    }

    private fun printResult(gameStep: GameStep) {
        if (gameStep.previousStep != null) {
            // recursively find first step
            printResult(gameStep.previousStep)
        }
        println(gameStep.gameVariant.toString())
    }
}
