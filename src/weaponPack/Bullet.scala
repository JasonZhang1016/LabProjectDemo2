package weaponPack

class Bullet(weapon: Weapon)
{
  var startingLocation: List[Double] = weapon.location
  var currentLocation: List[Double] = weapon.location
  var damage: Int = weapon.damage
  var bullet: Int = weapon.bulletSpeed
}
