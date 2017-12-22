package co.com.babyrecord.profile


import android.app.Fragment
import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.com.babyrecord.CREATE_OR_EDIT_BABY_REQUEST_CODE

import co.com.babyrecord.R
import co.com.babyrecord.baby.CreateOrEditBabyActivity
import kotlinx.android.synthetic.main.fragment_profile.*


/**
 * A simple [Fragment] subclass.
 */
class BabyProfileFragment : Fragment(), IBabyProfileFragmentView {


    private val mPresenter: IBabyProfileFragmentPresenter = BabyProfileFragmentPresenter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresenter.bind(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.unBind()
    }

    override fun initComponents() {
        mSRLProfile?.isEnabled = false
        mFabCreateBaby?.setOnClickListener {
            activity?.let {
                startActivityForResult(Intent(activity,
                        CreateOrEditBabyActivity::class.java), CREATE_OR_EDIT_BABY_REQUEST_CODE)
            }

        }

    }

    override fun showProgressBar() {
        mSRLProfile?.isRefreshing = true

    }

    override fun hideProgressBar() {
        mSRLProfile?.isRefreshing = false
    }

    override fun showName(name: SpannableString) {
        mTVName?.text = name

    }

    override fun showBirthday(birthDay: SpannableString) {
        mTVBirthday?.text = birthDay
    }

    override fun showWeight(weight: SpannableString) {
        mTVWeigth?.text = weight
        mTVWeigth?.visibility = View.GONE
    }

    override fun showHeight(height: SpannableString) {
        mTVHeight?.text = height
        mTVHeight?.visibility = View.GONE
    }

    override fun hideWeight() {
        mTVWeigth?.visibility = View.GONE
    }

    override fun hideHeight() {
        mTVHeight?.visibility = View.GONE
    }

    override fun showNoBabyMessage() {
        mLLNoBabyMessage?.visibility = View.VISIBLE
        mFabCreateBaby?.visibility = View.VISIBLE
        mTVName?.visibility = View.GONE
        mTVBirthday?.visibility = View.GONE
        mTVHeight?.visibility = View.GONE
        mTVWeigth?.visibility = View.GONE
    }

    override fun hideNoBabyMessage() {
        mLLNoBabyMessage?.visibility = View.GONE
        mFabCreateBaby?.visibility = View.GONE
        mTVName?.visibility = View.VISIBLE
        mTVBirthday?.visibility = View.VISIBLE
        mTVHeight?.visibility = View.VISIBLE
        mTVWeigth?.visibility = View.VISIBLE
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mPresenter.onActivityResult(requestCode, resultCode, data)
    }

    companion object {
        fun newInstance(): BabyProfileFragment {
            return BabyProfileFragment()
        }
    }

}// Required empty public constructor
