package tests

import org.scalatest.FunSuite
import playerPack._
import weaponPack._
import game._

class TestDetermineWinner extends FunSuite
{
  test("testing determineWinner function")
  {
    val testPlayer: Player = new Player("testPlayer", 1000, 10, List(0.0, 0.0))

    val game = new Game
    val pistol: Weapon = new Pistol(testPlayer)
    val rocket: Weapon = new RocketLauncher(testPlayer)

    //game starts with 3 players, no one has died so no winner
    assert(game.determineWinner().playerName == "no winner", "0")
    print("Player count after test 0 is "+ (game.playerCount).toString + "\n")

    //Kills 2 of the existing players on the playerMap so p3 is the only one left thus the winner
    game.playerMap("p1").takeDamage(pistol)
    game.playerMap("p2").takeDamage(pistol)
    game.playerMap("p2").takeDamage(pistol)
    assert(game.determineWinner().playerName == "p3", "1")
    print("Player count after test 1 is "+ (game.playerCount).toString + "\n")

    //add p5 to playerMap, kill p3, p5 should be the winner
    val p5: Player = new Player("p5", 40, 10, List(0.0, 0.0))
    game.addPlayerToGame(p5)
    game.playerMap("p3").takeDamage(rocket)
    assert(game.determineWinner().playerName == "p5", "2")
    print("Player count after test 2 is "+ (game.playerCount).toString + "\n")

    //p5 gets killed so there are no players left in the game, it returns a Player object I set in determineWinner() with the name "no winner"
    game.playerMap("p5").takeDamage(rocket)
    assert(game.determineWinner().playerName == "no winner", "3")
    print("Player count after test 3 is "+ (game.playerCount).toString + "\n")

    //2 players are added to the game, there are no winners
    val p6: Player = new Player("p6", 99, 10, List(0.0, 0.0))
    val p7: Player = new Player("p7", 1, 10, List(0.0, 0.0))
    game.addPlayerToGame(p6)
    game.addPlayerToGame(p7)
    assert(game.determineWinner().playerName == "no winner", "4")
    print("Player count after test 4 is "+ (game.playerCount).toString + "\n")

    //p6 gets killed so only p7 is left on the map and thus p7 is the winner
    game.playerMap("p6").takeDamage(rocket)
    assert(game.determineWinner().playerName == "p7", "5")
    print("Player count after test 5 is "+ (game.playerCount).toString + "\n")
  }
}
