package ch.hsr.qfs;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isNotChecked;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import android.test.suitebuilder.annotation.LargeTest;

import com.hsr.pewpew.qfs.R;
import ch.hsr.qfs.view.MainActivity;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);

    @Test
    public void fragmentLoginIsLoaded() {
        onView(withId(R.id.tfUsername)).check(matches(isDisplayed()));
    }

    @Test
    public void LoginIsSuccessfully() {
        onView(withId(R.id.tfUsername)).perform(click());
        onView(withId(R.id.tfUsername)).perform(typeText("user0"));
        onView(withId(R.id.tfPassword)).perform(typeText("pass0"));
        onView(withId(R.id.btnLogin)).perform(click());
        onView(withText("QuizHomeFragment")).check(matches(isDisplayed()));
    }

    @Test
    public void LoginIsFails() {
        onView(withId(R.id.tfUsername)).perform(click());
        onView(withId(R.id.tfUsername)).perform(typeText("user0"));
        onView(withId(R.id.tfPassword)).perform(typeText("pass1"));
        onView(withId(R.id.btnLogin)).perform(click());
        onView(withText("QuizHomeFragment")).check(doesNotExist());
    }
}
