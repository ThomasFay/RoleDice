package hello

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.control.Button
import scalafx.scene.effect.DropShadow
import scalafx.scene.layout._
import scalafx.scene.paint.Color._
import scalafx.scene.paint._
import scalafx.scene.text.Text
import scalafx.beans.property._
import scalafx.scene.control._
import dice.graphics._
import scala.math._
import scalafx.scene.input.MouseEvent

object ScalaFXHelloWorld extends JFXApp {

  val diceList = Seq(3,6,12,100)

  val nbFace = new IntegerProperty(this,"nbFace",-1)

  val actualValue = new IntegerProperty(this,"value",-1)

  val nbDiceChooser = new ComboBox(Array.range(1,10))

  val nbDice = nbDiceChooser.value

  nbDice.value = 1

  val dice = new DiceDrawing(nbFace,actualValue,nbDice)

  println(cos(90.toRadians))

  stage = new PrimaryStage {
    //    initStyle(StageStyle.Unified)
    title = "Dés pour jeu de rôle"
    scene = new Scene {
      fill = Color.rgb(200, 200, 200)
      content = new VBox {
        padding = Insets(0, 0, 0, 0)
        children = Seq(
          new HBox {
            padding = Insets(10,10,10,10)
            spacing = 10
            children = diceList.map(d => {new DiceButton(d,nbFace)}
            )
          },
          new HBox {
            padding = Insets(10,10,10,10)
            spacing = 10
            children = Seq(new Text("Nombre de dé"),nbDiceChooser)
          },
          dice,
          new HBox {
            padding = Insets(10,10,10,10)
            children = Seq(new launchButton(dice))
          }
        )
      }
    }

  }
}



