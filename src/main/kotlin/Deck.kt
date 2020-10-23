data class Deck(val cards: List<Card>) {
    companion object {
        
        const val START_DECK_SIZE = 108

        fun createStarterDeck(): Deck {
            val cards = List(START_DECK_SIZE) { Card() }
            return Deck(cards)
        }

    }

    fun draw(number: Int) : Pair<List<Card>,Deck> {
        val drawn = cards.take(number)
        val remaining = cards.takeLast(cards.size - number)
        val newDeck = Deck(remaining)
        return Pair(drawn, newDeck)
    }

    val size = cards.size
}