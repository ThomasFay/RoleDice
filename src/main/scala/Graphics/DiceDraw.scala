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

  val txt = new Text("Choisissez un dé") {
    style = "-fx-font-size : 30"
  }

  children = Seq(txt)

  if (nbFace.value == -1) {
    children = Seq(new Text("Choisissez un dé"))
    println("tutu")
  }

  actualValue.onChange{(_,_,newValue) =>
    txt.text =
      if (actualValue.value == -1)
        "?"
      else newValue.toString
  }


  nbFace.onChange{(_,_,newValue) =>
    if (newValue == -1){
      txt.text = "Choisissez un dé"
    } else {
      actualValue.value = -1
      txt.text = "?"
      children = Seq(new DiceDraw(newValue.intValue),txt)
    }
  }


  def roll = {actualValue.value = rand.nextInt(nbFace.value) + 1}

}

class DiceDraw(nbFace:Int) extends Path{

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

  elements_=(
    if (nbFace == -1)
      List[PathElement]()
    else
      MoveTo(getX(nbFace + 1),getY(nbFace + 1)) :: draw(nbFace))

  fill = Color.rgb(214,46,10)


}








