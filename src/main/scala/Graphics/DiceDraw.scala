package dice.graphics

import scalafx.scene.shape._
import scala.math._
import scalafx.scene.paint._
import scalafx.scene.layout._
import scalafx.scene.text.Text
import scalafx.beans.property._
import scala.util.Random

class DiceDrawing(val nbFace:IntegerProperty,val actualValue:IntegerProperty) extends StackPane{

  val rand = new Random(System.currentTimeMillis())

  minHeight.value = 200
  minWidth.value = 200

  val txt = new Text("Choisissez un dé")

  val dice = new DiceDraw(-1)

  children = Seq(dice,txt)

  if (nbFace.value == -1)
    children = Seq(new Text("Choisissez un dé"))

  nbFace.onChange{(_,_,newValue) =>
    dice.nbFace = newValue.intValue
    if (newValue == -1){
      txt.text = "Choisissez un dé"
    } else {
      actualValue.value = -1
    }
    dice.redraw
  }

  actualValue.onChange{(_,_,newValue) =>
    txt.text =
      if (actualValue() == -1)
        "toto"
      else newValue.toString
  }

  def roll = {actualValue.value = rand.nextInt(nbFace.value) + 1}

}

class DiceDraw(var nbFace:Int) extends Path{

  val size = 100

  def mkAngle(nbRemaining : Int) = {
    360/(2 * nbFace) + (nbFace - nbRemaining + 1) * 360/nbFace
  }

  def getX(nbRemain : Int) = {
    size * cos(mkAngle(nbRemain).toRadians)
  }

  def getY(nbRemain : Int) = {
    size * sin(mkAngle(nbRemain).toRadians)
  }

  def draw(nbRemain : Int) : List[PathElement] = {
    nbRemain match {
      case 0 => List[PathElement](LineTo(getX(nbFace + 1),getY(nbFace + 1)))
      case n => LineTo(getX(nbRemain),getY(nbRemain)) :: draw(nbRemain - 1)
    }
  }

  def redraw = {
    println(nbFace)
    elements_=(
      if (nbFace == -1)
        List[PathElement]()
      else
        MoveTo(getX(nbFace + 1),getY(nbFace + 1)) :: draw(nbFace))
  }

  fill = Color.rgb(214,46,10)

  redraw

}








