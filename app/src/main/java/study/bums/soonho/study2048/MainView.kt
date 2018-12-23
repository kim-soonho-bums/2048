package study.bums.soonho.study2048

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.content.Intent



class MainView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_view)
    }

    fun startButtonClicked(view: View) {
        val intent = Intent(this, GameMain::class.java)
        startActivity(intent)
    }
}
