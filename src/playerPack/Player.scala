package playerPack

import weaponPack._

class Player(name: String, health: Double, movespeed: Int, locationCoordinate: List[Double])
{
  var playerName: String = name
  var playerHealth: Double = health
  var playerMovespeed: Int = movespeed
  var playerLocation: List[Double] = locationCoordinate
  var playerX: Double = playerLocation(0)
  var playerY: Double = playerLocation(1)
  var playerWeapon: Weapon = new noWeapon()

  def playerHasWeapon(): Boolean =
  {
    if(this.playerWeapon.name == "none")
    {
      false
    }
    else
    {
      true
    }
  }
  def playerGetHit(bullet: Bullet): Boolean =
  {
    if((playerX == bullet.currentLocation(0)) && (playerY == bullet.currentLocation(1)))
    {
      true
    }
    else
    {
      false
    }
  }
  def takeDamage(weapon: Weapon): Unit =
  {
    if(this.playerHealth - weapon.damage >= 0)
    {
      this.playerHealth = this.playerHealth - weapon.damage
    }
    else
    {
      playerIsDead()
    }
  }
  def playerIsDead(): Unit =
  {
    this.playerHealth = 0
  }
  def isPlayerDead(): Boolean =
  {
    if(this.playerHealth <= 0)
    {
      this.playerHealth = 0
      true
    }
    else
    {
      false
    }
  }
}
