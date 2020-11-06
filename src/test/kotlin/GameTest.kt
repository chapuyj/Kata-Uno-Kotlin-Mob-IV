import Deck.Companion.START_DECK_SIZE
import Game.Companion.START_HAND_SIZE
import org.junit.Assert.*
import org.junit.Test

class GameTest {

    @Test
    fun gameCreated() {
        val players = listOf("Lamine", "François")
        val game = Game(players)
//        assertEquals(2, game.gameState.players.size)
//        assertEquals(START_DECK_SIZE, game.gameState.deck.size)
    }

    @Test(expected = InvalidNumberOfPlayersException::class)
    fun gameIsNotCreatedWithNotEnoughPlayer() {
        val players = listOf("Jordan")
        val game = Game(players)
    }

    @Test(expected = InvalidNumberOfPlayersException::class)
    fun gameIsNotCreatedWithTooManyPlayers() {
        val players = List(11){"Julian"}
        val game = Game(players)
    }

    @Test
    fun gameStarted() {
        val players = listOf("Lamine", "Habib")
        val game = Game(players)

        game.start()


        val discardedCard = 1
        val expectedDeckSize = START_DECK_SIZE - (START_HAND_SIZE * game.gameState.players.size) - discardedCard

        assertEquals(expectedDeckSize, game.gameState.deck.size)
        game.gameState.players.forEach{player ->
            assertEquals(START_HAND_SIZE, player.hand.size)
        }
        assertNotNull(game.gameState.discard)
        // assert première carte dans la défausse
        assertTrue(game.gameState.players.contains(game.gameState.currentPlayer))
    }

    @Test
    fun playerTurnPlayCard() {
        val players = listOf("Lamine", "François")
        val game = Game(players)
        game.start()

        val card = Card()
        game.cardPlayed()
        //game.cardPlayed(card)

        // assert carte dans la défausse
        assertEquals(game.gameState.currentPlayer.hand.size, START_HAND_SIZE - 1)
    }
}