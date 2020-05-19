package kohei.araya.androidsampler

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kohei.araya.androidsampler.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_layout, MainFragment.newInstance())
                .commitNow()
        }
    }
}
