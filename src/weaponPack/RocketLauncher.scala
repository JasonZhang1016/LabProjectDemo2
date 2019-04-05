package weaponPack
import playerPack._

class RocketLauncher(player: Player) extends Weapon
{
  this.name = "Rocket Launcher"
  this.damage = 100
  this.bulletSpeed = 20
  this.location = player.playerLocation
}
