package tests

import org.scalatest._
import playerPack._
import weaponPack._
import game._

class TestHitDetection extends FunSuite
{
  test("testing playerGetHit() function")
  {
    val p1: Player = new Player("p1", 30, 10, List(0.0, 0.0))
    val pistolOne: Weapon = new Pistol(p1)
    val b1: Bullet = new Bullet(pistolOne)
    assert(p1.playerGetHit(b1) == true, "1")

    b1.currentLocation = List(2.4, 1.0)
    assert(p1.playerGetHit(b1) == false, "2")

    val p2: Player = new Player("p2", 30, 10, List(4.999, 3.999))
    val pistolTwo: Weapon = new RocketLauncher(p2)
    val b2: Bullet = new Bullet(pistolTwo)
    assert(p2.playerGetHit(b2) == true, "3")

    b2.currentLocation = List(4.99999, 3.99999)
    assert(p1.playerGetHit(b1) == false, "4")

    b2.currentLocation = List(4.998, 3.998)
    assert(p1.playerGetHit(b1) == false, "4")
  }
}
