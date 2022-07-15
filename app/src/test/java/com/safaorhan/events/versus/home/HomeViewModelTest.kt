package com.safaorhan.events.versus.home

import app.cash.turbine.test
import com.safaorhan.events.versus.R
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

    @Test
    fun `when ics is clicked, should set the answer`() = runTest(dispatcher) {
        val viewModel = HomeViewModel()

        viewModel.onIceCreamSandwichClicked()

        viewModel.uiState.test {
            assertThat(awaitItem().answer).isEqualTo("Ice Cream Sandwich")
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `when ics is clicked, should play error beep`() = runTest(dispatcher) {
        val viewModel = HomeViewModel()

        viewModel.onIceCreamSandwichClicked()

        viewModel.uiState.test {
            assertThat(awaitItem().shouldPlaySound).isEqualTo(true)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `when ics is clicked, should show toast`() = runTest(dispatcher) {
        val viewModel = HomeViewModel()

        viewModel.onIceCreamSandwichClicked()

        viewModel.uiState.test {
            assertThat(awaitItem().toastMessage).isEqualTo(R.string.incorrect)
            cancelAndIgnoreRemainingEvents()
        }
    }

    // Gingerbread

    @Test
    fun `when gingerbread is clicked, should set the answer`() = runTest(dispatcher) {
        val viewModel = HomeViewModel()

        viewModel.onGingerbreadClicked()

        viewModel.uiState.test {
            assertThat(awaitItem().answer).isEqualTo("Gingerbread")
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `when gingerbread is clicked, should play error beep`() = runTest(dispatcher) {
        val viewModel = HomeViewModel()

        viewModel.onGingerbreadClicked()

        viewModel.uiState.test {
            assertThat(awaitItem().shouldPlaySound).isEqualTo(true)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `when gingerbread is clicked, should show toast`() = runTest(dispatcher) {
        val viewModel = HomeViewModel()

        viewModel.onGingerbreadClicked()

        viewModel.uiState.test {
            assertThat(awaitItem().toastMessage).isEqualTo(R.string.incorrect)
            cancelAndIgnoreRemainingEvents()
        }
    }

    // Honeycomb

    @Test
    fun `when honeycomb is clicked, should set the answer`() = runTest(dispatcher) {
        val viewModel = HomeViewModel()

        viewModel.onHoneycombClicked()

        viewModel.uiState.test {
            assertThat(awaitItem().answer).isEqualTo("Honeycomb")
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `when honeycomb is clicked, should play error beep`() = runTest(dispatcher) {
        val viewModel = HomeViewModel()

        viewModel.onHoneycombClicked()

        viewModel.uiState.test {
            assertThat(awaitItem().shouldPlaySound).isEqualTo(true)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `when honeycomb is clicked, should show toast`() = runTest(dispatcher) {
        val viewModel = HomeViewModel()

        viewModel.onHoneycombClicked()

        viewModel.uiState.test {
            assertThat(awaitItem().toastMessage).isEqualTo(R.string.incorrect)
            cancelAndIgnoreRemainingEvents()
        }
    }

    // Cinnamon candy

    @Test
    fun `when cinnamon candy is clicked, should set the answer`() = runTest(dispatcher) {
        val viewModel = HomeViewModel()

        viewModel.onCinnamonCandyClicked()

        viewModel.uiState.test {
            assertThat(awaitItem().answer).isEqualTo("Cinnamon Candy")
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `when cinnamon candy is clicked, should show snackbar`() = runTest(dispatcher) {
        val viewModel = HomeViewModel()

        viewModel.onCinnamonCandyClicked()

        viewModel.uiState.test {
            assertThat(awaitItem().snackbarMessage).isEqualTo(R.string.correct)
            cancelAndIgnoreRemainingEvents()
        }
    }

    // Next Button

    @Test
    fun `given answer not set, when next is clicked, should show a dialog`() = runTest(dispatcher) {
        val viewModel = HomeViewModel()

        viewModel.onNextButtonClicked()

        viewModel.uiState.test {
            assertThat(awaitItem().shouldShowConfirmationDialog).isEqualTo(true)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `given answer is set, when next is clicked, should navigate to results`() =
        runTest(dispatcher) {
            val viewModel = HomeViewModel()

            viewModel.uiState.update {
                it.copy(answer = "some answer")
            }

            viewModel.onNextButtonClicked()

            viewModel.uiState.test {
                assertThat(awaitItem().shouldNavigateTo).isEqualTo(R.id.resultFragment)
                cancelAndIgnoreRemainingEvents()
            }
        }
}