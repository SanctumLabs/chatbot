package com.chatbot.ui.auth.register

import com.chatbot.data.DataManager
import com.chatbot.data.io.SchedulerProvider
import com.chatbot.ui.base.BasePresenterImpl
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * @author lusinabrian
 * @notes: Presenter layer to interact with data and view
 */
class RegisterPresenterImpl<V : RegisterView>
@Inject
constructor(mDatamanager: DataManager, mCompositeDisposable: CompositeDisposable,
            mSchedulerProvider: SchedulerProvider
) : BasePresenterImpl<V>(mDatamanager, mSchedulerProvider, mCompositeDisposable), RegisterPresenter<V> {

}
