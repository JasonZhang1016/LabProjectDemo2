package game

import playerPack.Player
import weaponPack._

class Game
{
  //val playerOneLocation: List[Double] = List(Math.floor(Math.random() * 1400), Math.floor(Math.random() * 900))
  val playerOneLocation: List[Double] = List(800, 550)
  val playerTwoLocation: List[Double] = List(700, 450)

  var playerMap: Map[String, Player] = Map(
    "p1" -> new Player("p1", 10, 3, playerOneLocation),
    "p2" -> new Player("p2", 10, 3, playerTwoLocation)
  )
  var playerCount: Int = playerMap.size

  def addPlayerToGame(player: Player): Unit =
  {
    playerMap += player.playerName -> player
    playerCount = playerMap.size
  }
  def determineWinner(): Player =
  {
    playerCount = playerMap.size
    var winner: Player = new Player("no winner", 0, 0, List(0,0))

    //for loop checks to see if any player is dead and if they are, take them off playerCount
    for((name, player) <- playerMap)
    {
      if(player.isPlayerDead())
      {
        playerCount -= 1
      }
    }

    //for loop checks the playerMap to see if there is only 1 player alive, and if there is, it checks the health of all the players and whoever is   not dead is declared the winner
    if(playerCount == 1)
    {
      for((name, player) <- playerMap)
      {
        if(player.playerHealth != 0)
        {
          println(name + " is the last survivor!")
          winner = player
        }
      }
    }
    else
    {
      //print("There are no winners yet! \n")
    }
    //print("Players left on the map: " + playerCount + "\n")
    //print("Winner is " + winner.playerName)
    winner
  }
}
