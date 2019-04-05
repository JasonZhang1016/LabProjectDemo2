package tests

import org.scalatest._
import playerPack._
import weaponPack._
import game._

class TestPlayerDead extends FunSuite
{
  test("testing takeDamage() function")
  {
    val testPlayer: Player = new Player("testPlayer", 1000, 10, List(0.0, 0.0))

    val weapon: Weapon = new Pistol(testPlayer)
    val playerZeroPointZero: Player = new Player("p0.0", 10000, 10, List(0.0, 0.0))
    print("Player 0.0 has " + playerZeroPointZero.playerHealth + " health \n")
    assert(playerZeroPointZero.isPlayerDead() == false, "0.0")

    //player has 30hp, should not be dead
    val playerZero: Player = new Player("p0", 30, 10, List(0.0, 0.0))
    print("Player 0 has " + playerZero.playerHealth + " health \n")
    assert(playerZero.isPlayerDead() == false, "0")

    //player has 30hp, takes 10hp, should not be dead
    val playerOne: Player = new Player("p1", 30, 10, List(0.0, 0.0))
    playerOne.takeDamage(weapon)
    print("Player 1 has " + playerOne.playerHealth + " health \n")
    assert(playerOne.isPlayerDead() == false, "1")

    //player has 30hp and takes 10 damage 3 times, should be dead
    val playerTwo: Player = new Player("p2", 30, 10, List(0.0, 0.0))
    playerTwo.takeDamage(weapon)
    playerTwo.takeDamage(weapon)
    playerTwo.takeDamage(weapon)
    print("Player 2 has " + playerTwo.playerHealth + " health \n")
    assert(playerTwo.isPlayerDead() == true, "2")

    //player starts with 0hp, should be dead
    val playerThree: Player = new Player("p3", 0, 10, List(0.0, 0.0))
    print("Player 3 has " + playerThree.playerHealth + " health \n")
    assert(playerThree.isPlayerDead() == true, "3")

    //player starts with 0hp, takes 10 damage, should be dead
    val playerFour: Player = new Player("p4", 0, 10, List(0.0, 0.0))
    print("Player 4 has " + playerFour.playerHealth + " health \n")
    playerFour.takeDamage(weapon)
    assert(playerFour.isPlayerDead() == true, "4")

    //player starts with negative hp, should be dead
    val playerFive: Player = new Player("p5", -10, 10, List(0.0, 0.0))
    print("Player 5 has " + playerFive.playerHealth + " health \n")
    assert(playerFive.isPlayerDead() == true, "5")

    //player starts with negative hp, takes 20 damage, should be dead
    val playerSix: Player = new Player("p6", -10, 10, List(0.0, 0.0))
    playerSix.takeDamage(weapon)
    playerSix.takeDamage(weapon)
    print("Player 6 has " + playerSix.playerHealth + " health \n")
    assert(playerSix.isPlayerDead() == true, "6")
  }
}
