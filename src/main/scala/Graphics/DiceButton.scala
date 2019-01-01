package dice.graphics

import scalafx.scene.control.Button
import scalafx.beans.property.IntegerProperty
import scalafx.beans.binding._
import scalafx.scene.input.MouseEvent
import scalafx.Includes._
import scala.util.Random

class DiceButton(nbFace:Int,nbFaceProp:IntegerProperty) extends Button{
  text = "Dé " + nbFace

  onMouseClicked = (event : MouseEvent) => {nbFaceProp.value = nbFace}

}

class launchButton(dice : DiceDrawing) extends Button{
  text = "Lancer"

  onMouseClicked = (event : MouseEvent) => {dice.roll}

  disable <== when (dice.nbFace === -1) choose true otherwise false

}

trait Dice {

  val rand = new Random(System.currentTimeMillis())

  def throwing(nbFace:Int) = {rand.nextInt(nbFace) + 1}


}
