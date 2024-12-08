package com.airlibs.app

import com.airlibs.app.features.dashboard.GetGreetingImpl
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import java.util.Calendar

@RunWith(MockitoJUnitRunner::class)
class GreetingTest {
    @Test
    fun testGoodMorningGreeting() {
        val mockCalendar = mock(Calendar::class.java)
        `when`(mockCalendar.get(Calendar.HOUR_OF_DAY)).thenReturn(8)
        val greetingManager = GetGreetingImpl(mockCalendar)
        val greeting = greetingManager.getGreeting()
        assertEquals("Good Morning", greeting)
    }

    @Test
    fun testGoodAfternoonGreeting() {
        val mockCalendar = mock(Calendar::class.java)
        `when`(mockCalendar.get(Calendar.HOUR_OF_DAY)).thenReturn(14)
        val greetingManager = GetGreetingImpl(mockCalendar)
        val greeting = greetingManager.getGreeting()
        assertEquals("Good Afternoon", greeting)
    }

    @Test
    fun testGoodEveningGreeting() {
        val mockCalendar = mock(Calendar::class.java)
        `when`(mockCalendar.get(Calendar.HOUR_OF_DAY)).thenReturn(20)
        val greetingManager = GetGreetingImpl(mockCalendar)
        val greeting = greetingManager.getGreeting()
        assertEquals("Good Evening", greeting)
    }
}