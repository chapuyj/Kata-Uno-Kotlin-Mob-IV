class Game(val playerNames: List<String>) {

    companion object {
        const val MIN_NUMBER_OF_PLAYER = 2
        const val MAX_NUMBER_OF_PLAYER = 10

        const val START_HAND_SIZE = 7
    }

    lateinit var gameState : GameState

    init {
        if(playerNames.size !in MIN_NUMBER_OF_PLAYER..MAX_NUMBER_OF_PLAYER) throw InvalidNumberOfPlayersException()

    }

    fun start() {
        val players = playerNames.map { Player() }
        val currentPlayer = players.first()
        var deck = Deck.createStarterDeck()
        deck = dealCards(players, deck)
        val (discard, newDeck) = deck.draw(1)
        gameState = GameState(players, discard.first(), newDeck, currentPlayer)
    }

    private fun dealCards(players: List<Player>, deck: Deck) : Deck {
        var deck = deck
        players.forEach { player ->
            deck = dealCards(player, deck)
        }
        return deck
    }

    private fun dealCards(player: Player, deck: Deck) : Deck {
        val (drawn, newDeck) = deck.draw(START_HAND_SIZE)
        player.hand += drawn //TODO make the player immutable
        return newDeck
    }

    fun cardPlayed() {
        val discardedCard = gameState.currentPlayer.hand.removeAt(0)
        gameState = gameState.copy(discard = discardedCard)
    }
}

class InvalidNumberOfPlayersException : RuntimeException() {}