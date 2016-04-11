package ch.hsr.qfs;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import static android.support.test.espresso.Espresso.*;

import static android.support.test.espresso.*;

import android.test.suitebuilder.annotation.LargeTest;

import com.hsr.pewpew.qfs.R;
import ch.hsr.qfs.view.MainActivity;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);

    @Test
    public void fragmentIsLoaded() {
        //onView(withId(R.id.tfUsername));
    }
}
