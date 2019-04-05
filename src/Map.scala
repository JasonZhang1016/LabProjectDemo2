import scalafx.Includes._
import javafx.scene.input.{KeyCode, KeyEvent}
import scalafx.animation.AnimationTimer
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.input.MouseEvent
import scalafx.scene.paint.Color
import scalafx.scene.shape._
import playerPack._
import game._
import weaponPack._

object Map extends JFXApp
{
  val game: Game = new Game()
  val player1: Rectangle = playerRectangle(game.playerOneLocation(0), game.playerOneLocation(1))
  val player2: Rectangle = playerRectangle(game.playerTwoLocation(0), game.playerTwoLocation(1))

  def playerRectangle(xLocation: Double, yLocation: Double): Rectangle =
  {
    new Rectangle
    {
      x = xLocation
      y = yLocation
      width = 40
      height = 40
      fill = Color.rgb(scala.util.Random.nextInt(255), scala.util.Random.nextInt(255), scala.util.Random.nextInt(255))
    }
  }
  def newBullet(xLocation: Double, yLocation: Double): Rectangle =
  {
    new Rectangle
    {
      x = xLocation - 10
      y = yLocation - 10
      width = 10
      height = 10
      fill = Color.DarkOliveGreen
    }
  }
  var characterSpeed: Double = 1.0

  def Keys(x: KeyCode): Unit =
  {
    x.getName match
    {
      case "W" => player1.y.value -= characterSpeed * 10
      case "Up" => player1.y.value -= characterSpeed * 10
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
      case "A" => player1.x.value -= characterSpeed * 10
      case "Left" => player1.x.value -= characterSpeed * 10
      //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
      case "S" => player1.y.value += characterSpeed * 10
      case "Down" => player1.y.value += characterSpeed * 10
      //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
      case "D" => player1.x.value += characterSpeed * 10
      case "Right" => player1.x.value += characterSpeed * 10
      //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
      case _ =>
    }
  }
  stage = new JFXApp.PrimaryStage
  {
    title = "2D MMO Game"
    scene = new Scene(1500,1000)
    {
      fill = Color.SeaGreen
      content = List(player1, player2)

      onMouseClicked = (event: MouseEvent) =>
      {
        val bullet: Rectangle = newBullet(player1.x.value, player1.y.value)
        content.add(bullet)
        val timer = AnimationTimer(t =>
        {
          bulletFired(bullet, 5)
          if(bulletCollided(bullet))
          {
            //makes game changes
            val enemy: Player = game.playerMap("p2")
            val damage: Double = .14285714286
            enemy.playerHealth -= damage
            println(enemy.playerHealth)

            //update front end
            if(checkIfPlayerDead(enemy))
            {
              game.determineWinner()
              content.remove(player2)
            }
          }
        })
        timer.start()
      }
      def bulletFired(bullet: Rectangle, bulletSpeed: Int): Unit =
      {
        bullet.x.value -= bulletSpeed
        bullet.y.value -= bulletSpeed
      }
      def bulletCollided(bullet: Rectangle): Boolean =
      {
        if(((bullet.x.value < player2.x.value + 40) && (bullet.x.value > player2.x.value)) && ((bullet.y.value < player2.y.value + 40) && (bullet.y.value > player2.y.value)))
        {
          content.remove(bullet)
          true
        }
        else
        {
          false
        }
      }
      def checkIfPlayerDead(player: Player): Boolean =
      {
        if(player.playerHealth <= 0)
        {
          true
        }
        else
        {
          false
        }
      }
      addEventHandler(KeyEvent.KEY_PRESSED, (event: KeyEvent) => Keys(event.getCode))
    }
  }
}