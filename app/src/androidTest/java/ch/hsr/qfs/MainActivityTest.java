package ch.hsr.qfs;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.allOf;

import android.os.IBinder;
import android.provider.ContactsContract;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.Root;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.espresso.contrib.NavigationViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isNotChecked;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;

import static android.support.test.espresso.matcher.RootMatchers.withDecorView;

import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

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
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withId(R.id.left_drawer)).perform(NavigationViewActions.navigateTo(R.id.nav_login));

        onView(withId(R.id.etUsername)).perform(click());
        onView(withId(R.id.etUsername)).perform(typeText("user0"));
        onView(withId(R.id.etPassword)).perform(typeText("pass0"));
        onView(withId(R.id.btnLogin)).perform(click());

        onView(withText("Laufend")).check(matches(isDisplayed()));
        onView(withText("Offen")).check(matches(isDisplayed()));
        onView(withText("Beendet")).check(matches(isDisplayed()));
    }

    @Test
    public void loginIsFailed() {
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withId(R.id.left_drawer)).perform(NavigationViewActions.navigateTo(R.id.nav_login));

        onView(withId(R.id.etUsername)).perform(click());
        onView(withId(R.id.etUsername)).perform(typeText("user0"));
        onView(withId(R.id.etPassword)).perform(typeText("pass1"));
        onView(withId(R.id.btnLogin)).perform(click());

        onView(withText("Laufend")).check(doesNotExist());
        onView(withText("Offen")).check(doesNotExist());
        onView(withText("Beendet")).check(doesNotExist());
    }

    @Test
    public void registerIsSuccessfully() {
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withId(R.id.left_drawer)).perform(NavigationViewActions.navigateTo(R.id.nav_register));

        onView(withId(R.id.etFirstname)).check(matches(isDisplayed()));
        onView(withId(R.id.etFirstname)).perform(typeText("Hans"));
        onView(withId(R.id.etLastname)).perform(typeText("Muster"));
        onView(withId(R.id.etEmail)).perform(typeText("hansmuster@hsr.ch"));
        onView(withId(R.id.etUsername)).perform(typeText("quizmaster"));
        onView(withId(R.id.etPassword)).perform(typeText("123456"));
        onView(withId(R.id.etPasswordRepeat)).perform(typeText("123456"));

        onView(withId(R.id.btnRegister)).perform(ViewActions.closeSoftKeyboard()).perform(click());

        onView(withId(R.id.etUsername)).check(matches(isDisplayed()));
        onView(withId(R.id.etPassword)).check(matches(isDisplayed()));
        onView(withId(R.id.btnLogin)).check(matches(isDisplayed()));
    }

    @Test
    public void registerIsFailedNoUsername() {
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withId(R.id.left_drawer)).perform(NavigationViewActions.navigateTo(R.id.nav_register));

        onView(withId(R.id.etFirstname)).check(matches(isDisplayed()));
        onView(withId(R.id.etFirstname)).perform(typeText("Hans"));
        onView(withId(R.id.etLastname)).perform(typeText(""));
        onView(withId(R.id.etEmail)).perform(typeText("hansmuster@hsr.ch"));
        onView(withId(R.id.etUsername)).perform(typeText(""));
        onView(withId(R.id.etPassword)).perform(typeText("abcdefg"));
        onView(withId(R.id.etPasswordRepeat)).perform(typeText("abcdefg"));

        onView(withId(R.id.btnRegister)).perform(ViewActions.closeSoftKeyboard()).perform(click());
        onView(withId(R.id.btnRegister)).perform(click());

        onView(withText("Registrierung ist fehlgeschlagen")).inRoot(new ToastMatcher()).check(matches(isDisplayed()));
    }

    @Test
    public void registerIsFailedNoHSRMail() {
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withId(R.id.left_drawer)).perform(NavigationViewActions.navigateTo(R.id.nav_register));

        onView(withId(R.id.etFirstname)).check(matches(isDisplayed()));
        onView(withId(R.id.etFirstname)).perform(typeText("Hans"));
        onView(withId(R.id.etLastname)).perform(typeText("Muster"));
        onView(withId(R.id.etEmail)).perform(typeText("hansmuster@gmail.ch"));
        onView(withId(R.id.etUsername)).perform(typeText("quizmaster"));
        onView(withId(R.id.etPassword)).perform(typeText("123456"));
        onView(withId(R.id.etPasswordRepeat)).perform(typeText("123456"));
        onView(withId(R.id.btnRegister)).perform(ViewActions.closeSoftKeyboard()).perform(click());
        /*
        onView(withId(R.id.etEmail)).check(matches(withHint("E-Mail ungültig")));
        onView(withText("E-Mail ungültig")).check(matches(isDisplayed()));
        onView(withId(R.id.etEmail)).check(matches(withHint("E-Mail ungültig")));
        onView(withId(R.id.etUsername)).perform(typeText("quizmaster"));
        onView(withId(R.id.etPassword)).perform(typeText("123456"));
        onView(withId(R.id.etPasswordRepeat)).perform(typeText("123456"));
        onView(withText("E-Mail ungültig")).check(matches(withHint("E-Mail ungültig")));
        */
        onView(withId(R.id.btnLogin)).check(doesNotExist());
    }

    @Test
    public void quizIsOpen() {
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withId(R.id.left_drawer)).perform(NavigationViewActions.navigateTo(R.id.nav_login));

        onView(withId(R.id.etUsername)).perform(click());
        onView(withId(R.id.etUsername)).perform(typeText("user0"));
        onView(withId(R.id.etPassword)).perform(typeText("pass0"));
        onView(withId(R.id.btnLogin)).perform(click());

        onView(withText("Laufend")).check(matches(isDisplayed()));
        onView(withText("Offen")).check(matches(isDisplayed()));
        onView(withText("Beendet")).check(matches(isDisplayed()));

        onView(withId(R.id.fab)).perform(click());
        onView(withId(R.id.recyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(withId(R.id.viewpager)).perform(swipeLeft());
        onView(withText("user1")).check(matches(isDisplayed()));
    }

    @Test
    public void quizIsOpenAtOpponent() {
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withId(R.id.left_drawer)).perform(NavigationViewActions.navigateTo(R.id.nav_login));

        onView(withId(R.id.etUsername)).perform(click());
        onView(withId(R.id.etUsername)).perform(typeText("user2"));
        onView(withId(R.id.etPassword)).perform(typeText("pass2"));
        onView(withId(R.id.btnLogin)).perform(click());

        onView(withText("Laufend")).check(matches(isDisplayed()));
        onView(withText("Offen")).check(matches(isDisplayed()));
        onView(withText("Beendet")).check(matches(isDisplayed()));

        onView(withId(R.id.fab)).perform(click());
        onView(withId(R.id.recyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(withId(R.id.viewpager)).perform(swipeLeft());
        onView(withText("user0")).check(matches(isDisplayed()));

        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withId(R.id.left_drawer)).perform(NavigationViewActions.navigateTo(R.id.nav_logout));

        onView(withId(R.id.etUsername)).perform(click());
        onView(withId(R.id.etUsername)).perform(typeText("user0"));
        onView(withId(R.id.etPassword)).perform(typeText("pass0"));
        onView(withId(R.id.btnLogin)).perform(click());

        onView(withId(R.id.viewpager)).perform(swipeLeft());
        onView(withText("user2")).check(matches(isDisplayed()));
    }


    @Test
    public void quizIsRunning() {
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withId(R.id.left_drawer)).perform(NavigationViewActions.navigateTo(R.id.nav_login));

        onView(withId(R.id.etUsername)).perform(click());
        onView(withId(R.id.etUsername)).perform(typeText("user3"));
        onView(withId(R.id.etPassword)).perform(typeText("pass3"));
        onView(withId(R.id.btnLogin)).perform(click());

        onView(withText("Laufend")).check(matches(isDisplayed()));
        onView(withText("Offen")).check(matches(isDisplayed()));
        onView(withText("Beendet")).check(matches(isDisplayed()));

        onView(withId(R.id.fab)).perform(click());
        onView(withId(R.id.recyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(withId(R.id.viewpager)).perform(swipeLeft());
        onView(withText("user0")).check(matches(isDisplayed()));

        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withId(R.id.left_drawer)).perform(NavigationViewActions.navigateTo(R.id.nav_logout));

        onView(withId(R.id.etUsername)).perform(click());
        onView(withId(R.id.etUsername)).perform(typeText("user0"));
        onView(withId(R.id.etPassword)).perform(typeText("pass0"));
        onView(withId(R.id.btnLogin)).perform(click());

        onView(withId(R.id.viewpager)).perform(swipeLeft());
        onView(withText("user3")).check(matches(isDisplayed()));
/*
        onView(withId(R.id.ivAccept)).perform(click());
        onView(withId(R.id.viewpager)).perform(swipeRight());
        onView(withId(R.id.viewpager)).perform(swipeDown());

        onView(withText("Spielbereit")).check(matches(isDisplayed()));

        onView(withId(R.id.recyclerView)).perform(RecyclerViewActions.actionOnItem(hasDescendant(withText("user3")), click()));
*/
    }

/*
    @Test
    public void quizIsRunning() {

        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withId(R.id.left_drawer)).perform(NavigationViewActions.navigateTo(R.id.nav_logout));

        onView(withId(R.id.etUsername)).perform(click());
        onView(withId(R.id.etUsername)).perform(typeText("user1"));
        onView(withId(R.id.etPassword)).perform(typeText("pass1"));
        onView(withId(R.id.btnLogin)).perform(click());

        onView(withId(R.id.viewpager)).perform(swipeLeft());
        onView(withText("user1")).check(matches(isDisplayed()));

        onView(withId(R.id.ivAccept)).perform(click());
        onView(withId(R.id.viewpager)).perform(swipeRight());
        onView(withId(R.id.viewpager)).perform(swipeDown());

        onView(withText("Spielbereit")).check(matches(isDisplayed()));

    }
*/
    public class ToastMatcher extends TypeSafeMatcher<Root> {

        @Override
        public void describeTo(Description description) {
            description.appendText("is toast");
        }

        @Override
        public boolean matchesSafely(Root root) {
            int type = root.getWindowLayoutParams().get().type;
            if ((type == WindowManager.LayoutParams.TYPE_TOAST)) {
                IBinder windowToken = root.getDecorView().getWindowToken();
                IBinder appToken = root.getDecorView().getApplicationWindowToken();
                if (windowToken == appToken) {
                    return true;
                }
            }
            return false;
        }
    }
/*
    public static Matcher<View> withHint(final String expectedHint) {
        return new TypeSafeMatcher<View>() {

            @Override
            public boolean matchesSafely(View view) {
                if (!(view instanceof EditText)) {
                    return false;
                }

                String hint = ((EditText) view).getHint().toString();

                return expectedHint.equals(hint);
            }

            @Override
            public void describeTo(Description description) {
            }
        };
    }
*/
}
