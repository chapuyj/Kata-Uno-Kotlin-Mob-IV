class Game(val players: List<Player>) {

    companion object {
        const val MIN_NUMBER_OF_PLAYER = 2
        const val MAX_NUMBER_OF_PLAYER = 10

        const val START_DECK_SIZE = 108
        const val START_HAND_SIZE = 7
    }

    var currentPlayer: Player = NullPlayer()
    val discard = mutableListOf<Card>()
    val deck = mutableListOf<Card>()

    init {

        if(players.size !in MIN_NUMBER_OF_PLAYER..MAX_NUMBER_OF_PLAYER) throw InvalidNumberOfPlayersException()

        repeat(START_DECK_SIZE){
            deck.add(Card())
        }
    }

    fun start() {
        dealCards()
        putFirstCardInDiscard()
        pickFirstPlayer()
    }

    private fun pickFirstPlayer() {
        currentPlayer = players.first()
    }

    private fun dealCards() {
        players.forEach { player ->
            dealCards(player)
        }
    }

    private fun dealCards(player: Player) {
        repeat(START_HAND_SIZE) {
            val card = deck.removeAt(0)
            player.hand.add(card)
        }
    }

    private fun putFirstCardInDiscard() {
        val card = deck.removeAt(0)
        discard.add(card)
    }

    fun cardPlayed() {
        val card = currentPlayer.hand.removeAt(0)
        discard.add(card)
    }
}

class InvalidNumberOfPlayersException : RuntimeException() {}