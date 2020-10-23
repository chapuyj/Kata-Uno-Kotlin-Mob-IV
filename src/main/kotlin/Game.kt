class Game(val players: List<Player>) {

    companion object {
        const val MIN_NUMBER_OF_PLAYER = 2
        const val MAX_NUMBER_OF_PLAYER = 10

        const val START_HAND_SIZE = 7
    }

    var currentPlayer: Player = NullPlayer()
    lateinit var discard : Card
    var deck = Deck.createStarterDeck()
        private set(value) {
            field = value
        }

    init {
        if(players.size !in MIN_NUMBER_OF_PLAYER..MAX_NUMBER_OF_PLAYER) throw InvalidNumberOfPlayersException()
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
        val drawn = draw(START_HAND_SIZE)
        player.hand += drawn
    }

    private fun putFirstCardInDiscard() {
        discard = draw(1).first()
    }

    private fun draw(number: Int) : List<Card> {
        val (drawn, newDeck) = deck.draw(number)
        deck = newDeck
        return drawn
    }

    fun cardPlayed() {
        discard = currentPlayer.hand.removeAt(0)
    }
}

class InvalidNumberOfPlayersException : RuntimeException() {}