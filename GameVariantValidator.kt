package cz.skywall.kata1

class GameValidators {
    private val validators = listOf(
            FoxGooseAloneValidator,
            CornGooseAloneValidator
    )

    // Check validity on all of the implemented validators
    fun isValid(gameVariant: GameVariant): Boolean {
        return validators.fold(
                true,
                { acc, validator -> acc && validator.isValid(gameVariant) }
        )
    }
}

sealed class GameVariantValidator {
    abstract fun isValid(gameVariant: GameVariant): Boolean
}

object FoxGooseAloneValidator : GameVariantValidator() {

    override fun isValid(gameVariant: GameVariant): Boolean {
        val leftBankBoth = gameVariant.leftBank.objects.containsAll(setOf(Fox, Goose))
        val leftBankMe = gameVariant.leftBank.objects.contains(Me)

        val rightBankBoth = gameVariant.rightBank.objects.containsAll(setOf(Fox, Goose))
        val rightBankMe = gameVariant.rightBank.objects.contains(Me)

        if (leftBankBoth) {
            return leftBankMe
        }

        if (rightBankBoth) {
            return rightBankMe
        }

        return true
    }
}

object CornGooseAloneValidator : GameVariantValidator() {

    override fun isValid(gameVariant: GameVariant): Boolean {
        val leftBankBoth = gameVariant.leftBank.objects.containsAll(setOf(Corn, Goose))
        val leftBankMe = gameVariant.leftBank.objects.contains(Me)

        val rightBankBoth = gameVariant.rightBank.objects.containsAll(setOf(Corn, Goose))
        val rightBankMe = gameVariant.rightBank.objects.contains(Me)

        if (leftBankBoth) {
            return leftBankMe
        }

        if (rightBankBoth) {
            return rightBankMe
        }

        return true
    }
}

