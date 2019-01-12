package study.bums.soonho.study2048

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_game_main.*
import kotlin.random.Random

class GameMain : AppCompatActivity() {

    private fun createRandom2() {
        var buttons = getButtonList()
        while (true) {
            var randIndex = (0..(buttons.size-1)).random()
            var button = buttons[randIndex]
            var buttonString = button.text
            if(buttonString.equals("")){
                button.text = "2"
                break
            } else {
                continue
            }
        }
    }

    private fun resetButtonText() {
        var buttonList = getButtonList()
        buttonList.forEach {
            it.text = ""
        }
    }

    private fun getButtonList(): ArrayList<Button> {
        var buttonList:ArrayList<Button> = ArrayList()
        buttonList.add(gb1)
        buttonList.add(gb2)
        buttonList.add(gb3)
        buttonList.add(gb4)
        buttonList.add(gb5)
        buttonList.add(gb6)
        buttonList.add(gb7)
        buttonList.add(gb8)
        buttonList.add(gb9)
        buttonList.add(gb10)
        buttonList.add(gb11)
        buttonList.add(gb12)
        buttonList.add(gb13)
        buttonList.add(gb14)
        buttonList.add(gb15)
        buttonList.add(gb16)
        return buttonList
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_main)
        resetButtonText()
        createRandom2()
    }

    enum class Direction {
        UP, DOWN, RIGHT, LEFT
    }

    fun upButtonClicked(view: View) {
        acceptEvent(Direction.UP)
    }

    fun downButtonClicked(view: View) {
        acceptEvent(Direction.DOWN)
    }

    fun leftButtonClicked(view: View) {
        acceptEvent(Direction.LEFT)
    }

    fun rightButtonClicked(view: View) {
        acceptEvent(Direction.RIGHT)
    }

    private fun acceptEvent(direction: Direction) {

        var buttonList = getButtonList()

        var isVertical:Boolean
        var isReversed:Boolean
        when(direction){
            Direction.UP, Direction.DOWN -> isVertical = true
            Direction.LEFT, Direction.RIGHT -> isVertical = false
        }
        when(direction){
            Direction.UP, Direction.LEFT -> isReversed = true
            Direction.DOWN, Direction.RIGHT -> isReversed = false
        }

        var start:List<Int>
        var calc:Int
        if(isVertical){
            if(isReversed){
                start = listOf(1,2,3,4)
                calc = 4
            } else {
                start = listOf(13,14,15,16)
                calc = -4
            }
        } else {
            if(isReversed){
                start = listOf(1,5,9,13)
                calc = 1
            } else {
                start = listOf(4,8,12,16)
                calc = -1
            }
        }

        // line repeat
        for(l in 0..3){
            var added:ArrayList<Int> = ArrayList()
            // cell repeat
            for(c in 1..3){
                var index:Int = (start[l] + (calc*c) - 1)
                var button = buttonList[index]
                var numString:String = button.text.toString()
                if(numString.equals("")){
                    // has nothing
                    continue
                } else {
                    var num:Int = numString.toInt()
                    // moving repeat
                    for(t in (c-1) downTo 0){
                        var moveIndex:Int = (start[l] + (calc*t) - 1)
                        var moveButton = buttonList[moveIndex]
                        var moveString = moveButton.text.toString()
                        if(moveString.equals("") && t != 0){
                            // no number at target and target is not the last widget
                            continue
                        } else if(moveString.equals("") && t == 0){
                            // no number at target and target is the last widget
                            button.text = ""
                            moveButton.text = numString
                        } else {
                            // has number at target

                            if(numString.equals(moveString)){
                                // same number
                                if(!added.contains(t)) {
                                    // target is added widget
                                    button.text = ""
                                    var moveInt: Int = moveString.toInt()
                                    var plusString = (moveInt + num).toString()
                                    moveButton.text = plusString
                                    added.add(t)
                                    break
                                }
                            }

                            // target is added widget
                            button.text = ""
                            buttonList[moveIndex+calc].text = numString
                            break
                        }
                    }
                }
            }
        }
        createRandom2()
    }
}
