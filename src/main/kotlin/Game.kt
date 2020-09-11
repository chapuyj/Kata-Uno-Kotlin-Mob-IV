class Game(val players: List<Player>) {

    companion object {
        const val START_DECK_SIZE = 108
        const val START_HAND_SIZE = 7
    }

    val discard = mutableListOf<Card>()
    val deck = mutableListOf<Card>()

    init {
        repeat(START_DECK_SIZE){
            deck.add(Card())
        }
    }

    fun start() {
        dealCards()
        putFirstCardInDiscard()
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
}