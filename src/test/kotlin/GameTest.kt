import Deck.Companion.START_DECK_SIZE
import Game.Companion.START_HAND_SIZE
import org.junit.Assert.*
import org.junit.Test

class GameTest {

    @Test
    fun gameCreated() {
        val players = listOf<Player>(Player(), Player())
        val game = Game(players)
        assertEquals(2, game.players.size)
        assertEquals(START_DECK_SIZE, game.deck.size)
    }

    @Test(expected = InvalidNumberOfPlayersException::class)
    fun gameIsNotCreatedWithNotEnoughPlayer() {
        val players = listOf<Player>(Player())
        val game = Game(players)
    }

    @Test(expected = InvalidNumberOfPlayersException::class)
    fun gameIsNotCreatedWithTooManyPlayers() {
        val players = List(11){Player()}
        val game = Game(players)
    }

    @Test
    fun gameStarted() {
        val players = listOf<Player>(Player(), Player())
        val game = Game(players)

        game.start()


        val discardedCard = 1
        val expectedDeckSize = START_DECK_SIZE - (START_HAND_SIZE * game.players.size) - discardedCard

        assertEquals(expectedDeckSize, game.deck.size)
        game.players.forEach{player ->
            assertEquals(START_HAND_SIZE, player.hand.size)
        }
        assertNotNull(game.discard)
        // assert première carte dans la défausse
        assertTrue(players.contains(game.currentPlayer))
    }

    @Test
    fun playerTurnPlayCard() {
        val players = listOf<Player>(Player(), Player())
        val game = Game(players)

        game.start()
        game.cardPlayed()

        // assert carte dans la défausse
        assertEquals(game.currentPlayer.hand.size, START_HAND_SIZE - 1)
    }
}