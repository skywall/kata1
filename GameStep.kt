package cz.skywall.kata1

data class GameStep(
        val gameVariant: GameVariant,
        val previousStep: GameStep?
)
