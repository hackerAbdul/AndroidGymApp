package com.example.abdul.gymapp;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class LoginPageTest {

    @Rule
    public ActivityTestRule<LoginPage> mActivityTestRule = new ActivityTestRule<>(LoginPage.class);

    private LoginPage mLoginPage = null;

    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(Pre_homepage.class.getName(),null,false);

    @Before
    public void setUp() throws Exception {

        mLoginPage = mActivityTestRule.getActivity();

    }

    @Test
    public void testLaunchOfSecondActivityOnButtonClick(){
        assertNotNull(mLoginPage.findViewById(R.id.LogInButton));

        onView(withId(R.id.LogInButton)).perform(click());

        Activity Pre_homepage = getInstrumentation().waitForMonitorWithTimeout(monitor,5000);

        assertNotNull(Pre_homepage);

        Pre_homepage.finish();
    }


    @After
    public void tearDown() throws Exception {

        mLoginPage = null;
    }
}