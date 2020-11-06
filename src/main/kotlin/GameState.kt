data class GameState(val players: List<Player>, val discard : Card, val deck : Deck, val currentPlayer : Player )


//sealed class GameState(open val players: List<Player>, open val deck : Deck)
//
//data class InitialState(override val players: List<Player>, override val deck : Deck) : GameState(players, deck)
//
//data class State(override val players: List<Player>, override val deck : Deck, val discard : Card, val currentPlayer : Player ) : GameState(players, deck)
