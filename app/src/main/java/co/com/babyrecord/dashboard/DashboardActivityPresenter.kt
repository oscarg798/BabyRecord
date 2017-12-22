package co.com.babyrecord.dashboard

import android.app.Fragment
import android.content.Intent
import android.view.MenuItem
import co.com.babyrecord.R
import co.com.babyrecord.profile.BabyProfileFragment
import co.com.babyrecord.sleep_record.SleepRecordFragment
import java.lang.ref.WeakReference

/**
 * Created by oscarg798 on 12/22/17.
 */
class DashboardActivityPresenter : IDashboardActivityPresenter {

    private var mView: IDashboardActivityView? = null

    private var mCurrentFragment: WeakReference<Fragment>? = null

    override fun bind(view: IDashboardActivityView) {
        mView = view
        mView?.initComponents()

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_sleep_record -> {
                mCurrentFragment = WeakReference(SleepRecordFragment.newInstance())
                mCurrentFragment?.get()?.let {
                    mView?.changeFragment(mCurrentFragment!!.get()!!, "sleepRecordFragment")
                }


            }
            R.id.navigation_size -> {

            }
            R.id.navigation_profile -> {
                mCurrentFragment = WeakReference(BabyProfileFragment.newInstance())
                mCurrentFragment?.get()?.let {
                    mView?.changeFragment(mCurrentFragment!!.get()!!, "sleepRecordFragment")
                }
            }
        }
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        mCurrentFragment?.get()?.let {
            mCurrentFragment!!.get()!!.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun unBind() {
        mView = null
        mCurrentFragment?.clear()
    }
}