package ch.hsr.qfs;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.espresso.contrib.NavigationViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isNotChecked;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import android.test.suitebuilder.annotation.LargeTest;

import com.hsr.qfs.R;

import ch.hsr.qfs.view.MainActivity;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);

    @Test
    public void fragmentLoginIsLoaded() {
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withId(R.id.left_drawer)).perform(NavigationViewActions.navigateTo(R.id.nav_login));

        onView(withId(R.id.etUsername)).check(matches(isDisplayed()));
        onView(withId(R.id.etPassword)).check(matches(isDisplayed()));
        onView(withId(R.id.btnLogin)).check(matches(isDisplayed()));
    }

    @Test
    public void fragmentRegisterIsLoaded() throws InterruptedException {
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withId(R.id.left_drawer)).perform(NavigationViewActions.navigateTo(R.id.nav_register));

        onView(withId(R.id.etFirstname)).check(matches(isDisplayed()));
        onView(withId(R.id.etLastname)).check(matches(isDisplayed()));
        onView(withId(R.id.etEmail)).check(matches(isDisplayed()));
        onView(withId(R.id.etUsername)).check(matches(isDisplayed()));
        onView(withId(R.id.etPassword)).check(matches(isDisplayed()));
        onView(withId(R.id.etPasswordRepeat)).check(matches(isDisplayed()));
        onView(withId(R.id.btnRegister)).check(matches(isDisplayed()));
    }

    @Test
    public void loginIsSuccessfully() {
        onView(withId(R.id.etUsername)).perform(click());
        onView(withId(R.id.etUsername)).perform(typeText("user0"));
        onView(withId(R.id.etPassword)).perform(typeText("pass0"));
        onView(withId(R.id.btnLogin)).perform(click());

        onView(withText("Laufend")).check(matches(isDisplayed()));
        onView(withText("Offen")).check(matches(isDisplayed()));
        onView(withText("Beendet")).check(matches(isDisplayed()));
    }

    @Test
    public void loginIsFails() {
        onView(withId(R.id.etUsername)).perform(click());
        onView(withId(R.id.etUsername)).perform(typeText("user0"));
        onView(withId(R.id.etPassword)).perform(typeText("pass1"));
        onView(withId(R.id.btnLogin)).perform(click());

        onView(withText("Laufend")).check(doesNotExist());
        onView(withText("Offen")).check(doesNotExist());
        onView(withText("Beendet")).check(doesNotExist());
    }

}
