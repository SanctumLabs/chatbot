import android.content.Context
import com.chatbot.data.DataManager
import com.chatbot.data.TestSchedulerProvider
import com.chatbot.ui.splash.SplashPresenter
import com.chatbot.ui.splash.SplashPresenterImpl
import com.chatbot.ui.splash.SplashView
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.TestScheduler
import org.junit.After
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

/**
 * @author lusinabrian on 16/11/17
 * @Notes Tests for SplashPresenterTest
 * This will check if the expected methods on the view are called as expected
 */
@RunWith(MockitoJUnitRunner::class)
class SplashPresenterTest {
    @Mock lateinit var mMockSplashView : SplashView
    @Mock lateinit var mMockDataManager: DataManager

    lateinit var splashPresenter : SplashPresenter<SplashView>
    lateinit var mTestScheduler: TestScheduler

    companion object {
        @BeforeClass
        @JvmStatic
        @Throws(Exception::class)
        fun onlyOnce() {
            RxAndroidPlugins.setInitMainThreadSchedulerHandler({
                Schedulers.trampoline()
            })
        }
    }

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        val compositeDisposable = CompositeDisposable()
        mTestScheduler = TestScheduler()
        val testSchedulerProvider = TestSchedulerProvider(mTestScheduler)

        splashPresenter = SplashPresenterImpl(mMockDataManager, compositeDisposable, testSchedulerProvider)
        splashPresenter.onAttach(mMockSplashView)
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        splashPresenter.onDetach()
        RxAndroidPlugins.reset()
    }

    @Test
    fun testOnStartOpensRegisterScreen() {
        splashPresenter.onStart()

        verify(mMockSplashView).openRegisterScreen()
    }

    @Test
    fun testOnUserSessionActiveOpensMainScreen() {
        splashPresenter.onUserSessionActive()

        verify(mMockSplashView).openMainScreen()
    }
}