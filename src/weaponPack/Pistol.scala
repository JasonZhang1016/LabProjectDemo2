package weaponPack
import playerPack._

class Pistol(player: Player) extends Weapon
{
  this.name = "pistol"
  this.damage = 10
  this.bulletSpeed = 20
  this.location = player.playerLocation

}
