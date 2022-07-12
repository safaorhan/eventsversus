package com.safaorhan.events.versus.home

import androidx.navigation.ActionOnlyNavDirections
import app.cash.turbine.test
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_LONG
import com.safaorhan.events.navigation.NavigationEvent
import com.safaorhan.events.snackbar.SnackbarEvent
import com.safaorhan.events.test.TestEvents
import com.safaorhan.events.toast.ToastEvent
import com.safaorhan.events.versus.R
import com.safaorhan.events.versus.events.dialog.AlertDialogEvent
import com.safaorhan.events.versus.events.sound.SoundEvent
import com.safaorhan.text.asStringResource
import com.safaorhan.text.asText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    private val dispatcher = UnconfinedTestDispatcher()

    @Before
    fun before() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun after() {
        Dispatchers.resetMain()
    }

    // ICS

    @Test
    fun `when ics is clicked, should set the answer`() = runTest(dispatcher) {
        val events = TestEvents()
        val viewModel = HomeViewModel(events)

        viewModel.onIceCreamSandwichClicked()

        viewModel.uiState.test {
            assertThat(awaitItem().answer).isEqualTo("Ice Cream Sandwich")
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `when ics is clicked, should play error beep`() = runTest(dispatcher) {
        val events = TestEvents()
        val viewModel = HomeViewModel(events)

        viewModel.onIceCreamSandwichClicked()

        assertThat(events.events).contains(SoundEvent)
    }

    @Test
    fun `when ics is clicked, should show toast`() = runTest(dispatcher) {
        val events = TestEvents()
        val viewModel = HomeViewModel(events)

        viewModel.onIceCreamSandwichClicked()

        assertThat(events.events).contains(
            ToastEvent(message = R.string.incorrect.asStringResource().asText())
        )
    }

    // Gingerbread

    @Test
    fun `when gingerbread is clicked, should set the answer`() = runTest(dispatcher) {
        val events = TestEvents()
        val viewModel = HomeViewModel(events)

        viewModel.onGingerbreadClicked()

        viewModel.uiState.test {
            assertThat(awaitItem().answer).isEqualTo("Gingerbread")
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `when gingerbread is clicked, should play error beep`() = runTest(dispatcher) {
        val events = TestEvents()
        val viewModel = HomeViewModel(events)

        viewModel.onGingerbreadClicked()

        assertThat(events.events).contains(SoundEvent)
    }

    @Test
    fun `when gingerbread is clicked, should show toast`() = runTest(dispatcher) {
        val events = TestEvents()
        val viewModel = HomeViewModel(events)

        viewModel.onGingerbreadClicked()

        assertThat(events.events).contains(
            ToastEvent(message = R.string.incorrect.asStringResource().asText())
        )
    }

    // Honeycomb

    @Test
    fun `when honeycomb is clicked, should set the answer`() = runTest(dispatcher) {
        val events = TestEvents()
        val viewModel = HomeViewModel(events)

        viewModel.onHoneycombClicked()

        viewModel.uiState.test {
            assertThat(awaitItem().answer).isEqualTo("Honeycomb")
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `when honeycomb is clicked, should play error beep`() = runTest(dispatcher) {
        val events = TestEvents()
        val viewModel = HomeViewModel(events)

        viewModel.onHoneycombClicked()

        assertThat(events.events).contains(SoundEvent)
    }

    @Test
    fun `when honeycomb is clicked, should show toast`() = runTest(dispatcher) {
        val events = TestEvents()
        val viewModel = HomeViewModel(events)

        viewModel.onHoneycombClicked()

        assertThat(events.events).contains(
            ToastEvent(message = R.string.incorrect.asStringResource().asText())
        )
    }

    // Cinnamon candy

    @Test
    fun `when cinnamon candy is clicked, should set the answer`() = runTest(dispatcher) {
        val events = TestEvents()
        val viewModel = HomeViewModel(events)

        viewModel.onCinnamonCandyClicked()

        viewModel.uiState.test {
            assertThat(awaitItem().answer).isEqualTo("Cinnamon Candy")
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `when cinnamon candy is clicked, should show snackbar`() = runTest(dispatcher) {
        val events = TestEvents()
        val viewModel = HomeViewModel(events)

        viewModel.onCinnamonCandyClicked()

        assertThat(events.events.last()).isEqualTo(
            SnackbarEvent(
                message = R.string.correct.asStringResource().asText(),
                duration = LENGTH_LONG
            )
        )
    }

    // Next Button

    @Test
    fun `given answer not set, when next is clicked, should show a dialog`() = runTest(dispatcher) {
        val events = TestEvents()
        val viewModel = HomeViewModel(events)

        viewModel.onNextButtonClicked()

        assertThat(events.events.last()).isExactlyInstanceOf(AlertDialogEvent::class.java)
    }

    @Test
    fun `given answer is set, when next is clicked, should navigate to results`() =
        runTest(dispatcher) {
            val events = TestEvents()
            val viewModel = HomeViewModel(events)

            viewModel.uiState.update {
                it.copy(answer = "some answer")
            }

            viewModel.onNextButtonClicked()

            assertThat(events.events.last()).isEqualTo(
                NavigationEvent(
                    ActionOnlyNavDirections(R.id.resultFragment)
                )
            )
        }
}